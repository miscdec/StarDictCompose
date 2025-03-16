package com.datong.dict.data.dictionary.en.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = BingWord.TABLE_NAME)
/* loaded from: classes.dex */
class BingWord() : Parcelable {
    @JvmField
    var antonym: String? = null

    @JvmField
    var baseExpCN: String? = null

    @JvmField
    var collocation: String? = null

    @JvmField
    var expEN: String? = null

    @JvmField
    var expEN2CN: String? = null

    @PrimaryKey
    @JvmField
    var id: Int = 0

    @JvmField
    var oxford: String? = null

    @JvmField
    var phoneticUk: String? = null

    @JvmField
    var phoneticUs: String? = null

    @JvmField
    var sentence: String? = null

    @JvmField
    var shape: String? = null

    @JvmField
    var soundUkUrl: String? = null

    @JvmField
    var soundUsUrl: String? = null

    @JvmField
    var synonym: String? = null

    @JvmField
    var word: String? = null

    constructor(parcel: Parcel) : this() {
        antonym = parcel.readString()
        baseExpCN = parcel.readString()
        collocation = parcel.readString()
        expEN = parcel.readString()
        expEN2CN = parcel.readString()
        id = parcel.readInt()
        oxford = parcel.readString()
        phoneticUk = parcel.readString()
        phoneticUs = parcel.readString()
        sentence = parcel.readString()
        shape = parcel.readString()
        soundUkUrl = parcel.readString()
        soundUsUrl = parcel.readString()
        synonym = parcel.readString()
        word = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(antonym)
        parcel.writeString(baseExpCN)
        parcel.writeString(collocation)
        parcel.writeString(expEN)
        parcel.writeString(expEN2CN)
        parcel.writeInt(id)
        parcel.writeString(oxford)
        parcel.writeString(phoneticUk)
        parcel.writeString(phoneticUs)
        parcel.writeString(sentence)
        parcel.writeString(shape)
        parcel.writeString(soundUkUrl)
        parcel.writeString(soundUsUrl)
        parcel.writeString(synonym)
        parcel.writeString(word)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BingWord> {
        override fun createFromParcel(parcel: Parcel): BingWord {
            return BingWord(parcel)
        }

        override fun newArray(size: Int): Array<BingWord?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME: String = "dict"
    }

}




