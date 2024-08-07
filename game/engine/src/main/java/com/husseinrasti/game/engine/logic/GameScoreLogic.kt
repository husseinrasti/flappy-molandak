package com.husseinrasti.game.engine.logic

import com.husseinrasti.game.engine.GameLogic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class GameScoreLogic(
    private val playerLogic: PlayerLogic,
    private val blockMovementLogic: BlockMovementLogic
) : GameLogic, OnGameOverLogic {
    private val _score = MutableStateFlow(0)
    val score: StateFlow<Int> = _score
    override fun onUpdate(deltaTime: Float) {
        val blocks = blockMovementLogic.blockPosition.value
        blocks.forEach { block ->
            if (block.hasBeenScored) return@forEach

            val scoreRect = block.scoreRect
            val playerRect = playerLogic.player.value.rect

            val hasScored = playerRect.overlaps(scoreRect)
            if (hasScored) {
                blockMovementLogic.scoreBlock(block)
                _score.update { it + 1 }
            }
        }
    }


    override fun onGameOver() {
        _score.value = 0
    }
}