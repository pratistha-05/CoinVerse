package com.example.coinwave.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.coinwave.data.service.model.BottomNav

@Composable
fun NavigationGraph(navController: NavHostController) {
  NavHost(navController, startDestination = "") {
    composable(BottomNav.Home.route) {
//      onBottomBarVisibilityChanged(true)
      Screen1()
    }
    composable(BottomNav.Search.route) {
//      onBottomBarVisibilityChanged(true)
      Screen2()
    }
    composable(BottomNav.Profile.route) {
//      onBottomBarVisibilityChanged(true)
      Screen3()
    }
  }
}