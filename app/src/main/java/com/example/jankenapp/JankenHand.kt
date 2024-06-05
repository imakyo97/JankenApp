package com.example.jankenapp

import android.content.Context
import androidx.annotation.StringRes

enum class JankenHand(@StringRes val resId: Int) {
    ROCK(R.string.rock),
    SCISSORS(R.string.scissors),
    PAPER(R.string.paper);

    fun toText(context: Context): String {
        return context.getString(this.resId)
    }
}