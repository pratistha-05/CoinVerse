package com.example.coinwave.data.source

import androidx.datastore.core.DataStore
import com.example.coinwave.common.data.MarketPreferences
import com.example.coinwave.common.data.SortParams
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

import kotlinx.coroutines.flow.map

class PreferencesRepository @Inject constructor(
  private val marketPreferencesDataStore: DataStore<MarketPreferences>
) {

  val coinSortFlow: Flow<SortParams> = marketPreferencesDataStore.data
    .catch { exception ->
      if (exception is IOException) {
        emit(MarketPreferences())
      } else {
        throw exception
      }
    }
    .map { preferences ->
      preferences.coinSort
    }

  suspend fun updateCoinSortParams(sortParams: SortParams) {
    marketPreferencesDataStore.updateData { currentPreferences ->
      currentPreferences.copy(coinSort = sortParams)
    }
  }
}
