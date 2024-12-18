package com.example.coinwave.data.repository

import com.example.coinwave.common.Result
import com.example.coinwave.data.service.model.CoinDetailsResponse
import com.example.coinwave.data.source.network.ApiDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CoinDetailsRepository @Inject constructor(
  private val apiDataSource: ApiDataSource
) {

  suspend fun getCoinDetails(coinId: String): Flow<Result<CoinDetailsResponse>> =
    flow {
      val response = apiDataSource.getCoinDetails(coinId)

      if (response.isSuccessful) {
        val body = response.body()
        if (body != null) {
          emit(Result.Success(body)) 
        } else {
          emit(Result.Error("Response body is null"))
        }
      } else {
        emit(Result.Error("Unable to fetch coin details: ${response.message()}"))
      }
    }.catch { e ->
      emit(Result.Error("Exception occurred: ${e.message}"))
    }.flowOn(Dispatchers.IO) // Use IO dispatcher for network operations

}

