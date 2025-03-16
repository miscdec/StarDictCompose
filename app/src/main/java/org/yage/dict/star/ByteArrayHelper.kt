package org.yage.dict.star

/**
 * byte[] functionality
 * @author olaf@merkert.de, beethoven99@126.com
 */
object ByteArrayHelper {
    fun toLong(`in`: ByteArray): Long {
        var out: Long = 0
        for (i in `in`.size - 1 downTo 1) {
            out = out or (`in`[i].toInt() and 0xff).toLong()
            out = out shl 8
        }
        out = out or (`in`[0].toInt() and 0xff).toLong()
        return out
    }

    /**
     * 按JAVA的小端顺序来
     * @param in
     * @return
     */
    fun toInt(`in`: ByteArray): Int {
        var out = 0
        for (i in `in`.size - 1 downTo 1) {
            out = out or (`in`[i].toInt() and 0xff)
            out = out shl 8
        }
        out = out or (`in`[0].toInt() and 0xff)
        return out
    }

    /**
     * 转成int，按大端序号来
     * @param in
     * @return
     */
    fun toIntAsBig(`in`: ByteArray): Int {
        var out = 0
        for (i in 0 until `in`.size - 1) {
            out = out or (`in`[i].toInt() and 0xff)
            out = out shl 8
        }
        out = out or (`in`[`in`.size - 1].toInt() and 0xff)
        return out
    }

    /**
     * 转成int，按大端序号来
     * @param buf
     * @return
     */
    fun toIntAsBig2(buf: ByteArray): Int {
        var out = Int.MAX_VALUE
        out = out and (0x000000ff and buf[3].toInt())
        out = out or (0x0000ff00 and (buf[2].toInt() shl 8))
        out = out or (0x00ff0000 and (buf[1].toInt() shl 16))
        out = out or (-0x1000000 and (buf[0].toInt() shl 24))
        return out
    }

    /**
     * 转成int，按大端序号来
     * @param buf
     * @return
     */
    fun toIntAsBig3(buf: ByteArray): Int {
        var out = 0
        out = out or (0x000000ff and buf[3].toInt())
        out = out or (0x0000ff00 and (buf[2].toInt() shl 8))
        out = out or (0x00ff0000 and (buf[1].toInt() shl 16))
        out = out or (-0x1000000 and (buf[0].toInt() shl 24))
        return out
    }

    fun toShort(`in`: ByteArray): Short {
        var out: Short = 0
        for (i in `in`.size - 1 downTo 1) {
            out = (out.toInt() or (`in`[i].toInt() and 0xff)).toShort()
            out = (out.toInt() shl 8).toShort()
        }
        out = (out.toInt() or (`in`[0].toInt() and 0xff)).toShort()
        return out
    }

    fun toByteArray(`in`: Int): ByteArray {
        val out = ByteArray(4)
        out[0] = `in`.toByte()
        out[1] = (`in` shr 8).toByte()
        out[2] = (`in` shr 16).toByte()
        out[3] = (`in` shr 24).toByte()
        return out
    }

    fun toByteArray(`in`: Int, outSize: Int): ByteArray {
        val out = ByteArray(outSize)
        val intArray = toByteArray(`in`)
        var i = 0
        while (i < intArray.size && i < outSize) {
            out[i] = intArray[i]
            i++
        }
        return out
    }

    fun toString(theByteArray: ByteArray): String {
        val out = StringBuffer()
        for (i in theByteArray.indices) {
            val s = Integer.toHexString(theByteArray[i].toInt() and 0xff)
            if (s.length < 2) {
                out.append('0')
            }
            out.append(s).append(' ')
        }
        return out.toString()
    }

    fun isEqual(first: ByteArray?, second: ByteArray?): Boolean {
        var out = first != null && second != null && first.size == second.size
        var i = 0
        while (out && i < first!!.size) {
            if (first[i] != second!![i]) {
                out = false
            }
            i++
        }
        return out
    }

    fun toInt(cs: CharArray): Int {
        return toInt(toByteArray(cs))
    }

    fun toByteArray(cs: CharArray): ByteArray {
        val res = ByteArray(cs.size)
        var i = 0
        for (c in cs) {
            res[i++] = c.code.toByte()
        }
        return res
    }

    fun toCharArray(cs: ByteArray): CharArray {
        val res = CharArray(cs.size)
        var i = 0
        for (c in cs) {
            res[i++] = Char(c.toUShort())
        }
        return res
    }
}
