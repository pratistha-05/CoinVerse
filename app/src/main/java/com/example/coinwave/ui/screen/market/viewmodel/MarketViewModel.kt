package com.example.coinwave.ui.screen.market.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.coinwave.common.Result
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.data.source.PreferenceUseCase
import com.example.coinwave.ui.screen.market.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.StateFlow

@HiltViewModel
class MarketViewModel @Inject constructor(
  private val repository: CoinRepository,
  private val preferenceUseCase: PreferenceUseCase
) : ViewModel() {

  private val _coinList = MutableStateFlow<List<CoinItem>>(emptyList())
  val coinList = _coinList

  private val _sortParamsFlow = MutableStateFlow<SortParams>(SortParams.MarketCap)
  val sortParamsFlow: StateFlow<SortParams> = _sortParamsFlow

  init {
    viewModelScope.launch {
      preferenceUseCase.getSortParams().collect { sortParams ->
        _sortParamsFlow.emit(sortParams)
        getCoinsListData(sortParams)
      }
    }
  }

  fun startDataRefresh() {
    viewModelScope.launch {
      while (true) {
        getCoinsListData(sortParamsFlow.value)
        delay(5000L) // Delay for 5 seconds
      }
    }
  }

  //TODO: Add sort param here
  fun getCoinsListData(sortParams: SortParams) {
    viewModelScope.launch {
      val coinsResult: Result<List<CoinItem>> = repository.getCoins(sortParams)

      when (coinsResult) {
        is Result.Success -> {
          _coinList.emit(coinsResult.data)
        }
        is Result.Error -> {
          // Handle error
        }
      }
    }
  }

  fun updateCoinSortParams(sortParams: SortParams) {
    viewModelScope.launch {
      preferenceUseCase.updateMarketCoinSort(sortParams)
    }
  }
}
