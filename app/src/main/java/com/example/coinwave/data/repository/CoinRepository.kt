package com.example.coinwave.data.repository

import com.example.coinwave.common.Result
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.data.source.network.ApiDataSource
import javax.inject.Inject

class  CoinRepository @Inject constructor(
  private val apiDataSource: ApiDataSource
) {

  suspend fun getCoins(coinSort: SortParams): Result<List<CoinItem>> {
    return try {
      val response = apiDataSource.getCoins(coinSort)

      if (response.isSuccessful) {
        val coins = response.body()?.coinsData?.coinList?.filterNotNull() ?: emptyList()
        Result.Success(coins)
      } else {
        Result.Error("Error: ${response.message()}")
      }
    } catch (e: Exception) {
      Result.Error("Exception: ${e.message}")
    }
  }
}