package com.dts.circle_game

import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import com.dts.circle_game.databinding.ActivityMainBinding
import com.dts.circle_game.repository.FireDatabase
import com.dts.circle_game.repository.ScoreRepository
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TIMER_TIME_DURATION = 30_000L
        private const val TIMER_STEP_DURATION = 1000L
    }

    private lateinit var binding: ActivityMainBinding

    private val scoreRepository by lazy { ScoreRepository(FireDatabase()) }

    private var counterSuccess = 0
    private var counterTotal = 0

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()
        val data = scoreRepository.getAll()
        Timber.i("List data, $data")
    }

    private fun setupListener() {
        binding.circleDrawer.setOnCompleteLevel {
            counterSuccess++
            binding.tvCount.text = counterSuccess.toString()
        }
        binding.btnStart.setOnClickListener {
            counterSuccess = 0
            counterTotal = 0
            binding.tvCount.text = "0"
            binding.circleDrawer.isCanDraw = true
            binding.circleDrawer.invalidate()
            it.isEnabled = false
            beginTimer()
        }
    }

    private fun beginTimer() {
        val username = binding.etUserName.text.toString()
        timer?.cancel()
        timer = object : CountDownTimer(TIMER_TIME_DURATION, TIMER_STEP_DURATION) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = millisUntilFinished.secondsStr
            }

            override fun onFinish() {
                binding.btnStart.isEnabled = true
                binding.circleDrawer.isCanDraw = false
                binding.circleDrawer.invalidate()
                scoreRepository.saveScore(
                    username,
                    counterSuccess,
                    TIMER_TIME_DURATION.millisToSeconds
                )
                binding.root.snackbar(
                    resources.getString(
                        R.string.finish_game_format,
                        counterSuccess
                    )
                )
            }
        }
        timer?.start()
    }
}
