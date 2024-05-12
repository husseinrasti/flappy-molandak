package com.husseinrasti.game.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.husseinrasti.game.engine.logic.BlockMovementLogic

@Composable
internal fun Block(blockMovementLogic: BlockMovementLogic, modifier: Modifier = Modifier) {
    Box(modifier) {
        val blockPositions = blockMovementLogic.blockPosition.collectAsState().value
        blockPositions.forEach { blockPosition ->
            Pipe(blockPosition.topPipe)
            Pipe(blockPosition.bottomPipe)
        }
    }
}