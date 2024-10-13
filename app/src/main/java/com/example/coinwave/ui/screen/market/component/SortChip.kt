package com.example.coinwave.ui.screen.market.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.rounded.MoreTime
import androidx.compose.material.icons.rounded.TrendingDown
import androidx.compose.material.icons.rounded.TrendingUp
import androidx.compose.material.icons.rounded.Whatshot
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.coinwave.common.SortParams


@Composable
fun CoinSortChip(
  coinSort: SortParams,
  selected: Boolean,
  onClick: () -> Unit,
  modifier: Modifier = Modifier
) {
  FilterChip(
    label = { Text(text = stringResource(coinSort.nameId)) },
    onClick = onClick,
    selected = selected,
    leadingIcon = {
      val imageVector = when (coinSort) {
        SortParams.MarketCap -> Icons.Filled.AttachMoney
        SortParams.Popular -> Icons.Rounded.Whatshot
        SortParams.Gainers -> Icons.Rounded.TrendingUp
        SortParams.Losers -> Icons.Rounded.TrendingDown
        SortParams.Newest -> Icons.Rounded.MoreTime
      }

      Icon(
        imageVector = imageVector,
        tint = if (selected) {
          MaterialTheme.colorScheme.onSurface
        } else {
          MaterialTheme.colorScheme.onSurfaceVariant
        },
        contentDescription = null
      )
    },

      colors = FilterChipDefaults.filterChipColors(
      containerColor = MaterialTheme.colorScheme.background,
      labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
      selectedContainerColor = MaterialTheme.colorScheme.surface,
      selectedLabelColor = MaterialTheme.colorScheme.onSurface,
    ),
    shape = MaterialTheme.shapes.small,
    border = null,
    modifier = modifier
  )
}

