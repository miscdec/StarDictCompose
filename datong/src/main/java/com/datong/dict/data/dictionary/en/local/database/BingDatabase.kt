package com.datong.dict.data.dictionary.en.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.en.local.dao.BingDao
import com.datong.dict.data.dictionary.en.local.entity.BingWord

@Database(entities = [BingWord::class], version = 1)
abstract class BingDatabase : RoomDatabase(){
    abstract fun bingDao(): BingDao
}
