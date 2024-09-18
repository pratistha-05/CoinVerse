package com.example.coinwave.ui.screen.market.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.coinwave.common.Result
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class MarketViewModel @Inject constructor(
  private val repository: CoinRepository
) : ViewModel() {

  private val _coinList = MutableStateFlow<List<CoinItem>>(emptyList())
  val coinList = _coinList

  fun getCoinsListData() {
    viewModelScope.launch {
      val coinsResult: Result<List<CoinItem>> = repository.getCoins()

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
}
