package com.dts.circle_game

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.dts.circle_game.scores.ScoresActivity
import com.dts.circle_game.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    companion object {
        private const val TIMER_TIME_DURATION = 30_000L
        private const val TIMER_STEP_DURATION = 1000L
    }


    private lateinit var binding: ActivityMainBinding
    private var counterSuccess = 0
    private var counterTotal = 0

    private var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupListener()

    }

    private fun setupListener() {
        binding.btnScores.setOnClickListener {
            val intent = Intent(this, ScoresActivity::class.java)
            startActivity(intent)
        }

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

        binding.etUserName.addTextChangedListener {
            binding.btnStart.isEnabled = it?.length!! >= 3
        }
    }

    private fun beginTimer() {
        timer?.cancel()
        timer = object : CountDownTimer(TIMER_TIME_DURATION, TIMER_STEP_DURATION) {

            override fun onTick(millisUntilFinished: Long) {
                binding.tvTimer.text = millisUntilFinished.secondsStr
            }

            override fun onFinish() {
                binding.btnStart.isEnabled = true
                binding.circleDrawer.isCanDraw = false
                binding.circleDrawer.invalidate()
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
