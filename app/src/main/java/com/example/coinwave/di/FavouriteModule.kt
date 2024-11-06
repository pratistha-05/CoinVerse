package com.example.coinwave.di

import com.example.coinwave.data.service.model.database.CoinDatabase
import com.example.coinwave.data.service.model.database.FavouriteDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FavouriteModule {

  @Provides
  fun provideFavouriteDao(appDatabase: CoinDatabase): FavouriteDao {
    return appDatabase.favouriteDao()
  }
}