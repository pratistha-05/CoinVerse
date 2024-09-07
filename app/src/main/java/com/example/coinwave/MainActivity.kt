package com.example.coinwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coinwave.data.service.model.BottomNav
import com.example.coinwave.ui.navigation.NavigationGraph
import com.example.coinwave.ui.theme.CoinWaveTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalMaterial3Api::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      CoinWaveTheme {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

        Scaffold(
          modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

          topBar = {
            TopAppBar(
              title = {
                Text(
                  "Good morning", maxLines = 1, overflow = TextOverflow.Ellipsis
                )
              },
              navigationIcon = {
                IconButton(onClick = { /* do something */ }) {
                  Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Localized description"
                  )
                }
              },
              actions = {
                IconButton(onClick = { /* do something */ }) {
                  Icon(
                    imageVector = Icons.Filled.Menu, contentDescription = "Localized description"
                  )
                }
              },
              scrollBehavior = scrollBehavior,
            )
          },
          bottomBar = {
            BottomBar(navController = rememberNavController() )
          }
        ) { innerPadding ->
          NavigationGraph(navController = rememberNavController())
        }
      }
    }
  }

  @Composable
  fun BottomBar(
    navController: NavHostController
  ) {
    val screens = listOf(
      BottomNav.Home, BottomNav.Search, BottomNav.Profile
    )

    NavigationBar(
      modifier = Modifier,
      containerColor = Color.LightGray,
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
            unselectedTextColor = Color.Gray,
            selectedTextColor = Color.Black,
            selectedIconColor = Color.Black,
            unselectedIconColor = Color.Black,
            indicatorColor = Color.White
          ),
        )
      }
    }
  }
}