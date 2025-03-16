package com.datong.dict.data.dictionary.en.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.en.local.dao.DictCnDao
import com.datong.dict.data.dictionary.en.local.entity.DictCnWord
import com.datong.dict.utils.PathUtil

@Database(entities = [DictCnWord::class], version = 1)
/* loaded from: classes.dex */
abstract class DictCnDatabase : RoomDatabase() {


    abstract fun dictCnDao(): DictCnDao

    companion object {
        private var INSTANCE: DictCnDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): DictCnDatabase? {
            if (INSTANCE == null) {
                newInstance(context)
            }
            return INSTANCE
        }

        fun newInstance(context: Context): DictCnDatabase {
            val dictCnDatabase = INSTANCE
            dictCnDatabase?.close()
            val applicationContext = context.applicationContext
            val dictCnDatabase2 = databaseBuilder(
                applicationContext,
                DictCnDatabase::class.java,
                PathUtil.PATH_DB + "/dict_en_dictCn_encrypted.db"
            ).setJournalMode(JournalMode.TRUNCATE).build()
            INSTANCE = dictCnDatabase2
            return dictCnDatabase2
        }
    }
}
