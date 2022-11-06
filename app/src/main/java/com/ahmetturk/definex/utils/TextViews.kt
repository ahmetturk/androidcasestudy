package com.ahmetturk.definex.utils

import android.graphics.LinearGradient
import android.graphics.Shader
import android.widget.TextView

fun TextView.setGradientTextColor(startColor: Int, endColor: Int) {
    this.post {
        val textShader = LinearGradient(
            0f, 0f, this.measuredWidth.toFloat(), 0f,
            startColor,
            endColor,
            Shader.TileMode.CLAMP
        )
        this.paint.shader = textShader
        this.invalidate()
    }
}
