package com.example.coinwave.ui.screen.market.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import com.example.coinwave.common.Result
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.repository.CoinRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MarketViewModel @Inject constructor(private val repository: CoinRepository): ViewModel(
) {

  var coinList= mutableListOf<CoinItem>()

fun getCoinsListData(){
  viewModelScope.launch{

    val coinsResult: Result<List<CoinItem>> = repository.getCoins()

    when (coinsResult) {
      is Result.Success -> {
        coinList=coinsResult.data.toMutableList()
//        _uiState.update {
//          it.copy(
//            coins = coinsResult.data.toMutableList(),
//            isLoading = false
//          )
//        }
      }

      is Result.Error -> {
//        _uiState.update {
//          val errorMessages = it.errorMessageIds + R.string.error_local_coins
//
//          it.copy(
//            errorMessageIds = errorMessages,
//            isLoading = false
//          )
//        }
      }
    }
  }
}
}