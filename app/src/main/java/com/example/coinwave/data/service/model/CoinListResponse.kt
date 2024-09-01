package com.example.coinwave.data.service.model

import com.google.gson.annotations.SerializedName

data class CoinListResponse(
  @SerializedName("data")
  val coinsData: CoinsData?
)

data class CoinsData(
  @SerializedName("coins")
  val coinList: List<CoinItem?>?
)

data class CoinItem(
  @SerializedName("uuid")
  val id: String?,
  @SerializedName("symbol")
  val symbol: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("iconUrl")
  val imageUrl: String?,
  @SerializedName("price")
  val currentPrice: String?,
  @SerializedName("change")
  val priceChangePercentage24h: String?
)