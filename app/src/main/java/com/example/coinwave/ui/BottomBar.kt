package com.example.coinwave.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.coinwave.data.service.model.BottomNav

@Composable
fun BottomBar(
  navController: NavHostController) {
  val screens = listOf(
    BottomNav.Home, BottomNav.Profile
  )
  NavigationBar(
    modifier = Modifier,
    containerColor = Color.Black,
  ) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    screens.forEach { screen ->
      NavigationBarItem(
        label = {
          Text(text = screen.label)
        },
        icon = {
          Icon(imageVector = screen.icon, contentDescription = "")
        },
        selected = currentRoute == screen.route,
        onClick = {
          navController.navigate(screen.route) {
            popUpTo(navController.graph.findStartDestination().id) {
              saveState = true
            }
            launchSingleTop = true
            restoreState = true
          }
        },
        colors = NavigationBarItemDefaults.colors(
          selectedTextColor = Color.White,
          selectedIconColor = Color.White,
          unselectedIconColor = Color.Gray,
          indicatorColor = Color.Black
        ),
      )
    }
  }
}
