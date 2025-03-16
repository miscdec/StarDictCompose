package com.acmpo6ou.stardict.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Favorites::class], version = 1)
abstract class FavoritesDatabase() : RoomDatabase(){
    abstract fun favoritesDao(): FavoritesDao
}

