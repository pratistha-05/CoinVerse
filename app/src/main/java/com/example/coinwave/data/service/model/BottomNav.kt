package com.example.coinwave.data.service.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNav(val route: String, val icon: ImageVector, val label: String) {
  object Home : BottomNav("Home", Icons.Default.Home, "Home")
//  object Search : BottomNav("Search", Icons.Default.Search, "Search")
  object Profile : BottomNav("Favourite", Icons.Default.Favorite, "Favourite")
}