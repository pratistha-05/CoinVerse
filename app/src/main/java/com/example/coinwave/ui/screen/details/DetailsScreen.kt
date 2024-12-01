package com.example.coinwave.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.coinwave.ui.screen.market.MarketList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
  viewModel: DetailsViewModel = hiltViewModel()
) {

  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      TopAppBar(title = {}, actions = {
        IconButton(onClick = {}) {
          Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Search",
            tint = Color.White
          )
        }
      },
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
        containerColor = Color.Black,
        titleContentColor = Color.White,
        navigationIconContentColor = Color.White
      ))
    },
    containerColor = Color.Black,
    contentColor = Color.Black,
  ) { innerPadding ->
    Box(
      contentAlignment = Alignment.TopCenter,
      modifier = Modifier
        .padding(innerPadding)
        .background(Color.Black)
    ) {

    }
  }
}




@Composable
@Preview
private fun DetailsScreenPreview() {
DetailsScreen()
}
