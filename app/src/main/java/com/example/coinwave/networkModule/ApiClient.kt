package com.example.coinwave.networkModule

import com.example.coinwave.common.Constants.BASE_URL
import com.example.coinwave.data.service.CoinService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

  private val retrofit: Retrofit by lazy {
    Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  val coinService: CoinService by lazy {
    retrofit.create(CoinService::class.java)
  }
}