package com.example.coinwave.di

import com.example.coinwave.data.service.model.database.CoinDatabase
import com.example.coinwave.data.service.model.database.FavouriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

  @Provides
  fun provideFavouriteDao(appDatabase: CoinDatabase): FavouriteDao {
    return appDatabase.favouriteDao()
  }
}