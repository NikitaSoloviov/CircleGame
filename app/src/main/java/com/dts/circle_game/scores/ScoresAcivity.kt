package com.dts.circle_game.scores

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dts.circle_game.scores.adapter.ScoresAdapter
import com.dts.circle_game.databinding.ActivityScoresBinding

class ScoresActivity : AppCompatActivity() {

    private val viewModel by lazy { ScoresViewModel() }
    private val adapter = ScoresAdapter()
    private lateinit var binding: ActivityScoresBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoresBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAdapter()
        setupObservers()
        viewModel.putTestData()
    }

    private fun setupAdapter() {
        binding.rvScores.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.onScoresUpdate().observe(this) { scores ->
            adapter.items = scores
        }
    }
}
