import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive

@Composable
fun ProgressTimer(
  durationMillis: Int = 12000, // 12 seconds
  size: Dp = 12.dp,
  color: Color = Color.LightGray,
  backgroundColor: Color = Color.DarkGray,
  strokeWidth: Dp = 8.dp
) {
  val progress = remember { androidx.compose.animation.core.Animatable(0f) }

  // Restart the animation every 12 seconds
  LaunchedEffect(Unit) {
    while (isActive) {
      progress.animateTo(
        targetValue = 1f,
        animationSpec = tween(durationMillis)
      )
      progress.snapTo(0f) // Reset progress for next cycle
    }
  }

  Canvas(modifier = Modifier.size(size)) {
    // Background circle
    drawArc(
      color = backgroundColor,
      startAngle = 0f,
      sweepAngle = 360f,
      useCenter = true
    )

    // Animated progress arc
    drawArc(
      color = color,
      startAngle = -90f,
      sweepAngle = 360 * progress.value,
      useCenter = true
    )
  }
}

