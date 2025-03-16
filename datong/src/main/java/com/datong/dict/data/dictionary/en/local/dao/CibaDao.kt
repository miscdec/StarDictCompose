package com.datong.dict.data.dictionary.en.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.en.local.entity.CibaWord

@Dao
interface CibaDao {

    @Query("SELECT * FROM dict WHERE word = :keywords")
    fun getWord(keywords: String): CibaWord
}
