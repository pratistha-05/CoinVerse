package com.example.coinwave.ui.screen.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinwave.data.service.model.database.Coin
import com.example.coinwave.ui.screen.favourite.repository.FavouriteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewmodel @Inject constructor( private val favouriteRepository: FavouriteRepository) :
  ViewModel() {

  val favouriteList = MutableStateFlow<List<Coin>>(emptyList())

  fun getFavouriteList() {
    viewModelScope.launch {
      favouriteRepository.getAllFavourites().collect { coins ->
          favouriteList.value = coins
      }
    }
  }
}