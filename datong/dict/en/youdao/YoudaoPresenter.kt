package com.datong.dict.module.dict.en.youdao

import android.os.Handler
import android.os.Looper
import com.datong.dict.base.BaseFragment
import com.datong.dict.data.dictionary.en.YoudaoRepository
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord
import com.datong.dict.data.dictionary.en.source.YoudaoDataSource
import com.datong.dict.module.dict.en.youdao.YoudaoContract.AuthorityView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.BaseExplainView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.BilingualView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.CollinsView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.DiscriminateView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.OriginalSoundView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.RelatWordView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.SynonymView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.The21stDictView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.WordGroupView
import com.datong.dict.module.dict.en.youdao.YoudaoContract.YoudaoView
import com.datong.dict.module.dict.en.youdao.items.authority.AuthorityFragment
import com.datong.dict.module.dict.en.youdao.items.authority.adapter.AuthorityItem
import com.datong.dict.module.dict.en.youdao.items.baseExplain.adapter.ExpEnItem
import com.datong.dict.module.dict.en.youdao.items.bilingual.BilingualFragment
import com.datong.dict.module.dict.en.youdao.items.bilingual.adapter.BilingualItem
import com.datong.dict.module.dict.en.youdao.items.collins.CollinsFragment
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsExplainItem
import com.datong.dict.module.dict.en.youdao.items.collins.adapter.CollinsItem
import com.datong.dict.module.dict.en.youdao.items.discriminate.DiscriminateFragment
import com.datong.dict.module.dict.en.youdao.items.discriminate.adapter.DiscriminateItem
import com.datong.dict.module.dict.en.youdao.items.originalSound.OriginalSoundFragment
import com.datong.dict.module.dict.en.youdao.items.originalSound.adapter.OriginalSoundItem
import com.datong.dict.module.dict.en.youdao.items.relatWord.RelatWordFragment
import com.datong.dict.module.dict.en.youdao.items.relatWord.adapter.RelatWordItem
import com.datong.dict.module.dict.en.youdao.items.synonym.SynonymFragment
import com.datong.dict.module.dict.en.youdao.items.synonym.adapter.SynonymItem
import com.datong.dict.module.dict.en.youdao.items.the21stDict.The21stDictFragment
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.The21DictItem
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.explain.The21stDictExplainItem
import com.datong.dict.module.dict.en.youdao.items.the21stDict.adapter.phrase.The21stDictPhraseItem
import com.datong.dict.module.dict.en.youdao.items.wordGroup.WordGroupFragment
import com.datong.dict.module.dict.en.youdao.items.wordGroup.adapter.WordGroupItem
import com.datong.dict.utils.ClipboardUtil
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.jsoup.select.Elements

