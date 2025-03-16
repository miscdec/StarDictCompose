package org.yage.dict.star


import org.apache.commons.lang3.ArrayUtils
import java.io.FileInputStream
import java.io.IOException
import java.io.RandomAccessFile
import java.io.UnsupportedEncodingException
import java.nio.charset.StandardCharsets
import java.util.Locale

/**
 * StarDict dictionary file parser
 * usage as follows:<br></br>
 * `StarDictParser rdw=new StarDictParser();`<br></br>
 * `rdw.loadIndexFile(f);`<br></br>
 * `rdw.loadContentFile(fcnt);`<br></br>
 * @author beethoven99@126.com
 */
class StarDictParser(
    indexFile:FileInputStream,
    dictFile:RandomAccessFile,
    bufferSize: Int
) {


    //全局读文件缓冲区
    private val buf = ByteArray(bufferSize)

    //字符串的开始标记
    private var smark = 0

    //缓冲区的处理标记
    private var mark = 0

    //装载读到的单词和位置、长度
    private var words: MutableMap<String, WordPosition> = HashMap()

    //随机读取字典内容
    private var randomAccessFile: RandomAccessFile

    init {
        randomAccessFile = dictFile
        loadIndexFile(fis = indexFile, bufferSize = bufferSize)
    }

    fun searchWord(term: String): Map<String, WordPosition> {

        val result = mutableListOf<Pair<String, WordPosition>>()


        //间接开头的结果
        val resb= mutableListOf<Pair<String, WordPosition>>()

        for (en in words.entries) {
            val i = en.key
                .uppercase()
                .lowercase(Locale.getDefault())
                .indexOf(term)

            if (result.size < MAX_RESULT){
                if (i == 0) {
                    result.add(Pair(en.key,en.value))
                } else if (i > 0 && resb.size < MAX_RESULT) {
                    resb.add(Pair(en.key,en.value))
                }
            }
        }
        if (result.size < MAX_RESULT) {

            val need = MAX_RESULT - resb.size
            result.addAll(resb)
            if (need < resb.size) {
                result.take(need)
            }
        }
        return result.associate{ it }
    }

    /**
     * 加载内容文件
     * @param f
     */
//    fun loadContentFile(randomAccessFile: RandomAccessFile): Boolean {
//
//        try {
//            randomAccessFile = RandomAccessFile(f, "r")
//            println("is file open valid: " + randomAccessFile.fd.valid())
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//            return false
//        } catch (e: IOException) {
//            e.printStackTrace()
//            return false
//        }
//        return true
//    }

    /**
     * 得到字典内容中的内容片断，即为释义
     * @param start offset point
     * @param len length to get
     * @return
     */
    fun getWordExplanation(start: Int, len: Int): String {
        var res = ""
        val buf = ByteArray(len)
        try {
            randomAccessFile.seek(start.toLong())
            val ir = randomAccessFile.read(buf)
            if (ir != len) {
                println("Error occurred, not enought bytes read, wanting:$len,got:$ir")
            }
            res = String(buf, StandardCharsets.UTF_8)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return res
    }

    /**
     * 加载索引文件
     * @param f
     */
    private fun loadIndexFile(fis: FileInputStream,bufferSize: Int) {

        try {
            //第一次读
            var res = fis.read(buf)
            while (res > 0) {
                mark = 0
                smark = 0
                parseByteArray(buf, bufferSize)
                res = if (mark == res) {
                    //上一个刚好完整，可能性几乎不存在，不过还要考虑
                    fis.read(buf)
                } else {
                    //有上一轮未处理完的，应该从mark+1开始
                    //System.out.println("写 buf from: "+(buf.length-smark)+", len:"+(smark));
                    fis.read(buf, buf.size - smark, smark)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fis.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    /**
     * parse a block of bytes
     * @param buf 使用全局的，之后可以去掉它
     * @param len cbuf最多只有这么长
     * @throws UnsupportedEncodingException
     */
    private fun parseByteArray(buf: ByteArray, len: Int) {
        while (mark < len) {
            if (buf[mark].toInt() != 0) {
                //在字符串中
                if (mark == len - 1) {
                    //字符串被截断了
                    //System.out.println("字符串被截断：mark:"+mark+", smark: "+smark+", left:"+(len-mark));
                    System.arraycopy(buf, smark, buf, 0, len - smark)
                    break
                } else {
                    mark++
                }
            } else {
                //等于0，说明一个单词完结
                var tword: String? = null
                if (mark != 0) {
                    val bs = ArrayUtils.subarray(buf, smark, mark)
                    tword = String(bs, StandardCharsets.UTF_8)
                }
                if (len - mark > 8) {
                    //如果后面至少还有8个
                    smark = mark + 9
                    val bstartpos = ArrayUtils.subarray(buf, mark + 1, mark + 5)
                    val blen = ArrayUtils.subarray(buf, mark + 5, mark + 9)
                    val startpos = ByteArrayHelper.toIntAsBig(bstartpos)
                    val strlen = ByteArrayHelper.toIntAsBig(blen)
                    //System.out.println(" start: "+startpos+" , len:"+strlen);
                    //现在是一个完整的单词了
                    if (tword != null && tword.trim { it <= ' ' }.isNotEmpty() && strlen < 10000) {
                        words[tword] = WordPosition(startpos, strlen)
                    }
                    mark += 8
                } else {
                    //后面少于8个
                    //System.out.println("后面少于8个:mark="+mark+", smark: "+smark+", left:"+(len-mark));
                    //加完之后已跳过0，指向两个8字节数字
                    System.arraycopy(buf, smark, buf, 0, len - smark)
                    break
                }
            }
        }
    }

    fun getWords(): MutableMap<String, WordPosition> {
        return words
    }

    fun setWords(words: MutableMap<String, WordPosition>) {
        this.words = words
    }

    companion object {
        //最大返回结果数
        const val MAX_RESULT = 40

        const val DefaultIndexBufferSize = 1024

        /**
         * customer comparator
         */
        private val WordComparator =
            Comparator<Map.Entry<String, WordPosition>> { (key), (key1) ->
                key.compareTo(
                    key1, ignoreCase = true
                )
            }
    }
}
