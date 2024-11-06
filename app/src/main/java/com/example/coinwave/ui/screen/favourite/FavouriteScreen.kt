package com.example.coinwave.ui.screen.favourite

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.coinwave.R
import com.example.coinwave.ui.screen.favourite.viewmodel.FavouriteViewmodel

@Composable
fun FavouriteScreen(  navController: NavController,
  viewModel: FavouriteViewmodel= hiltViewModel()
) {
  val favouriteCoins by viewModel.favouriteList.collectAsState()

  if (favouriteCoins.isEmpty()) {
    Column(
      modifier = Modifier.fillMaxSize().background(Color.Black),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Image(
        painter = painterResource(id = R.drawable.favourite_empty_state),
        contentDescription = "Empty State",
        modifier = Modifier.size(150.dp)
      )
      Text(
        text = "No favourites found",
        color = Color.White,
        fontSize = 18.sp,
      )
    }  } else {
    LazyColumn {

    }
  }
}

