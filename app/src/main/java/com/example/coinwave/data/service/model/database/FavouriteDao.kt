package com.example.coinwave.data.service.model.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.coinwave.data.service.model.CoinItem
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouriteDao {
  @Query("SELECT * FROM favourite_data_table")
  fun getAllFavourites(): Flow<List<Coin>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insertFavourite(coin: Coin)

  @Delete
  suspend fun deleteFavourite(coin: Coin)
}