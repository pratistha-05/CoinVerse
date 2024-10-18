package com.example.coinwave.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import com.example.coinwave.common.data.MarketPreferences
import com.example.coinwave.common.source.preferences.MarketPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn
object PreferencesModule {
  @Provides
  @Singleton
  fun provideMarketPreferences(
    @ApplicationContext appContext: Context
  ): DataStore<MarketPreferences> {
    return DataStoreFactory.create(
      serializer = MarketPreferencesSerializer,
      produceFile = { appContext.dataStoreFile("market_preferences.pb") },
      corruptionHandler = ReplaceFileCorruptionHandler(
        produceNewData = { MarketPreferences() }
      )
    )
  }
}