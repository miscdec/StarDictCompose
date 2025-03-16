package com.datong.dict.module.dict

import android.graphics.Color
import com.datong.dict.R
import com.datong.dict.base.BaseFragment
import com.datong.dict.data.dictionary.en.DatongEnRepository.Companion.getInstance
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord
import com.datong.dict.data.dictionary.en.source.DatongEnDateSource
import com.datong.dict.data.dictionary.jp.DatongJapRepository
import com.datong.dict.data.dictionary.jp.local.entity.datong.DatongJapWord
import com.datong.dict.data.dictionary.jp.source.DatongJapDateSource
import com.datong.dict.module.dict.adapter.SelectorItem
import com.datong.dict.utils.SettingUtil
import com.datong.dict.utils.SoundUtil
import com.datong.dict.widget.MessageSnackbar
import com.datong.dict.widget.WordSnackbar

/* loaded from: classes.dex */
class DictPresenter(var view: DictContract.View) : DictContract.Presenter {
    // com.datong.dict.module.dict.DictContract.Presenter
    override fun getMenuItems(currentFragment: BaseFragment): Array<String> {
        return arrayOf(4.toString())
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun handleMenuItemClick(item: String) {
    }

    init {
        view.setPresenter(this)
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun onloadSelectorItems() {
        view.selectorItems.clear()
        val selectorItem = SelectorItem()
        selectorItem.logo = R.drawable.img_dict_selector_datong
        selectorItem.dict = 1
        selectorItem.bgColor = "#e79686"
        selectorItem.dictName = "大同词典"
        view.selectorItems.add(selectorItem)
        val selectorItem2 = SelectorItem()
        selectorItem2.logo = R.drawable.img_dict_selector_youdao
        selectorItem2.dict = 2
        selectorItem2.bgColor = "#e79686"
        selectorItem2.dictName = "有道词典"
        view.selectorItems.add(selectorItem2)
        val selectorItem3 = SelectorItem()
        selectorItem3.logo = R.drawable.img_dict_selector_ciba
        selectorItem3.dict = 3
        selectorItem3.bgColor = "#7abed1"
        selectorItem3.dictName = "金山词霸"
        view.selectorItems.add(selectorItem3)
        val selectorItem4 = SelectorItem()
        selectorItem4.logo = R.drawable.img_dict_selector_bing
        selectorItem4.dict = 4
        selectorItem4.bgColor = "#44b29d"
        selectorItem4.dictName = "必应词典"
        view.selectorItems.add(selectorItem4)
        val selectorItem5 = SelectorItem()
        selectorItem5.logo = R.drawable.img_dict_selector_dict_cn
        selectorItem5.dict = 5
        selectorItem5.bgColor = "#48c9b0"
        selectorItem5.dictName = "海词词典"
        view.selectorItems.add(selectorItem5)
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun handleOnSelectorItemClick(item: SelectorItem) {
        val dict = item.dict
//        if (dict == 1) {
//            view.showDatongEnFragment()
//        } else if (dict == 2) {
            view.showYoudaoFragment()
//        } else if (dict == 3) {
//            view.showCibaFragment()
//        } else if (dict == 4) {
//            view.showBingFragment()
//        } else if (dict == 5) {
//            view.showDictCnFragment()
//        }
        view.setDictSelectorColor(Color.parseColor(item.bgColor))
        view.setToolbarTitle(item.dictName)
        WordSnackbar.dismiss()
        MessageSnackbar.dismiss()
    }

    /* synthetic */
    // com.datong.dict.module.dict.DictContract.Presenter
    override fun setCurrentFragment(language: String) {
        if (language == "en") {
            val defaultDictEN = SettingUtil.getDefaultDictEN()
            when {
                defaultDictEN == 0 -> {
                    view.setToolbarTitle("大同词典")
                    view.setDictSelectorColor(Color.parseColor("#e79686"))
//                    view.setCurrentFragmentIsDatongEn()
                }
                defaultDictEN == 1 -> {
                    view.setToolbarTitle("有道词典")
                    view.setDictSelectorColor(Color.parseColor("#e79686"))
                    view.setCurrentFragmentIsYoudao()
                }
                defaultDictEN == 2 -> {
                    view.setToolbarTitle("金山词霸")
                    view.setDictSelectorColor(Color.parseColor("#7abed1"))
//                    view.setCurrentFragmentIsCiba()
                }
                defaultDictEN == 3 -> {
                    view.setToolbarTitle("必应词典")
                    view.setDictSelectorColor(Color.parseColor("#44b29d"))
//                    view.setCurrentFragmentIsBing()
                }
                defaultDictEN != 4 -> {
                }
                else -> {
                    view.setToolbarTitle("海词词典")
                    view.setDictSelectorColor(Color.parseColor("#48c9b0"))
//                    view.setCurrentFragmentIsDictCn()
                }
            }
        } else if (language == "jp" && SettingUtil.getDefaultDictJP() == 0) {
            view.setToolbarTitle("大同词典")
//            view.setCurrentFragmentIsDatongJp()
        }
    }

    override fun updateCollectState() {
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    /* renamed from: updateCollectState */ /* synthetic */ /* synthetic */
    // com.datong.dict.module.dict.DictContract.Presenter
    override fun collectWhenQuery(word: String, wordId: String) {
        if (SettingUtil.isAutoCollect() && SettingUtil.getCollectMode() == 0) {
        }
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun collectWhenClick(word: String, wordId: String) {
    }

    /* synthetic */
    // com.datong.dict.module.dict.DictContract.Presenter
    override fun addSearchHistory(word: String, wordId: String) {
        if (SettingUtil.getLanguage() == 0) {
            getInstance(view.context)!!
                .getWord(word, object : DatongEnDateSource.GetWordCallback {
                    // from class: com.datong.dict.module.dict.DictPresenter.3
                    // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
                    override fun onError() {
                    }

                    // com.datong.dict.data.dictionary.en.source.DatongEnDateSource.GetWordCallback
                    override fun onLoad(word: DatongEnWord) {
                    }
                })
        } else {
            DatongJapRepository.getInstance(view.context)
                .getWord(wordId, object : DatongJapDateSource.GetWordCallback {
                    // from class: com.datong.dict.module.dict.DictPresenter.4
                    // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
                    override fun error() {
                    }

                    // com.datong.dict.data.dictionary.jp.source.DatongJapDateSource.GetWordCallback
                    override fun onload(word2: DatongJapWord) {
                    }
                })
        }
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun deleteAllCollected(word: String) {
        if (SettingUtil.getLanguage() == 1) {
            return
        }
    }

    // com.datong.dict.module.dict.DictContract.Presenter
    override fun autoPlaySound(word: String) {
        val i: Int
        val i2: Int
        if (SettingUtil.isAutoPlaySound()) {
            if (SettingUtil.getLanguage() == 0) {
                i2 = if (SettingUtil.getPhoneticType() == 0) 1 else 2
                i = 1
            } else {
                i = -1
                i2 = 4
            }
            SoundUtil.getInstance().text(word).url("").soundType(1).dictType(i).languageType(i2)
                .play()
        }
    }
}
