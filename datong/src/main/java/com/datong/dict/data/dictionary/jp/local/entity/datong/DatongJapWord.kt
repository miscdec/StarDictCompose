package com.datong.dict.data.dictionary.jp.local.entity.datong

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class DatongJapWord {
    var content: Content? = null
    @PrimaryKey var index: Index? = null


}
