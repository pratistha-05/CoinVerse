package com.example.coinwave.ui.screen.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.MarketList
import com.example.coinwave.ui.screen.market.component.MarketCoinListItem
import com.example.coinwave.ui.screen.market.viewmodel.MarketViewModel
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController,
  viewModel: SearchViewModel = hiltViewModel()
) {
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
  var coinList by remember { mutableStateOf<List<CoinItem>>(emptyList()) }
  val listState = rememberLazyListState()
  val snackbarHostState = remember { SnackbarHostState() }

  val inputText by viewModel.inputText.collectAsState()
  Scaffold(

  modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

    topBar = {
      TopAppBar(
        title = {
          OutlinedTextField(
            value = inputText,
            onValueChange = {
                viewModel.onInputChange(it)
              },
            placeholder = { Text("Search...", color = Color.White) },
            modifier = Modifier
              .fillMaxWidth()
              .padding(end = 8.dp),
            singleLine = true,
            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.colors(
              focusedTextColor = Color.White,
            )
          )
        },
        navigationIcon = {
          IconButton(onClick = {
            navController.popBackStack()
          }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = "Back",
              tint = Color.White
            )
          }
        },
        actions = {},
        scrollBehavior = scrollBehavior,
        colors = TopAppBarDefaults.topAppBarColors(
          containerColor = Color.Black,
          titleContentColor = Color.White,
          navigationIconContentColor = Color.White 
        )
      )
    },
    containerColor = Color.Black,
    contentColor = Color.Black,
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },

    ) { innerPadding ->

    LaunchedEffect(Unit) {
      viewModel.coinList.collectLatest {
        coinList = it
      }
    }
    Box(
      contentAlignment = Alignment.TopCenter,
      modifier = Modifier
        //          .pullRefresh(pullRefreshState)
        .padding(innerPadding)
    ) {
      MarketList(
        coinList = coinList,
        //        onCoinClick = onCoinClick,
        //        coinSort = model.coinSort,
        lazyListState = listState
      )
    }
    }
  }

