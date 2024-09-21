package com.example.coinwave.ui.screen.market

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.component.MarketCoinListItem
import com.example.coinwave.ui.screen.market.viewmodel.MarketViewModel
import kotlinx.coroutines.flow.collectLatest



@OptIn(ExperimentalMaterial3Api::class)
@Composable
 fun MarketScreen(
  modifier: Modifier = Modifier,
//  onCoinClick: (CoinItem) -> Unit,
  viewModel: MarketViewModel = hiltViewModel()

) {
  val listState = rememberLazyListState()
  val snackbarHostState = remember { SnackbarHostState() }
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
  var coinList by remember { mutableStateOf<List<CoinItem>>(emptyList()) }

  LaunchedEffect(Unit) {
    viewModel.getCoinsListData()
    viewModel.coinList.collectLatest {
      coinList=it
    }
  }



  Scaffold(topBar = {
    MarketTopBar(25.00, modifier)
  },
    snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
    modifier = modifier
      .fillMaxSize()
      .nestedScroll(scrollBehavior.nestedScrollConnection)
  ) { paddingValues ->
    Box(
      contentAlignment = Alignment.TopCenter,
      modifier = Modifier
        //          .pullRefresh(pullRefreshState)
        .padding(paddingValues)
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketTopBar(
  marketCapPercentage: Double, modifier: Modifier = Modifier
) {
  var menuExpanded by remember { mutableStateOf(false) }
  TopAppBar(title = {
    Column {
      Text(
        text = "Good Morning",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
      )
      marketCapPercentage.let {
        Text(
          text = "Todo", //            when {
          //              it. -> "Market is up"
          //              it. -> "market is down"
          //              else -> "Everything is normal"
          //            },
          style = MaterialTheme.typography.titleLarge,
          color = MaterialTheme.colorScheme.onSurfaceVariant
        )
      }
    }
  })
}

@Composable
fun MarketList(
  coinList: List<CoinItem>,
//  onCoinClick: (CoinItem) -> Unit,
  lazyListState: LazyListState,
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(12.dp)
      .border(
        width = 2.dp,
        color = Color.LightGray,
        shape = RoundedCornerShape(20.dp)
      )
      .background(Color(0xFF1A1919), shape = RoundedCornerShape(20.dp)) // Light black background with shape
  ) {
    LazyColumn(
      modifier = Modifier.fillMaxSize(),
      contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
      state = lazyListState
    ) {

      items(count = coinList.size, itemContent = { index ->
        val coinListItem = coinList[index]
        MarketCoinListItem(
          item = coinListItem, //        onCoinClick = { onCoinClick(coinListItem) },
        )
      })
    }
  }
}

