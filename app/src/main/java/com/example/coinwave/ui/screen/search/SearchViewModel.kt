package com.example.coinwave.ui.screen.search

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinwave.common.Result
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
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

  init {
    viewModelScope.launch {
      _inputText
        .debounce(500L)
        .filter { it.isNotBlank() }
        .distinctUntilChanged()
        .collectLatest { query ->
          performSearch(query)
        }
    }
  }

  fun onInputChange(query: String) {
    _inputText.value = query
  }

  private suspend fun performSearch(query: String) {
    viewModelScope.launch {
      val coinsResult: Result<List<CoinItem>> = repository.searchCoins(query)

      when (coinsResult) {
        is Result.Success -> {
          _coinList.emit(coinsResult.data)
        }
        is Result.Error -> {
          // Handle error (e.g., log it or show a UI message)
        }
      }
    }
  }


  fun clearSearchResults() {
    _coinList.value = emptyList()
  }
}
