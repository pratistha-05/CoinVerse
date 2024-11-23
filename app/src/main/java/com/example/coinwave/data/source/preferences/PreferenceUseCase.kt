package com.example.coinwave.data.source.preferences

import com.example.coinwave.common.data.SortParams
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PreferenceUseCase @Inject constructor(
  private val preferencesRepository: PreferencesRepository
) {

  fun getSortParams(): Flow<SortParams> {
    return preferencesRepository.coinSortFlow
  }

   suspend fun updateMarketCoinSort(coinSort: SortParams) {
    preferencesRepository.updateCoinSortParams(coinSort)
  }
}