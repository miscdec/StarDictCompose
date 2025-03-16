package com.datong.dict.data.dictionary.en.local.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CibaWord.TABLE_NAME)
/* loaded from: classes.dex */
class CibaWord() : Parcelable {
    @JvmField
    var analysis: String? = null
    @JvmField
    var antonym: String? = null
    @JvmField
    var authority: String? = null
    @JvmField
    var bilingual: String? = null
    @JvmField
    var cet4: String? = null
    @JvmField
    var cet6: String? = null
    @JvmField
    var collins: String? = null
    @JvmField
    var expCN: String? = null
    @JvmField
    var expEn2Cn: String? = null
    @JvmField
    var expEn2En: String? = null

    @PrimaryKey
    @JvmField
    var id: Int = 0

    var phoneticUk: String? = null
    var phoneticUs: String? = null
    var phrase: String? = null
    var shape: String? = null
    var soundUkUrl: String? = null
    var soundUsUrl: String? = null
    var synonym: String? = null
    var word: String? = null
    var wordRoot: String? = null

    constructor(parcel: Parcel) : this() {
        analysis = parcel.readString()
        antonym = parcel.readString()
        authority = parcel.readString()
        bilingual = parcel.readString()
        cet4 = parcel.readString()
        cet6 = parcel.readString()
        collins = parcel.readString()
        expCN = parcel.readString()
        expEn2Cn = parcel.readString()
        expEn2En = parcel.readString()
        id = parcel.readInt()
        phoneticUk = parcel.readString()
        phoneticUs = parcel.readString()
        phrase = parcel.readString()
        shape = parcel.readString()
        soundUkUrl = parcel.readString()
        soundUsUrl = parcel.readString()
        synonym = parcel.readString()
        word = parcel.readString()
        wordRoot = parcel.readString()
    }

    // android.os.Parcelable
    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(analysis)
        parcel.writeString(antonym)
        parcel.writeString(authority)
        parcel.writeString(bilingual)
        parcel.writeString(cet4)
        parcel.writeString(cet6)
        parcel.writeString(collins)
        parcel.writeString(expCN)
        parcel.writeString(expEn2Cn)
        parcel.writeString(expEn2En)
        parcel.writeInt(id)
        parcel.writeString(phoneticUk)
        parcel.writeString(phoneticUs)
        parcel.writeString(phrase)
        parcel.writeString(shape)
        parcel.writeString(soundUkUrl)
        parcel.writeString(soundUsUrl)
        parcel.writeString(synonym)
        parcel.writeString(word)
        parcel.writeString(wordRoot)
    }

    companion object CREATOR : Parcelable.Creator<CibaWord> {
        override fun createFromParcel(parcel: Parcel): CibaWord {
            return CibaWord(parcel)
        }

        override fun newArray(size: Int): Array<CibaWord?> {
            return arrayOfNulls(size)
        }

        const val TABLE_NAME: String = "dict"
    }
}
