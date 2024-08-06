package com.husseinrasti.game.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.husseinrasti.game.core.model.toDpSize
import com.husseinrasti.game.engine.logic.PlayerLogic
import com.husseinrasti.game.ui.R

@Composable
internal fun Player(
    modifier: Modifier,
    playerLogic: PlayerLogic,
) {
    Box(modifier) {

        val player = playerLogic.player.collectAsState()
        Image(
            painterResource(id = R.drawable.molandak),
            contentDescription = null,
            Modifier
                .offset {
                    IntOffset(x = 0, y = player.value.y.roundToPx())
                }
                .size(player.value.size.toDpSize())
                .graphicsLayer {
                    rotationZ = (player.value.speed * 90f).coerceIn(-60f, 60f)
                }
        )
    }
}