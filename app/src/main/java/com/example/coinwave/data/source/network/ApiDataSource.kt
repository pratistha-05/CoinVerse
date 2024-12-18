package com.example.coinwave.data.source.network
import com.example.coinwave.data.service.CoinService
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.common.Result
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.impl.getOrderBy
import com.example.coinwave.data.service.model.CoinDetailsResponse
import com.example.coinwave.data.service.model.CoinListResponse
import retrofit2.Response
import javax.inject.Inject

class ApiDataSource @Inject constructor(
  private val apiService: CoinService
) {
  suspend fun getCoins(coinSort: SortParams): Response<CoinListResponse> {
    return apiService.getCoins(orderBy = coinSort.getOrderBy())
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

  suspend fun getCoinDetails(id: String): Response<CoinDetailsResponse> {
    return apiService.getCoinDetails(id)
  }
}
