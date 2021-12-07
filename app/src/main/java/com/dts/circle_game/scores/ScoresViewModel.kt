package com.dts.circle_game.scores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dts.circle_game.model.Score


class ScoresViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<Score>>()

    fun onScoresUpdate(): LiveData<List<Score>> = itemsLiveData

    fun putTestData() {
        val data = mutableListOf<Score>()
        data.add(Score("user", 20, 1))
        data.add(Score("user", 20, 1))
        data.add(Score("user", 20, 1))


        itemsLiveData.postValue(data)
    }
}
