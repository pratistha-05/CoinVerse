package com.example.coinwave.common.data

import kotlinx.serialization.Serializable

@Serializable
data class MarketPreferences(
  val coinSort: SortParams = SortParams.MarketCap
)
