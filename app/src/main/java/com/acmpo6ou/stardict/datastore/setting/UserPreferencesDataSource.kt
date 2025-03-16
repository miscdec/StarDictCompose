package com.acmpo6ou.stardict.datastore.setting

import androidx.datastore.core.DataStore
import com.acmpo6ou.stardict.datastore.setting.DarkMode
import com.acmpo6ou.stardict.datastore.setting.UserPreferences
import com.acmpo6ou.stardict.datastore.setting.copy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


class UserPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData get() = userPreferences.data.map { it.toUserData() }


    suspend fun setDarkTheme(value: DarkMode) = withContext(Dispatchers.IO) {
        userPreferences.updateData {
            it.copy {
                darkMode = value
            }
        }
    }

    suspend fun setThemeColor(value: Int) = withContext(Dispatchers.IO) {
        userPreferences.updateData {
            it.copy {
                themeColor = value
            }
        }
    }

    suspend fun setFontSize(value:Float) = withContext(Dispatchers.IO){
        userPreferences.updateData {
            it.copy {
                fontSize = value
            }
        }
    }

    suspend fun setDictPath(value: List<String>) = withContext(Dispatchers.IO) {
        userPreferences.updateData {
            it.copy {
                dictPath = dictPath.copy {
                    path.clear()
                    path.addAll(value)
                }
            }
        }
    }

//    suspend fun setUsingDynamicColor(value: Boolean) = withContext(Dispatchers.IO){
//        userPreferences.updateData {
//            it.copy {
//                usingDynamicColor = value
//            }
//        }
//    }
//
//    suspend fun setDownloadPath(value: String) = withContext(Dispatchers.IO) {
//        userPreferences.updateData {
//            it.copy {
//                downloadPath = value
//            }
//        }
//    }
//
//    suspend fun setDeleteZipFile(value: Boolean) = withContext(Dispatchers.IO) {
//        userPreferences.updateData {
//            it.copy {
//                deleteZipFile = value
//            }
//        }
//    }

    suspend fun setEnableNavigationAnimation(value: Boolean) = withContext(Dispatchers.IO) {
        userPreferences.updateData {
            it.copy {
                enableNavigationAnimation = value
            }
        }
    }
}