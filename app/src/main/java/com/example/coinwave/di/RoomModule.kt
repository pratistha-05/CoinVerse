package com.example.coinwave.di

import android.content.Context
import androidx.room.Room
import com.example.coinwave.data.source.database.CoinDatabase
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
      "favourite_data_table"
    ).build()
  }
}