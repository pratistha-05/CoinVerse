package com.example.coinwave.common.data

import androidx.annotation.StringRes
import com.example.coinwave.R

enum class SortParams(@StringRes val nameId: Int) {
  MarketCap(R.string.market_coin_sort_market_cap),
  Popular(R.string.market_coin_sort_popular),
  Gainers(R.string.market_coin_sort_gainers),
  Losers(R.string.market_coin_sort_losers),
  Newest(R.string.market_coin_sort_newest),
}