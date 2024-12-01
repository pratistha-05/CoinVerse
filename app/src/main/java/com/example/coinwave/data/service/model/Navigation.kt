package com.example.coinwave.data.service.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.StackedLineChart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNav(val route: String, val icon: ImageVector, val label: String) {
  object Home : BottomNav("Home", Icons.Filled.StackedLineChart, "Market")
  object Favourite : BottomNav("Favourite", Icons.Default.Favorite, "Favourite")
}

sealed class Screen(val route: String) {
  data object Details : Screen("details")
}
