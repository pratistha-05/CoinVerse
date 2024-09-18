package com.example.coinwave.ui.screen.market

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.coinwave.data.service.model.CoinItem

@Composable
fun MarketCoinListItem(
  item: CoinItem,
//  onCoinClick: (CoinItem) -> Unit,
) {

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .padding(16.dp),
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Image(
      painter = rememberImagePainter(item.imageUrl),
      contentDescription = "Coin Image",
      modifier = Modifier
        .size(40.dp)
        .clip(CircleShape)
        .background(Color.LightGray)
    )


    Text(
      text = item.name.toString(),
      modifier = Modifier.weight(1f)
    )

    // Coin Price
    Text(
      text = item.currentPrice.toString(),
      color = Color.Green
    )
  }

}