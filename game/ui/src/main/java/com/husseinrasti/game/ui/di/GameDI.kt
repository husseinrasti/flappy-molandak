package com.husseinrasti.game.ui.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.husseinrasti.game.core.model.Viewport
import com.husseinrasti.game.engine.LogicManager
import com.husseinrasti.game.engine.TimeManager
import com.husseinrasti.game.engine.gameCoroutineScope
import com.husseinrasti.game.engine.logic.BlockMovementLogic
import com.husseinrasti.game.engine.logic.BlockSpawnerLogic
import com.husseinrasti.game.engine.logic.GameOverManager
import com.husseinrasti.game.engine.logic.GameScoreLogic
import com.husseinrasti.game.engine.logic.GameStatusLogic
import com.husseinrasti.game.engine.logic.OnGameOverLogic
import com.husseinrasti.game.engine.logic.PlayerCollisionLogic
import com.husseinrasti.game.engine.logic.PlayerLogic

class GameDI(viewport: Viewport, val timeManager: TimeManager) {
    val coroutineScope = gameCoroutineScope()
    val gameStatusLogic = GameStatusLogic(coroutineScope)
    val playerLogic = PlayerLogic(gameStatusLogic, viewport)
    val blockMovementLogic = BlockMovementLogic(viewport)
    val playerCollisionLogic = PlayerCollisionLogic(playerLogic, blockMovementLogic, gameStatusLogic, viewport)
    val gameScoreLogic = GameScoreLogic(playerLogic, blockMovementLogic)
    private val onGameOverLogics: List<OnGameOverLogic> =
        listOf(playerLogic, blockMovementLogic, gameScoreLogic)
    val gameOverManager = GameOverManager(gameStatusLogic, onGameOverLogics, coroutineScope)
    private val logics = listOf(
        playerLogic,
        blockMovementLogic,
        playerCollisionLogic,
        gameScoreLogic,
        gameStatusLogic,
    )
    val logicManager = LogicManager(logics, gameStatusLogic, timeManager, coroutineScope)

    val blockSpawnerLogic = BlockSpawnerLogic(blockMovementLogic, gameStatusLogic, coroutineScope, viewport)

    companion object {
        @Composable
        fun rememberDI(viewport: Viewport): GameDI {
            val coroutineScope = rememberCoroutineScope()
            val timeManager = remember {
                TimeManager(coroutineScope)
            }
            return remember {
                GameDI(viewport, timeManager)
            }
        }
    }
}