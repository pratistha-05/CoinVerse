package com.example.coinwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.coinwave.ui.BottomBar
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
        val navController = rememberNavController()

        Scaffold(
          bottomBar = {
            BottomBar(navController = navController)
          }
        ) { paddingValues ->
          Box() {
            NavigationGraph(navController = navController)
          }
        }
      }
    }
  }
}
