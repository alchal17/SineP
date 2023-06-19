package com.example.sinep.scripts

import android.content.Context

fun dpToPx(context: Context, dpValue: Float) =
    dpValue * context.resources.displayMetrics.density