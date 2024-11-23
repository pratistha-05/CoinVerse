package com.example.coinwave.data.service.model.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourite_data_table")
data class Coin(
  @PrimaryKey
  val id: String,
  val name: String,
  val symbol: String,
  val imageUrl: String,
  val currentPrice: String,
  val priceChangePercentage24h: String
)
