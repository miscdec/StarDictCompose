package com.datong.dict.data.dictionary.jp.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.datong.dict.data.dictionary.jp.local.dao.DatongJapContentDao
import com.datong.dict.data.dictionary.jp.local.dao.DatongJapIndexDao
import com.datong.dict.data.dictionary.jp.local.entity.datong.Content
import com.datong.dict.data.dictionary.jp.local.entity.datong.Index
import com.datong.dict.utils.PathUtil

@Database(entities = [Index::class,Content::class], version = 1)
/* loaded from: classes.dex */
abstract class DatongJapDatabase : RoomDatabase() {

    abstract fun contentDao(): DatongJapContentDao

    abstract fun indexDao(): DatongJapIndexDao

    companion object {
        private var INSTANCE: DatongJapDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): DatongJapDatabase {
            val datongJapDatabase = INSTANCE
            return datongJapDatabase ?: newInstance(context)
        }

        fun newInstance(context: Context): DatongJapDatabase {
            val datongJapDatabase = INSTANCE
            datongJapDatabase?.close()
            val datongJapDatabase2 = databaseBuilder(
                context.applicationContext,
                DatongJapDatabase::class.java,
                PathUtil.PATH_DB + "/dict_jp_datong_encrypted.db"
            ).setJournalMode(JournalMode.TRUNCATE).build()
            INSTANCE = datongJapDatabase2
            return datongJapDatabase2
        }
    }
}
