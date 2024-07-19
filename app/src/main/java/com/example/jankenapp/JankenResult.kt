package com.example.jankenapp

import android.content.Context
import androidx.annotation.StringRes

enum class JankenResult(@StringRes val resId: Int) {
    WIN(R.string.your_win),
    LOSS(R.string.your_losing),
    DRAW(R.string.draw);
}