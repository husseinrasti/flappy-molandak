package com.husseinrasti.game.core.model

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.Dp

data class Player(
    val y: Dp,
    val speed: Float,
    val size: Size = Size(140f, 72f)
) {
    val rect = Rect(Offset(0f, y.value), size.toComposeSize())

    companion object {
        const val acceleration = 0.001f
    }
}