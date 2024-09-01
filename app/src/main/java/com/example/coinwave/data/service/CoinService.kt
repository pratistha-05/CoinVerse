package com.example.coinwave.data.service

import com.example.coinwave.data.service.model.CoinListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinService {
  @GET("coins")
  suspend fun getCoins(
    @Query("referenceCurrencyUuid") currencyUUID: String = "yhjMzLPhuIDl",
    @Query("orderBy") orderBy: String = "marketCap",
    @Query("timePeriod") timePeriod: String = "24h",
    @Query("orderDirection") orderDirection: String = "desc",
    @Query("limit") limit: String = "100"
  ): Response<CoinListResponse>

}