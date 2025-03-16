package com.datong.dict.data.dictionary.en.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = YoudaoWord.TABLE_NAME)
/* loaded from: classes.dex */
class YoudaoWord() : Parcelable {

    @JvmField
    var authority: String? = null
    @JvmField
    var bilingual: String? = null
    @JvmField
    var collins: String? = null
    @JvmField
    var discriminate: String? = null
    @JvmField
    var expCN: String? = null
    @JvmField
    var expEN: String? = null
    @PrimaryKey
    @JvmField
    var id: Int = 0
    @JvmField
    var originalSound: String? = null
    var phoneticUk: String? = null
    var phoneticUs: String? = null
    @JvmField
    var relatWord: String? = null
    @JvmField
    var shape: String? = null
    @JvmField
    var synonyms: String? = null
    @JvmField
    var the21stDict: String? = null
    @JvmField
    var word: String? = null
    @JvmField
    var wordGroup: String? = null

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }




    constructor(parcel: Parcel) : this() {
        authority = parcel.readString()
        bilingual = parcel.readString()
        collins = parcel.readString()
        discriminate = parcel.readString()
        expCN = parcel.readString()
        expEN = parcel.readString()
        id = parcel.readInt()
        originalSound = parcel.readString()
        phoneticUk = parcel.readString()
        phoneticUs = parcel.readString()
        relatWord = parcel.readString()
        shape = parcel.readString()
        synonyms = parcel.readString()
        the21stDict = parcel.readString()
        word = parcel.readString()
        wordGroup = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(authority)
        parcel.writeString(bilingual)
        parcel.writeString(collins)
        parcel.writeString(discriminate)
        parcel.writeString(expCN)
        parcel.writeString(expEN)
        parcel.writeInt(id)
        parcel.writeString(originalSound)
        parcel.writeString(phoneticUk)
        parcel.writeString(phoneticUs)
        parcel.writeString(relatWord)
        parcel.writeString(shape)
        parcel.writeString(synonyms)
        parcel.writeString(the21stDict)
        parcel.writeString(word)
        parcel.writeString(wordGroup)
    }

    companion object CREATOR : Parcelable.Creator<YoudaoWord> {
        override fun createFromParcel(parcel: Parcel): YoudaoWord {
            return YoudaoWord(parcel)
        }

        override fun newArray(size: Int): Array<YoudaoWord?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME: String = "dict"
    }

}
