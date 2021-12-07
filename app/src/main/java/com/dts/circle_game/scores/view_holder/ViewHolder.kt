package com.dts.circle_game.scores.view_holder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.dts.circle_game.scores.model.Score
import com.dts.circle_game.databinding.CellItemBinding

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding: CellItemBinding = CellItemBinding.bind(view)

    fun bind(view: Score) {
        binding.tvPlace.text = view.place.toString()
        binding.tvScore.text = view.score.toString()
        binding.tvUserName.text = view.userName
    }
}
