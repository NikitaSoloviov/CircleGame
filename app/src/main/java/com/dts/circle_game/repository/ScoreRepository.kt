package com.dts.circle_game.repository

import com.dts.circle_game.model.Score

class ScoreRepository(private val dataSource: ScoreDataSource) {

    var onComplete: ((List<Score>) -> Unit)? = null

    fun getAll() = onComplete?.let { dataSource.getAll(it) }

    fun saveScore(username: String, score: Int, timeSeconds: Int) {
        dataSource.saveScore(Score(username, score, timeSeconds))
    }
}
