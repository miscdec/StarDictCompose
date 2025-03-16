package com.datong.dict.data.dictionary.jp.local.entity.datong

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.datong.dict.utils.NotNullUtil

@Entity(tableName = Index.TABLE_NAME) /* loaded from: classes.dex */
class Index {
    var accent: String? = null
    var expBase: String? = null
        get() = NotNullUtil.getString(field)
    @JvmField
    var expJP: String? = null

    @JvmField
    var indexExpBase: String? = null

    @JvmField
    var indexExpJP: String? = null
    @JvmField
    var kana: String? = null
    @JvmField
    var romaji: String? = null
    @JvmField
    var word: String? = null
    var wordClass: String? = null

    @PrimaryKey
    var wordId: String = ""


    companion object {
        const val TABLE_NAME: String = "Index_"
    }
}
