package com.example.coinwave.ui.screen.market.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.coinwave.data.service.model.CoinItem

@Composable
fun MarketCoinListItem(
  item: CoinItem,
  //  onCoinClick: (CoinItem) -> Unit,
) {

  val price = item.currentPrice?.let { String.format("%.3f", it.toDouble()) }
  Surface(
    modifier = Modifier
      .fillMaxWidth()
      .background(color = Color.Black)
//      .clickable { onCoinClick(coin) }
  ) {
    Row(
      modifier = Modifier.fillMaxWidth().padding(12.dp),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      Image(
        painter = rememberAsyncImagePainter(item.imageUrl),
        contentDescription = "Coin Image",
        modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray)
      )
      Spacer(Modifier.width(12.dp))

      Column(
        modifier = Modifier.weight(1f)
      ) {
        Text(
          text = item.name.toString(),
          color = Color.White,
          fontSize = 18.sp,
          fontWeight = FontWeight.Bold
        )
        Text(
          text = item.symbol.toString(), color = Color.Gray, fontSize = 14.sp
        )
      }
      Spacer(Modifier.width(10.dp))

      Column(
        horizontalAlignment = Alignment.End
      ) {
        Text(
          text = "$$price",
          color = Color.White,
          fontSize = 16.sp,
          fontWeight = FontWeight.SemiBold,
          textAlign = TextAlign.End
        )
        PriceChangeIndicator(item.priceChangePercentage24h)
      }
    }
  }
}
