package com.datong.dict.data.dictionary.en.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DatongEnWord.TABLE_NAME)
/* loaded from: classes.dex */
class DatongEnWord() : Parcelable {

    @JvmField
    var antonym: String? = null

    @JvmField
    var expCN: String? = null

    @JvmField
    var expEN: String? = null
    @JvmField
    @PrimaryKey var id: Int = 0
    @JvmField
    var phoneticUk: String? = null
    @JvmField
    var phoneticUs: String? = null
    @JvmField
    var sentence: String? = null
    @JvmField
    var shape: String? = null
    @JvmField
    var syllable: String? = null
    @JvmField
    var synonym: String? = null
    @JvmField
    var word: String? = null

    @JvmField
    var wordLevel: Int = 0

    @JvmField
    var wordOrigin: String? = null
    @JvmField
    var wordRoot: String? = null

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }


    constructor(parcel: Parcel) : this() {
        antonym = parcel.readString()
        expCN = parcel.readString()
        expEN = parcel.readString()
        id = parcel.readInt()
        phoneticUk = parcel.readString()
        phoneticUs = parcel.readString()
        sentence = parcel.readString()
        shape = parcel.readString()
        syllable = parcel.readString()
        synonym = parcel.readString()
        word = parcel.readString()
        wordLevel = parcel.readInt()
        wordOrigin = parcel.readString()
        wordRoot = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(antonym)
        parcel.writeString(expCN)
        parcel.writeString(expEN)
        parcel.writeInt(id)
        parcel.writeString(phoneticUk)
        parcel.writeString(phoneticUs)
        parcel.writeString(sentence)
        parcel.writeString(shape)
        parcel.writeString(syllable)
        parcel.writeString(synonym)
        parcel.writeString(word)
        parcel.writeInt(wordLevel)
        parcel.writeString(wordOrigin)
        parcel.writeString(wordRoot)
    }

    companion object CREATOR : Parcelable.Creator<DatongEnWord> {
        override fun createFromParcel(parcel: Parcel): DatongEnWord {
            return DatongEnWord(parcel)
        }

        override fun newArray(size: Int): Array<DatongEnWord?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME: String = "dict"
    }

}
