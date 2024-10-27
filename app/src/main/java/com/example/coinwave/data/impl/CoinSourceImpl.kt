package com.example.coinwave.data.impl

import com.example.coinwave.common.data.SortParams


   fun SortParams.getOrderBy(): String {
    return when (this) {
      SortParams.MarketCap -> "marketCap"
      SortParams.Popular -> "24hVolume"
      SortParams.Gainers -> "change"
      SortParams.Losers -> "change"
      SortParams.Newest -> "listedAt"
    }
  }

   fun SortParams.getOrderDirection(): String {
    return when (this) {
      SortParams.MarketCap -> "desc"
      SortParams.Popular -> "desc"
      SortParams.Gainers -> "desc"
      SortParams.Losers -> "asc"
      SortParams.Newest -> "desc"
    }
  }