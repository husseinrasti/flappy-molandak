package com.husseinrasti.game.ui.components

import android.graphics.BitmapShader
import android.graphics.Shader
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.husseinrasti.game.component.extensions.toPx
import com.husseinrasti.game.engine.TimeManager
import com.husseinrasti.game.engine.logic.BlockMovementLogic.Companion.scrollAmount
import com.husseinrasti.game.ui.R
import com.husseinrasti.game.ui.resizeTo

@Composable
internal fun Background(timeManager: TimeManager) {
    BoxWithConstraints {
        var scrollX by remember { mutableFloatStateOf(0f) }
        LaunchedEffect(key1 = Unit) {
            timeManager.deltaTime.collect { deltaTime ->
                scrollX -= deltaTime * scrollAmount
            }
        }
        val paint = Paint().asFrameworkPaint().apply {
            shader = BitmapShader(
                ImageBitmap.imageResource(id = R.drawable.background_flappy).asAndroidBitmap()
                    .resizeTo(maxHeight.toPx().toInt()),
                Shader.TileMode.REPEAT,
                Shader.TileMode.MIRROR
            )
        }
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawIntoCanvas {
                it.translate(dx = scrollX.dp.toPx(), dy = 0f)
                it.nativeCanvas.drawPaint(paint)
                it.translate(dx = 0f, dy = 0f)
            }
        }
    }
}
