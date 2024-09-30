package com.example.coinwave.ui.screen.market.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PriceChangeIndicator(
  percentageChange: String?
) {
  val percentage = percentageChange?.toDoubleOrNull()?:0.0

  val (icon, color, rotation) = when {
    percentage > 0 -> Triple(Icons.Default.ArrowDropDown, Color.Green, 180f)
    percentage < 0 -> Triple(Icons.Default.ArrowDropDown, Color.Red, 0f)
    else -> Triple(Icons.Default.ArrowDropDown, Color.Gray, 0f)
  }

  Row(
    verticalAlignment = Alignment.CenterVertically,
    horizontalArrangement = Arrangement.End
  ) {
    Icon(
      imageVector = icon,
      contentDescription = "Trending",
      tint = color,
      modifier = Modifier.size(18.dp)
         .graphicsLayer(rotationZ = rotation)

    )
    Text(
      text = "${"%.3f".format(percentage)}%",
      color = color,
      fontSize = 14.sp,
      textAlign = TextAlign.End,
      modifier = Modifier.padding(start = 4.dp)
    )
  }
}
