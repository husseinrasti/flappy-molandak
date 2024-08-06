package com.husseinrasti.game.engine.logic

import androidx.compose.ui.unit.dp
import com.husseinrasti.game.core.model.Block
import com.husseinrasti.game.core.model.Pipe
import java.util.Random
import com.husseinrasti.game.core.model.Viewport

class BlockCreator(private val viewport: Viewport) {
    private val minimumHeightPercentage = 0.1f
    private val minimumGateHeightPercentage = 0.3f
    private val maximumGateHeightPercentage = 0.5f
    fun createBlock(): Block {
        val totalHeight = viewport.height
        val maximumGateHeight = totalHeight * maximumGateHeightPercentage
        val usableHeight =
            (totalHeight * (1f - minimumHeightPercentage)).coerceAtMost(maximumGateHeight)
        val minimumHeight = usableHeight * minimumGateHeightPercentage
        val pipeMinimumHeight = viewport.height * (minimumHeightPercentage / 2f)

        val randomFloat = Random().nextFloat()
        val gateHeight = minimumHeight + (usableHeight - minimumHeight) * randomFloat

        val minimumGateStartY = pipeMinimumHeight
        val maximumGateEndY = totalHeight * (1f - minimumHeightPercentage) - gateHeight

        val gateStart = minimumGateStartY + maximumGateEndY * (Random().nextFloat())

        return Block(
            Pipe(0.dp, gateStart, viewport.width),
            Pipe(gateStart + gateHeight, totalHeight, viewport.width)
        )
    }
}