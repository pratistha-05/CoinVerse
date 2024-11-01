package com.example.coinwave.di

import android.content.Context
import androidx.room.Room
import com.example.coinwave.common.Constants.COIN_DATABASE_NAME
import com.example.coinwave.data.service.model.database.CoinDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

  @Provides
  @Singleton
  fun provideCoinDatabase(@ApplicationContext context: Context): CoinDatabase {
    return Room.databaseBuilder(
      context.applicationContext,
      CoinDatabase::class.java,
      COIN_DATABASE_NAME
    ).build()
  }
}