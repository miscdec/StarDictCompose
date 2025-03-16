package com.datong.dict.data.dictionary.en.local.database

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.en.local.dao.CibaDao
import com.datong.dict.data.dictionary.en.local.entity.CibaWord

@Database(entities = [CibaWord::class], version = 1)
abstract class CibaDatabase: RoomDatabase() {

    abstract fun cibaDao(): CibaDao

}