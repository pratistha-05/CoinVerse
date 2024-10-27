package com.example.coinwave.ui.screen.market.repository
import com.example.coinwave.data.service.CoinService
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.data.service.model.CoinListResponse
import retrofit2.Response
import com.example.coinwave.common.Result
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.impl.getOrderBy
import com.example.coinwave.networkModule.ApiClient
import javax.inject.Inject

class CoinRepository @Inject constructor(
  private val apiService: CoinService
) {
  suspend fun getCoins(coinSort: SortParams): Result<List<CoinItem>> {
    return try {
      val response = apiService.getCoins(
        orderBy = coinSort.getOrderBy()
      )

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

  suspend fun searchCoins(query: String): Result<List<CoinItem>> {
    return try {
      val response = apiService.searchCoins(query)

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
