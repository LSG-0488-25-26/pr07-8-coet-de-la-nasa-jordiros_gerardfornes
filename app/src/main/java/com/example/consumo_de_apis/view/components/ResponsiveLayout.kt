package com.example.consumo_de_apis.view

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.layout.WindowMetricsCalculator

enum class ScreenOrientation { PORTRAIT, LANDSCAPE }

data class WindowInfo(
    val width: Dp,
    val height: Dp,
    val orientation: ScreenOrientation
)

@Composable
fun getWindowInfo(): WindowInfo {
    val context = LocalContext.current

    val windowMetrics = WindowMetricsCalculator.getOrCreate()
        .computeCurrentWindowMetrics(context)

    val widthPx = windowMetrics.bounds.width().toFloat()
    val heightPx = windowMetrics.bounds.height().toFloat()

    val density = context.resources.displayMetrics.density

    val widthDp = (widthPx / density).dp
    val heightDp = (heightPx / density).dp

    val configuration = LocalConfiguration.current
    val orientation = if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        ScreenOrientation.LANDSCAPE
    } else {
        ScreenOrientation.PORTRAIT
    }
    return WindowInfo(widthDp, heightDp, orientation)
}