/* loaded from: classes.dex */
class YoudaoPresenter(
    var word: String,
    private val youdaoView: YoudaoView,
    private val baseExplainView: BaseExplainView,
    private val collinsView: CollinsView,
    private val the21stDictView: The21stDictView,
    private val wordGroupView: WordGroupView,
    private val synonymsView: SynonymView,
    private val relatWordView: RelatWordView,
    private val discriminateView: DiscriminateView,
    private val bilingualView: BilingualView,
    private val originalSoundView: OriginalSoundView,
    private val authorityView: AuthorityView,
    var repository: YoudaoRepository
) : YoudaoContract.Presenter {
    init {
        youdaoView.setPresenter(this)
        baseExplainView.setPresenter(this)
        collinsView.setPresenter(this)
        the21stDictView.setPresenter(this)
        wordGroupView.setPresenter(this)
        synonymsView.setPresenter(this)
        relatWordView.setPresenter(this)
        discriminateView.setPresenter(this)
        bilingualView.setPresenter(this)
        originalSoundView.setPresenter(this)
        authorityView.setPresenter(this)
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$1  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass1 internal constructor(/* synthetic */val fragmentList: MutableList<BaseFragment>) :
        YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$1$$ExternalSyntheticLambda1
            // java.lang.Runnable
            {
                filterPagerTitles(word, fragmentList)
                Handler(Looper.getMainLooper()).post(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$1$$ExternalSyntheticLambda0
                // java.lang.Runnable
                {
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    youdaoView.updatePager()
                })
            }).start()
        }

        /* synthetic */ /* synthetic */
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
            youdaoView.hideRefreshView()
        }

        private fun filterPagerTitles(word: YoudaoWord, fragmentList: MutableList<BaseFragment?>) {
            try {
                val arrayList: ArrayList<BaseFragment> = ArrayList()
                for (baseFragment in fragmentList) {
                    if (((baseFragment is CollinsFragment) && word.collins!!.isEmpty()) || (((baseFragment is The21stDictFragment) && word.getThe21stDict()
                            .equals("")) || (((baseFragment is WordGroupFragment) && word.wordGroup
                            .equals("")) || (((baseFragment is SynonymFragment) && word.synonyms
                            .equals("")) || (((baseFragment is RelatWordFragment) && word.relatWord
                            .equals("")) || (((baseFragment is DiscriminateFragment) && word.discriminate
                            .equals("")) || (((baseFragment is BilingualFragment) && word.bilingual
                            .equals("")) || (((baseFragment is OriginalSoundFragment) && word.originalSound
                            .equals("")) || ((baseFragment is AuthorityFragment) && word.authority
                            .equals("")))))))))
                    ) {
                        arrayList.add(baseFragment)
                    }
                }
                for (baseFragment2 in arrayList) {
                    fragmentList.remove(baseFragment2)
                }
                arrayList.clear()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadPagerTitles(fragmentList: MutableList<BaseFragment>) {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass1(fragmentList))
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadBaseExplain() {
        repository.getWord(this.word, object : YoudaoDataSource.GetWordCallback {
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter.2
            // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
            override fun onError() {
            }

            // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
            override fun onLoad(word: YoudaoWord) {
                if (youdaoView.context == null) {
                    return
                }
                baseExplainView.setWord(word.word)
                val baseExplainView = this@YoudaoPresenter.baseExplainView
                baseExplainView.setPhoneticUs("美 " + word.phoneticUs)
                val baseExplainView2 = this@YoudaoPresenter.baseExplainView
                baseExplainView2.setPhoneticUk("英 " + word.phoneticUk)
                this@YoudaoPresenter.baseExplainView.setExpCN(word.expCN)
                this@YoudaoPresenter.baseExplainView.setShape(word.shape)
                this@YoudaoPresenter.baseExplainView.showBaseExplainCard()
            }
        })
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$3  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass3 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            if (word.expEN.equals("")) {
                youdaoView.hideRefreshView()
            } else {
                Thread {
                    val expEnItemList = getExpEnItemList(word)
                    Handler(Looper.getMainLooper()).postDelayed({
                        this@AnonymousClass3.onLoadYoudaoPresenter(
                            expEnItemList
                        )
                    }, 400L)
                }.start()
            }
        }

        /* synthetic */
        /* synthetic */ fun onLoadYoudaoPresenter(list: List<ExpEnItem>?) {
            youdaoView.hideRefreshView()
            if (youdaoView.context == null) {
                return
            }
            baseExplainView.showExplainEnCard()
            baseExplainView.setExpEnListAdapter(list)
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
            youdaoView.hideRefreshView()
        }

        private fun getExpEnItemList(word: YoudaoWord): List<ExpEnItem> {
            val arrayList = ArrayList<ExpEnItem>()
            while (Jsoup.parse(word.expEN!!).getElementsByTag("list").iterator().hasNext()) {
                val next = Jsoup.parse(word.expEN!!).getElementsByTag("list").iterator().next()
                val text = next.getElementsByTag("wordClass").text()
                val elementsByTag = next.getElementsByTag("item")
                var i = 0
                while (i < elementsByTag.size) {
                    val element = elementsByTag[i]
                    val sb = StringBuilder()
                    i++
                    sb.append(i)
                    sb.append("")
                    val sb2 = sb.toString()
                    val text2 = element.getElementsByTag("explain").text()
                    val text3 = element.getElementsByTag("sentence").text()
                    val text4 = element.getElementsByTag("synonyms").text()
                    val expEnItem = ExpEnItem()
                    expEnItem.wordClass = text
                    expEnItem.index = sb2
                    expEnItem.explain = text2
                    expEnItem.sentence = text3
                    expEnItem.synonyms = text4
                    arrayList.add(expEnItem)
                }
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadExplainEN() {
        repository.getWord(this.word, AnonymousClass3())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$4  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass4 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$4$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val collinsItemList = getCollinsItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$4$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    collinsView.setCollinsListAdapter(collinsItemList)
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getCollinsItemList(word: YoudaoWord): List<CollinsItem> {
            var str: String?
            var str2: String?
            var str3: String?
            var str4: String?
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            var it: Iterator<Element> =
                Jsoup.parse(word.collins).getElementsByTag("list").iterator()
            while (it.hasNext()) {
                var next = it.next()
                val text = next.getElementsByTag("title").text()
                val collinsItem = CollinsItem()
                collinsItem.title = text
                var it2: Iterator<Element> = next.getElementsByTag("item").iterator()
                while (it2.hasNext()) {
                    val next2 = it2.next()
                    val text2 = next2.getElementsByTag("word").text()
                    val text3 = next2.getElementsByTag("phonetic").text()
                    val text4 = next2.getElementsByTag("starCount").text()
                    val text5 = next2.getElementsByTag("exam").text()
                    val text6 = next2.getElementsByTag("shape").text()
                    val arrayList2: ArrayList<*> = ArrayList<Any?>()
                    var elementsByTag = next.getElementsByTag("explain")
                    var i = 0
                    while (i < elementsByTag.size) {
                        val element = elementsByTag[i]
                        val sb = StringBuilder()
                        val i2 = i + 1
                        sb.append(i2)
                        sb.append("")
                        val sb2 = sb.toString()
                        val it3 = it
                        val text7 = element.getElementsByTag("wordClass").text()
                        val element2 = next
                        val text8 = element.getElementsByTag("exp").text()
                        val it4 = it2
                        val elementsByTag2 = element.getElementsByTag("sentence")
                        val elements = elementsByTag
                        if (elementsByTag2.size == 1) {
                            str3 = elementsByTag2.first().getElementsByTag("en").text()
                            str4 = elementsByTag2.first().getElementsByTag("cn").text()
                            str = ""
                            str2 = str
                        } else if (elementsByTag2.size > 1) {
                            val text9 = elementsByTag2[0].getElementsByTag("en").text()
                            str4 = elementsByTag2[0].getElementsByTag("cn").text()
                            val text10 = elementsByTag2[1].getElementsByTag("en").text()
                            str2 = elementsByTag2[1].getElementsByTag("cn").text()
                            str = text10
                            str3 = text9
                        } else {
                            str = ""
                            str2 = str
                            str3 = str2
                            str4 = str3
                        }
                        val collinsExplainItem = CollinsExplainItem()
                        collinsExplainItem.index = sb2
                        collinsExplainItem.wordClas = text7
                        collinsExplainItem.explain = text8
                        collinsExplainItem.en1 = str3
                        collinsExplainItem.cn1 = str4
                        collinsExplainItem.en2 = str
                        collinsExplainItem.cn2 = str2
                        arrayList2.add(collinsExplainItem)
                        it = it3
                        next = element2
                        it2 = it4
                        elementsByTag = elements
                        i = i2
                    }
                    collinsItem.word = text2
                    collinsItem.phonetic = text3
                    collinsItem.starCount = text4
                    collinsItem.exam = text5
                    collinsItem.shape = text6
                    collinsItem.setExplainItemList(arrayList2)
                }
                arrayList.add(collinsItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadCollins() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass4())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$5  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass5 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$5$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val the21DictItemList = getThe21DictItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$5$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    the21stDictView.setThe21stDictListAdapter(the21DictItemList)
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getThe21DictItemList(word: YoudaoWord): List<The21DictItem> {
            var it: Iterator<Element>
            var element: Element
            var it2: Iterator<Element>
            var element2: Element
            var it3: Iterator<Element>
            var str: String?
            var str2: String?
            var str3: String?
            var str4: String?
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            var it4: Iterator<Element> =
                Jsoup.parse(word.getThe21stDict()).getElementsByTag("list").iterator()
            while (it4.hasNext()) {
                var next = it4.next()
                val the21DictItem = The21DictItem()
                val arrayList2: ArrayList<*> = ArrayList<Any?>()
                val arrayList3: ArrayList<*> = ArrayList<Any?>()
                var it5: Iterator<Element> = next.getElementsByTag("item").iterator()
                while (it5.hasNext()) {
                    val next2 = it5.next()
                    if (next2.getElementsByTag("explain").size > 0) {
                        val text = next.getElementsByTag("listTitle").text()
                        val text2 = next2.getElementsByTag("itemTitle").text()
                        val elementsByTag = next2.getElementsByTag("explain")
                        var i = 0
                        while (i < elementsByTag.size) {
                            val element3 = elementsByTag[i]
                            val sb = StringBuilder()
                            i++
                            sb.append(i)
                            sb.append("")
                            val sb2 = sb.toString()
                            val ownText = element3.ownText()
                            val it6 = it4
                            if (element3.children().size == 2) {
                                str = element3.children()[0].text()
                                element2 = next
                                it3 = it5
                                str4 = element3.children()[1].text()
                                str2 = ""
                                str3 = str2
                            } else if (element3.children().size > 2) {
                                val text3 = element3.children()[0].text()
                                element2 = next
                                val text4 = element3.children()[1].text()
                                val text5 = element3.children()[2].text()
                                val text6 = element3.children()[3].text()
                                it3 = it5
                                str4 = text4
                                str2 = text5
                                str = text3
                                str3 = text6
                            } else {
                                element2 = next
                                it3 = it5
                                str = ""
                                str2 = str
                                str3 = str2
                                str4 = str3
                            }
                            val the21stDictExplainItem = The21stDictExplainItem()
                            the21stDictExplainItem.wordClass = text2
                            the21stDictExplainItem.index = sb2
                            the21stDictExplainItem.explain = ownText
                            the21stDictExplainItem.en1 = str
                            the21stDictExplainItem.cn1 = str4
                            the21stDictExplainItem.en2 = str2
                            the21stDictExplainItem.cn2 = str3
                            arrayList2.add(the21stDictExplainItem)
                            it4 = it6
                            it5 = it3
                            next = element2
                        }
                        it = it4
                        element = next
                        it2 = it5
                        the21DictItem.explainTitle = text
                        the21DictItem.setExplainItemList(arrayList2)
                    } else {
                        it = it4
                        element = next
                        it2 = it5
                        if (next2.getElementsByTag("synonyms").size <= 0 && next2.getElementsByTag("antonyms").size <= 0) {
                            if (next2.getElementsByTag("phrase").size > 0) {
                                val elementsByTag2 = next2.getElementsByTag("phrase")
                                var i2 = 0
                                while (i2 < elementsByTag2.size) {
                                    val element4 = elementsByTag2[i2]
                                    val sb3 = StringBuilder()
                                    i2++
                                    sb3.append(i2)
                                    sb3.append("")
                                    val sb4 = sb3.toString()
                                    val ownText2 = element4.ownText()
                                    val elementsByTag3 = element4.getElementsByTag("exp")
                                    var str5 = ""
                                    var i3 = 0
                                    while (i3 < elementsByTag3.size) {
                                        val sb5 = StringBuilder()
                                        sb5.append(str5)
                                        val i4 = i3 + 1
                                        sb5.append(i4)
                                        sb5.append(". ")
                                        sb5.append(elementsByTag3[i3].text())
                                        sb5.append(if (i3 == elementsByTag3.size - 1) "" else "\n")
                                        str5 = sb5.toString()
                                        i3 = i4
                                    }
                                    val the21stDictPhraseItem = The21stDictPhraseItem()
                                    the21stDictPhraseItem.index = sb4
                                    the21stDictPhraseItem.phrase = ownText2
                                    the21stDictPhraseItem.explain = str5
                                    arrayList3.add(the21stDictPhraseItem)
                                }
                                the21DictItem.setPhraseItemList(arrayList3)
                            } else {
                                next2.getElementsByTag("shape").size
                            }
                        }
                    }
                    it4 = it
                    it5 = it2
                    next = element
                }
                arrayList.add(the21DictItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadThe21stDict() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass5())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$6  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass6 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$6$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val wordGroupList = getWordGroupList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$6$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    wordGroupView.setWordGroupListAdapter(wordGroupList)
                    wordGroupView.showWordGroupCard()
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getWordGroupList(word: YoudaoWord): List<WordGroupItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val elementsByTag: Elements = Jsoup.parse(word.getWordGroup()).getElementsByTag("item")
            var i = 0
            while (i < elementsByTag.size) {
                val element = elementsByTag[i]
                val sb = StringBuilder()
                i++
                sb.append(i)
                sb.append("")
                val sb2 = sb.toString()
                val text = element.getElementsByTag("en").text()
                val text2 = element.getElementsByTag("cn").text()
                val wordGroupItem = WordGroupItem()
                wordGroupItem.index = sb2
                wordGroupItem.wordGroup = text
                wordGroupItem.explain = text2
                arrayList.add(wordGroupItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadWordGroup() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass6())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$7  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass7 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$7$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val synonymList = getSynonymList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$7$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    synonymsView.setSynonymListAdapter(synonymList)
                    synonymsView.showSynonymCard()
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getSynonymList(word: YoudaoWord): List<SynonymItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val elementsByTag: Elements = Jsoup.parse(word.getSynonyms()).getElementsByTag("item")
            var i = 0
            while (i < elementsByTag.size) {
                val element = elementsByTag[i]
                val sb = StringBuilder()
                i++
                sb.append(i)
                sb.append("")
                val sb2 = sb.toString()
                val text = element.getElementsByTag("explain").text()
                val text2 = element.getElementsByTag("synonym").text()
                val synonymItem = SynonymItem()
                synonymItem.index = sb2
                synonymItem.explain = text
                synonymItem.synonym = text2
                arrayList.add(synonymItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadSynonym() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass7())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$8  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass8 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$8$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val relatWordItemList = getRelatWordItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$8$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    relatWordView.setRelatWordListAdapter(relatWordItemList)
                    relatWordView.showRelatWordCard()
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getRelatWordItemList(word: YoudaoWord): List<RelatWordItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val elementsByTag: Elements = Jsoup.parse(word.getRelatWord()).getElementsByTag("item")
            var i = 0
            while (i < elementsByTag.size) {
                val element = elementsByTag[i]
                val sb = StringBuilder()
                i++
                sb.append(i)
                sb.append("")
                val sb2 = sb.toString()
                val text = element.getElementsByTag("word").text()
                val text2 = element.getElementsByTag("explain").text()
                val relatWordItem = RelatWordItem()
                relatWordItem.index = sb2
                relatWordItem.word = text
                relatWordItem.explain = text2
                arrayList.add(relatWordItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadRelatWord() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass8())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$9  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass9 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$9$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val discriminateItemList = getDiscriminateItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$9$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    discriminateView.setDiscriminateListAdapter(discriminateItemList)
                    discriminateView.showDiscriminateCard()
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getDiscriminateItemList(word: YoudaoWord): List<DiscriminateItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val elementsByTag: Elements =
                Jsoup.parse(word.getDiscriminate()).getElementsByTag("list")
            var i = 0
            while (i < elementsByTag.size) {
                val element = elementsByTag[i]
                val sb = StringBuilder()
                i++
                sb.append(i)
                sb.append("")
                val sb2 = sb.toString()
                val replace = element.getElementsByTag("title").text().replace(",", " , ")
                val text = element.getElementsByTag("subTitle").text()
                val elementsByTag2 = element.getElementsByTag("item")
                var str = ""
                var i2 = 0
                while (i2 < elementsByTag2.size) {
                    val element2 = elementsByTag2[i2]
                    val text2 = element2.getElementsByTag("word").text()
                    val text3 = element2.getElementsByTag("explain").text()
                    val sb3 = StringBuilder()
                    sb3.append(str)
                    sb3.append(text2)
                    sb3.append(" : ")
                    sb3.append(text3)
                    sb3.append(if (i2 == elementsByTag2.size + (-1)) "" else "\n")
                    str = sb3.toString()
                    i2++
                }
                val discriminateItem = DiscriminateItem()
                discriminateItem.index = sb2
                discriminateItem.title = replace
                discriminateItem.subTitle = text
                discriminateItem.cotent = str
                arrayList.add(discriminateItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onloadDiscriminate() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass9())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$10  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass10 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$10$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val bilingualItemList = getBilingualItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$10$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    bilingualView.setBilingualListAdapter(bilingualItemList)
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
            youdaoView.hideRefreshView()
        }

        fun getBilingualItemList(word: YoudaoWord): List<BilingualItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val elementsByTag: Elements = Jsoup.parse(word.getBilingual()).getElementsByTag("item")
            var i = 0
            while (i < elementsByTag.size) {
                val element = elementsByTag[i]
                val sb = StringBuilder()
                i++
                sb.append(i)
                sb.append("")
                val sb2 = sb.toString()
                val text = element.getElementsByTag("en").text()
                val text2 = element.getElementsByTag("cn").text()
                val text3 = element.getElementsByTag(Constants.FROM).text()
                val bilingualItem = BilingualItem()
                bilingualItem.index = sb2
                bilingualItem.en = text
                bilingualItem.cn = text2
                bilingualItem.from = text3
                arrayList.add(bilingualItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onlaodBilingual() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass10())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$11  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass11 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$11$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val originalSoundItemList = getOriginalSoundItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$11$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    originalSoundView.setOriginalSoundListAdapter(originalSoundItemList)
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
            youdaoView.hideRefreshView()
        }

        private fun getOriginalSoundItemList(word: YoudaoWord): List<OriginalSoundItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val it: Iterator<Element> =
                Jsoup.parse(word.getOriginalSound()).getElementsByTag("item").iterator()
            var i = 0
            while (it.hasNext()) {
                val next = it.next()
                if (next.getElementsByTag("soundUrl").size != 0) {
                    i++
                    val text = next.getElementsByTag("en").text()
                    val text2 = next.getElementsByTag("soundUrl").text()
                    val text3 = next.getElementsByTag(Constants.FROM).text()
                    val originalSoundItem = OriginalSoundItem()
                    originalSoundItem.index = i.toString() + ""
                    originalSoundItem.en = text
                    originalSoundItem.soundUrl = text2
                    originalSoundItem.from = text3
                    arrayList.add(originalSoundItem)
                }
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onlaodOriginalSound() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass11())
    }

    /* JADX INFO: Access modifiers changed from: package-private */ /* renamed from: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$12  reason: invalid class name */ /* loaded from: classes.dex */
    inner class AnonymousClass12 internal constructor() : YoudaoDataSource.GetWordCallback {
        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onError() {
        }

        // com.datong.dict.data.dictionary.en.source.YoudaoDataSource.GetWordCallback
        override fun onLoad(word: YoudaoWord) {
            Thread(Runnable
            // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$12$$ExternalSyntheticLambda0
            // java.lang.Runnable
            {
                val authorityItemList = getAuthorityItemList(word)
                Handler(Looper.getMainLooper()).postDelayed(Runnable
                // from class: com.datong.dict.module.dict.en.youdao.YoudaoPresenter$12$$ExternalSyntheticLambda1
                // java.lang.Runnable
                {
                    youdaoView.hideRefreshView()
                    if (youdaoView.context == null) {
                        return@Runnable
                    }
                    authorityView.setAuthorityListAdapter(authorityItemList)
                }, 500L
                )
            }).start()
        }

        /* synthetic */ /* synthetic */
        private fun getAuthorityItemList(word: YoudaoWord): List<AuthorityItem> {
            val arrayList: ArrayList<*> = ArrayList<Any?>()
            val it: Iterator<Element> =
                Jsoup.parse(word.getAuthority()).getElementsByTag("item").iterator()
            var i = 0
            while (it.hasNext()) {
                val next = it.next()
                i++
                val text = next.getElementsByTag("en").text()
                val text2 = next.getElementsByTag(Constants.FROM).text()
                val authorityItem = AuthorityItem()
                authorityItem.index = i.toString() + ""
                authorityItem.en = text
                authorityItem.from = text2
                arrayList.add(authorityItem)
            }
            return arrayList
        }
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun onlaodAuthority() {
        youdaoView.showRefreshView()
        repository.getWord(this.word, AnonymousClass12())
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun showWordSnackbar(word: String) {
        youdaoView.showWordSnackbar(word)
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun getWordText(): String {
        return this.word
    }

    // com.datong.dict.module.dict.en.youdao.YoudaoContract.Presenter
    override fun copyToClipboard(word: String) {
        ClipboardUtil.copy(youdaoView.rootView.context, word)
        youdaoView.showMessageSnackbar("已复制到剪切板！")
    }
}
