package com.example.coinwave.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.coinwave.data.service.model.BottomNav
import com.example.coinwave.data.service.model.Screen
import com.example.coinwave.ui.screen.favourite.FavouriteScreen
import com.example.coinwave.ui.screen.market.MarketScreen
import com.example.coinwave.ui.screen.market.details.DetailsScreen
import com.example.coinwave.ui.screen.search.SearchScreen

@Composable
fun NavigationGraph(navController: NavHostController) {
  NavHost(navController, startDestination = BottomNav.Home.route) {
    composable(BottomNav.Home.route) {
      MarketScreen(navController)
    }
    composable("search") {
      SearchScreen( navController)
    }
    composable(BottomNav.Favourite.route) {
      FavouriteScreen(navController)
    }
    composable(
      route = Screen.Details.route+"/{coinId}",
      arguments = listOf(navArgument("coinId") { type = NavType.StringType })
    ) { backStackEntry ->
//      val coinId = backStackEntry.arguments?.getString("coinId") ?: ""
      DetailsScreen()
    }
  }
}