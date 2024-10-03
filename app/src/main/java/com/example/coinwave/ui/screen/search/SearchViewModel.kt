package com.example.coinwave.ui.screen.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinwave.common.Result
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val repository: CoinRepository
) : ViewModel() {

  private val _inputText: MutableStateFlow<String> = MutableStateFlow("")
  val inputText: StateFlow<String> = _inputText

  private val _coinList = MutableStateFlow<List<CoinItem>>(emptyList())
  val coinList: StateFlow<List<CoinItem>> = _coinList

  fun searchCoins(query: String) {
    _inputText.value = query

    if (query.isBlank()) {
      _coinList.value = emptyList()
      return
    }
    viewModelScope.launch {
      val coinsResult: Result<List<CoinItem>> = repository.searchCoins(query)

      when (coinsResult) {
        is Result.Success -> {
          _coinList.emit(coinsResult.data)
        }
        is Result.Error -> {
          // Handle error (e.g., log it or show a UI message)
          // For simplicity, we'll just print the error message here
        }
      }
    }
  }
}
