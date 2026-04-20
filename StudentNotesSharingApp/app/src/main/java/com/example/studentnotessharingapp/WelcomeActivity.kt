package com.example.studentnotessharingapp

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val colors = listOf(
            Color.RED,
            Color.BLUE,
            Color.GREEN,
            Color.CYAN,
            Color.MAGENTA,
            Color.YELLOW
        )

        val randomColor = colors[Random.nextInt(colors.size)]

        window.decorView.setBackgroundColor(randomColor)
    }
}