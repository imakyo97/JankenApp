package com.example.jankenapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.jankenapp.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        val activityMainView = activityMainBinding.root
        setContentView(activityMainView)

        val viewModel: JankenViewModel by viewModels()

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { uiState ->
                    activityMainBinding.tvResult.text = toStringResult(uiState.result)
                    activityMainBinding.tvComputerHand.text = toStringHand(uiState.computerHand)
                    activityMainBinding.tvMyHand.text = toStringHand(uiState.myHand)
                }
            }
        }
        activityMainBinding.btRock.setOnClickListener { viewModel.didTapRock() }
        activityMainBinding.btScissors.setOnClickListener { viewModel.didTapScissors() }
        activityMainBinding.btPaper.setOnClickListener { viewModel.didTapPaper() }
    }

    private fun toStringResult(result: JankenResult?): String {
        return when (result) {
            JankenResult.WIN -> getString(R.string.your_win)
            JankenResult.LOSS -> getString(R.string.your_losing)
            JankenResult.DRAW -> getString(R.string.draw)
            else -> ""
        }
    }

    private fun toStringHand(hand: JankenHand?): String {
        return when (hand) {
            JankenHand.ROCK -> getString(R.string.rock)
            JankenHand.SCISSORS -> getString(R.string.scissors)
            JankenHand.PAPER -> getString(R.string.paper)
            else -> ""
        }
    }
}