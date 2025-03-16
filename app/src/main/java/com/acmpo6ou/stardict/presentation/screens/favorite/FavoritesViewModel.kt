package com.acmpo6ou.stardict.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.acmpo6ou.stardict.database.Favorites
import com.acmpo6ou.stardict.database.FavoritesDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import splitties.init.appCtx
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor() : ViewModel() {
    private val favoritesRepository: FavoritesRepository = FavoritesRepository()

    val favorites = favoritesRepository.getFavorites()


    fun addFavorite(word: String) {
        viewModelScope.launch {
            favoritesRepository.insert(Favorites(word = word, timestamp = System.currentTimeMillis()))
        }
    }

    fun removeFavorite(favorites: Favorites) {
        viewModelScope.launch {
            favoritesRepository.delete(favorites)
        }
    }

}

class FavoritesRepository{

    private val db = Room.databaseBuilder(
        appCtx,
        FavoritesDatabase::class.java, "favorites"
    ).build()

    private val dao = db.favoritesDao()

    fun getFavorites(): Flow<List<Favorites>> {
        return dao.getAll()
    }

    suspend fun insert(favorites: Favorites) {
        dao.insertAll(favorites)
    }

    suspend fun delete(favorites: Favorites) {
        dao.delete(favorites)
    }
}