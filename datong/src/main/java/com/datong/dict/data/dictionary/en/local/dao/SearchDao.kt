package com.datong.dict.data.dictionary.en.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.datong.dict.data.dictionary.en.local.entity.DatongEnWord

@Dao
/* loaded from: classes.dex */
interface SearchDao {
    @Query("SELECT * FROM dict WHERE word LIKE :keyword LIMIT 50")
    fun searchByWildcard(keyword: String): List<DatongEnWord>

    @Query("SELECT * FROM dict WHERE expCN LIKE :keywords ORDER BY wordLevel DESC LIMIT 20 ")
    fun searchWordByCN(keywords: String): List<DatongEnWord>


    @Query("SELECT * FROM dict WHERE (word >= :lowerMinKeywords AND word < :lowerMaxKeywords) OR (word >= :upperMinKeywords AND word < :upperMaxKeywords) LIMIT 20 ")
    fun searchWordByEN(
        lowerMinKeywords: String?,
        lowerMaxKeywords: String?,
        upperMinKeywords: String?,
        upperMaxKeywords: String?
    ): List<DatongEnWord>
}
