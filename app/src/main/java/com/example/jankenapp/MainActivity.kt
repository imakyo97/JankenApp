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
                    activityMainBinding.tvResult.text = uiState.result
                    activityMainBinding.tvComputerHand.text = uiState.computerHand
                    activityMainBinding.tvMyHand.text = uiState.myHand
                }
            }
        }
        activityMainBinding.btRock.setOnClickListener{viewModel.didTapRock()}
        activityMainBinding.btScissors.setOnClickListener{viewModel.didTapScissors()}
        activityMainBinding.btPaper.setOnClickListener{viewModel.didTapPaper()}
    }
}