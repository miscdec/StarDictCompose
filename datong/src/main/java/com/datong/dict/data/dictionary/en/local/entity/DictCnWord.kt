package com.datong.dict.data.dictionary.en.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = DictCnWord.TABLE_NAME)
/* loaded from: classes.dex */
class DictCnWord() : Parcelable {

    var phoneticUs: String? = null
    @JvmField
    var collocation: String? = null
    @JvmField
    var discriminate: String? = null
    @JvmField
    var expCN: String? = null
    @JvmField
    var expDetail: String? = null
    @JvmField
    var expDouble: String? = null
    @JvmField
    var expEN: String? = null
    @JvmField
    var frequency: String? = null

    @PrimaryKey
    @JvmField
    var id: Int = 0
    @JvmField
    var mistakes: String? = null
    @JvmField
    var oftenPhrase: String? = null
    @JvmField
    var oftenSentence: String? = null
    @JvmField
    var phoneticUk: String? = null
    @JvmField
    var sentence: String? = null
    @JvmField
    var sentenceFrequency: String? = null
    @JvmField
    var shape: String? = null
    @JvmField
    var star: Int = 0
    @JvmField
    var syllable: String? = null
    @JvmField
    var synonAndAnton: String? = null
    @JvmField
    var ukSoundUrlMan: String? = null
    @JvmField
    var ukSoundUrlWoman: String? = null
    @JvmField
    var usSoundUrlMan: String? = null
    @JvmField
    var usSoundUrlWoman: String? = null
    @JvmField
    var usage: String? = null
    @JvmField
    var word: String? = null

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }




    constructor(parcel: Parcel) : this() {
        phoneticUs = parcel.readString()
        collocation = parcel.readString()
        discriminate = parcel.readString()
        expCN = parcel.readString()
        expDetail = parcel.readString()
        expDouble = parcel.readString()
        expEN = parcel.readString()
        frequency = parcel.readString()
        id = parcel.readInt()
        mistakes = parcel.readString()
        oftenPhrase = parcel.readString()
        oftenSentence = parcel.readString()
        phoneticUk = parcel.readString()
        sentence = parcel.readString()
        sentenceFrequency = parcel.readString()
        shape = parcel.readString()
        star = parcel.readInt()
        syllable = parcel.readString()
        synonAndAnton = parcel.readString()
        ukSoundUrlMan = parcel.readString()
        ukSoundUrlWoman = parcel.readString()
        usSoundUrlMan = parcel.readString()
        usSoundUrlWoman = parcel.readString()
        usage = parcel.readString()
        word = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(phoneticUs)
        parcel.writeString(collocation)
        parcel.writeString(discriminate)
        parcel.writeString(expCN)
        parcel.writeString(expDetail)
        parcel.writeString(expDouble)
        parcel.writeString(expEN)
        parcel.writeString(frequency)
        parcel.writeInt(id)
        parcel.writeString(mistakes)
        parcel.writeString(oftenPhrase)
        parcel.writeString(oftenSentence)
        parcel.writeString(phoneticUk)
        parcel.writeString(sentence)
        parcel.writeString(sentenceFrequency)
        parcel.writeString(shape)
        parcel.writeInt(star)
        parcel.writeString(syllable)
        parcel.writeString(synonAndAnton)
        parcel.writeString(ukSoundUrlMan)
        parcel.writeString(ukSoundUrlWoman)
        parcel.writeString(usSoundUrlMan)
        parcel.writeString(usSoundUrlWoman)
        parcel.writeString(usage)
        parcel.writeString(word)
    }

    companion object CREATOR : Parcelable.Creator<DictCnWord> {
        override fun createFromParcel(parcel: Parcel): DictCnWord {
            return DictCnWord(parcel)
        }

        override fun newArray(size: Int): Array<DictCnWord?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME: String = "dict"
    }

}
