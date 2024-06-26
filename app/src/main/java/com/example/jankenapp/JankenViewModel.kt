package com.example.jankenapp

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class JankenUiState(
    val result: JankenResult? = null,
    val computerHand: JankenHand? = null,
    val myHand: JankenHand? = null,
)

class JankenViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(JankenUiState())
    val uiState: StateFlow<JankenUiState> = _uiState.asStateFlow()
    private val jankenManager = JankenManager()

    private fun updateState(myHand: JankenHand) {
        val computerHand = jankenManager.getRandomHand()
        val result = jankenManager.checkResult(myHand = myHand, computerHand = computerHand)
        _uiState.update { uiState ->
            uiState.copy(
                result = result,
                computerHand = computerHand,
                myHand = myHand,
            )
        }
    }

    fun didTapRock() {
        updateState(myHand = JankenHand.ROCK)
    }

    fun didTapScissors() {
        updateState(myHand = JankenHand.SCISSORS)
    }

    fun didTapPaper() {
        updateState(myHand = JankenHand.PAPER)
    }
}