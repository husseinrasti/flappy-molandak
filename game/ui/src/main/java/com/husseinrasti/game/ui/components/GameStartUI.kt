package com.husseinrasti.game.ui.components

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.husseinrasti.game.core.model.GameStatus
import com.husseinrasti.game.ui.di.GameDI

@Composable
internal fun BoxScope.GameStartUI(di: GameDI) {
    val notStarted =
        di.gameStatusLogic.gameState.collectAsState().value == GameStatus.NotStarted
    if (notStarted)
        Text(
            "Tap To Start!",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-140).dp),
            fontSize = 50.sp,
            fontWeight = FontWeight.Black
        )
}