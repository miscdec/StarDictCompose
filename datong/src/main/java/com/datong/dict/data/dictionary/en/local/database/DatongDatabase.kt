package com.datong.dict.data.dictionary.en.local.database

import android.content.Context
import android.text.TextUtils
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.en.local.dao.DatongDao
import com.datong.dict.data.dictionary.en.local.dao.SearchDao
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord
import com.datong.dict.utils.ConvertUtil
import com.datong.dict.utils.PathUtil

@Database(entities = [DatongEnWord::class], version = 1)
abstract class DatongDatabase : RoomDatabase() {

    abstract fun datongDao(): DatongDao

    abstract fun searchDao(): SearchDao

    companion object {
        private var INSTANCE: DatongDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): DatongDatabase? {
            if (INSTANCE == null) {
                newInstance(context)
            }
            return INSTANCE
        }

        fun newInstance(context: Context): DatongDatabase {
            val datongDatabase = INSTANCE
            datongDatabase?.close()
            val sb = StringBuilder().also {
                it.append(PathUtil.PATH_DB)
                it.append(if (!TextUtils.isEmpty(ConvertUtil.MAP_DATONG_EN)) "/dict_en_datong.db" else "/dict_en_datong_encrypted.db")
            }
            val datongDatabase2 = databaseBuilder(
                context.applicationContext,
                DatongDatabase::class.java,
                sb.toString()
            ).setJournalMode(JournalMode.TRUNCATE).build()
            INSTANCE = datongDatabase2
            return datongDatabase2
        }
    }
}
