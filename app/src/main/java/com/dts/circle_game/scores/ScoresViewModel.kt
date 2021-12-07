package com.dts.circle_game.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dts.circle_game.scores.model.Score

class ScoresViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<Score>>()

    fun onScoresUpdate(): LiveData<List<Score>> = itemsLiveData

    fun putTestData() {
        val data = mutableListOf<Score>()
        data.add(Score(1, 20, "User"))
        data.add(Score(2, 20, "User`"))
        data.add(Score(3, 20, "User1"))
        data.add(Score(4, 20, "User2"))
        data.add(Score(5, 20, "User3"))

        itemsLiveData.postValue(data)
    }
}
