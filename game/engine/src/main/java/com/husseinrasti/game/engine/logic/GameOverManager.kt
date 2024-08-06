package com.husseinrasti.game.engine.logic

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import com.husseinrasti.game.core.model.GameStatus

class GameOverManager(
    private val gameStatusLogic: GameStatusLogic,
    private val onGameOverLogics: List<OnGameOverLogic>,
    private val coroutineScope: CoroutineScope
) {
    init {
        coroutineScope.launch {
            gameStatusLogic.gameState.filter {
                it == GameStatus.GameOver
            }.collect {
                onGameOverLogics.forEach { it.onGameOver() }
            }
        }
    }
}