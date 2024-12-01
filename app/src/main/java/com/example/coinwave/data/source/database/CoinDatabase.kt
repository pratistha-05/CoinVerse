package com.example.coinwave.data.source.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.coinwave.data.source.database.dao.FavouriteDao

@Database(entities = [Coin::class], version = 1)
abstract class CoinDatabase : RoomDatabase() {
  abstract fun favouriteDao(): FavouriteDao
}