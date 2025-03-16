package com.acmpo6ou.stardict.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.acmpo6ou.stardict.datastore.setting.UserPreferences
import com.acmpo6ou.stardict.datastore.setting.UserPreferencesSerializer
import com.acmpo6ou.stardict.di.ApplicationScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer,
        @ApplicationScope applicationScope: CoroutineScope
    ): DataStore<UserPreferences> {
        return DataStoreFactory.create(
            serializer = userPreferencesSerializer,
            scope = applicationScope,
        ) {
            context.dataStoreFile("user_preferences.pb")
        }
    }
}