package com.acmpo6ou.stardict.presentation.screens.dicts

import android.net.Uri


sealed interface DictStatusUiState {
    data object Idle : DictStatusUiState
    //    data class Idle(val result: List<RouteList>) : SearchStatusUiState
    data class Success(val result: List<StarDictInfo>) : DictStatusUiState
    data class Loading(val process:String) : DictStatusUiState
    data class Error(val exception: Exception) : DictStatusUiState
}

sealed class DictUIIntent {
    data class LoadDictionariesFromUris(val uris: Uri) : DictUIIntent()

    data object GetLoadedDictionaries : DictUIIntent()

    data class RemoveLoadedDictionaries(val it: StarDictInfo): DictUIIntent()
}