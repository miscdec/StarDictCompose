package com.acmpo6ou.stardict.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Index
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Entity(tableName = "favorites", indices = [Index(value = arrayOf("word", "timestamp"), unique = true)])
data  class Favorites(
    @PrimaryKey val id:Long = 0L,
    val word:String,
    val timestamp:Long
)

@Dao
interface FavoritesDao {
    @Query("SELECT * FROM favorites")
    fun getAll(): Flow<List<Favorites>>

    @Query("SELECT * FROM favorites WHERE word IN (:words)")
    fun loadAllByWords(words: List<String>): List<Favorites>

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun findById(id: Long): Favorites

    @Insert
    suspend fun insertAll(vararg favorites: Favorites)

    @Delete
    suspend fun delete(user: Favorites)
}

