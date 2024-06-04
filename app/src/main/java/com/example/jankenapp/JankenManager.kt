package com.example.jankenapp

class JankenManager {

    fun getRandomHand(): JankenHand {
        return JankenHand.values().random()
    }

    fun checkResult(myHand: JankenHand, computerHand: JankenHand): JankenResult {
        return if (myHand == computerHand) {
            JankenResult.DRAW
        } else if (
            (myHand == JankenHand.ROCK && computerHand == JankenHand.SCISSORS) ||
            (myHand == JankenHand.SCISSORS && computerHand == JankenHand.PAPER) ||
            (myHand == JankenHand.PAPER && computerHand == JankenHand.ROCK)
        ) {
            JankenResult.WIN
        } else {
            JankenResult.LOSS
        }
    }
}