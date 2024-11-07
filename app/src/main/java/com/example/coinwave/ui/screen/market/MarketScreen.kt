package com.example.coinwave.ui.screen.market

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.hilt.navigation.compose.hiltViewModel
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.coinwave.common.data.SortParams
import com.example.coinwave.data.service.model.CoinItem
import com.example.coinwave.ui.screen.market.component.CoinSortChip
import com.example.coinwave.ui.screen.market.component.MarketCoinListItem
import com.example.coinwave.ui.screen.market.viewmodel.MarketViewModel
import kotlinx.coroutines.flow.collectLatest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketScreen(
  navController: NavHostController,
  //  onCoinClick: (CoinItem) -> Unit,
  viewModel: MarketViewModel = hiltViewModel()

) {
  val listState = rememberLazyListState()
  val snackbarHostState = remember { SnackbarHostState() }
  var coinList by remember { mutableStateOf<List<CoinItem>>(emptyList()) }
  val selectedSort by viewModel.sortParamsFlow.collectAsState(SortParams.MarketCap)
  val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
  val scrollFraction = scrollBehavior.state.overlappedFraction
  val dynamicTextColor = if (scrollFraction > 0.5f) Color.Black else Color.White

  //when the MarketScreen is first composed
  LaunchedEffect(Unit) {
    viewModel.coinList.collectLatest { coins ->
      coinList = coins
    }
  }

  Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
      TopAppBar(title = {
        Text(
          "Cryptocurrency", maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          color = dynamicTextColor
        )
      },
        actions = {
          IconButton(onClick = {
            navController.navigate("search")
          }) {
            Icon(
              imageVector = Icons.Default.Search,
              contentDescription = "Search",
              tint = dynamicTextColor
            )
          }
        }
        ,
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
      Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
          .padding(innerPadding)
          .background(Color.Black)
      ) {
        MarketList(
          coinList = coinList,
          //        onCoinClick = onCoinClick,
          lazyListState = listState,
          selectedSort = selectedSort ,
          updateSortParams = { sortParams->
            viewModel.updateCoinSortParams(sortParams)
          }
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
        text = "Cryptocurrency",
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.onBackground
      )
      marketCapPercentage.let {
        Text(
          text = "Todo",
          //            when {
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
  selectedSort : SortParams,
  updateSortParams:(SortParams) -> Unit,
  isSearching: Boolean = false,
  ) {
  LazyColumn(
    state = lazyListState,
    contentPadding = PaddingValues(start = 12.dp, end = 12.dp),
    modifier = Modifier.fillMaxSize()

  ) {
   if(!isSearching) {
     item {
       Row(
         horizontalArrangement = Arrangement.spacedBy(8.dp),
         modifier = Modifier.horizontalScroll(rememberScrollState()).padding(bottom = 8.dp)
       ) {
         SortParams.entries.forEach { coinSortEntry ->
           CoinSortChip(coinSort = coinSortEntry,
             selected = coinSortEntry == selectedSort,
             onClick = { updateSortParams(coinSortEntry) })
         }
       }
     }
   }

    items(
      count = coinList.size,
      itemContent = { index ->
        val coinListItem = coinList[index]
          MarketCoinListItem(
            item = coinListItem,
            modifier = Modifier.padding(bottom = 8.dp)
            //          onCoinClick = { onCoinClick(coinListItem) },
          )
      },
    )
  }
}

