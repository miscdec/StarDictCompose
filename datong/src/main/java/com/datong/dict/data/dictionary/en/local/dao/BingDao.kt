package com.datong.dict.data.dictionary.en.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.en.local.entity.BingWord

@Dao
interface BingDao {

    @Query("SELECT * FROM dict WHERE word = :keywords")
    fun getWord(keywords: String): BingWord
}

