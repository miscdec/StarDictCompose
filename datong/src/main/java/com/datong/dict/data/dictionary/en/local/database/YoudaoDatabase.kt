package com.datong.dict.data.dictionary.en.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.en.local.dao.YoudaoDao
import com.datong.dict.data.dictionary.en.local.entity.YoudaoWord
import com.datong.dict.utils.PathUtil


@Database(entities = [YoudaoWord::class], version = 1)
/* loaded from: classes.dex */
abstract class YoudaoDatabase : RoomDatabase() {
    abstract fun youdaoDao(): YoudaoDao

    companion object {
        private var INSTANCE: YoudaoDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): YoudaoDatabase? {
            if (INSTANCE == null) {
                newInstance(context)
            }
            return INSTANCE
        }

        fun newInstance(context: Context): YoudaoDatabase {
            val youdaoDatabase = INSTANCE
            youdaoDatabase?.close()
            val applicationContext = context.applicationContext
            val youdaoDatabase2 = databaseBuilder(
                applicationContext,
                YoudaoDatabase::class.java,
                PathUtil.PATH_DB + "/dict_en_youdao_encrypted.db"
            ).setJournalMode(JournalMode.TRUNCATE).build()
            INSTANCE = youdaoDatabase2
            return youdaoDatabase2
        }
    }
}
