package com.example.coinwave.ui.screen.market.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.coinwave.R
import com.example.coinwave.data.service.model.CoinItem

@Composable
fun MarketCoinListItem(
  item: CoinItem,
  modifier: Modifier
  // onCoinClick: (CoinItem) -> Unit,
) {
  val price = item.currentPrice?.let { String.format("%.3f", it.toDouble()) }

  Box(
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(8.dp))
      .background(Color(0xFF232324))
      .padding(10.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.SpaceBetween
    ) {
      val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
          .data(item.imageUrl)
          .error(R.drawable.coin_verse_icon)
          .decoderFactory(SvgDecoder.Factory())
          .build()
      )

      Image(
        painter = painter,
        contentDescription = "Coin Image",
        modifier = Modifier
          .size(40.dp)
          .clip(CircleShape)
          .background(Color.LightGray)
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
          text = item.symbol.toString(),
          color = Color.Gray,
          fontSize = 14.sp
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
