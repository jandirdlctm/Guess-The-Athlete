package com.example.guesstheathlete

import androidx.annotation.StringRes

data class Facts(@StringRes val textResId: Int, val answer: String)
