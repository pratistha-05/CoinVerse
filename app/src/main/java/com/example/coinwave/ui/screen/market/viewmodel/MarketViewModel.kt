package com.example.coinwave.ui.screen.market.viewmodel

import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
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
import kotlinx.coroutines.isActive

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

  fun startPeriodicDataRefresh(lifecycleOwner: LifecycleOwner) {
    lifecycleOwner.lifecycleScope.launch {
      lifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
        while (isActive) {
          getCoinsListData(_sortParamsFlow.value)
          delay(12000)
        }
      }
    }
  }

  fun getCoinsListData(sortParams: SortParams) {
    viewModelScope.launch {
      val coinsResult: Result<List<CoinItem>> = repository.getCoins(sortParams)

      when (coinsResult) {
        is Result.Success -> {
          _coinList.emit(coinsResult.data)
        }
        is Result.Error -> {
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
