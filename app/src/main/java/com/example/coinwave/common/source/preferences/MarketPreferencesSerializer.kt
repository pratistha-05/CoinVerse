package com.example.coinwave.common.source.preferences

import com.example.coinwave.common.data.MarketPreferences

object MarketPreferencesSerializer : BasePreferencesSerializer<MarketPreferences>(
  defaultInstance = { MarketPreferences() },
  serializer = MarketPreferences.serializer()
)
