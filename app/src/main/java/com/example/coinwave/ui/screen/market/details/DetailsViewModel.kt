package com.example.coinwave.ui.screen.market.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.coinwave.common.Constants.PARAM_COIN_ID
import com.example.coinwave.data.source.network.ApiDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
  private val repository: ApiDataSource,
  savedStateHandle: SavedStateHandle
) : ViewModel() {

  private val coinId = savedStateHandle.get<String>(PARAM_COIN_ID)

}