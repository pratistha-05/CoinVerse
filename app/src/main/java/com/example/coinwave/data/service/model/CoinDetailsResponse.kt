package com.example.coinwave.data.service.model

import com.google.gson.annotations.SerializedName

data class CoinDetailsResponse(
  @SerializedName("data")
  val coinDetailResponseObject: CoinDetailResponse?
)

data class CoinDetailResponse(
  @SerializedName("coin")
  val coinDetailsData: CoinDetailsData?
)

data class CoinDetailsData(
  @SerializedName("uuid")
  val id: String?,
  @SerializedName("name")
  val name: String?,
  @SerializedName("symbol")
  val symbol: String?,
  @SerializedName("iconUrl")
  val imageUrl: String?,
  @SerializedName("price")
  val currentPrice: String?,
  @SerializedName("marketCap")
  val marketCap: String?,
  @SerializedName("rank")
  val marketCapRank: String?,
  @SerializedName("24hVolume")
  val volume24h: String?,
  @SerializedName("supply")
  val supply: Supply?,
  @SerializedName("allTimeHigh")
  val allTimeHigh: AllTimeHigh?,
  @SerializedName("listedAt")
  val listedAt: Long?
)

data class Supply(
  @SerializedName("circulating")
  val circulatingSupply: String?
)

data class AllTimeHigh(
  @SerializedName("price")
  val price: String?,
  @SerializedName("timestamp")
  val timestamp: Long?
)
