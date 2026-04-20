package com.example.tipcalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val costInput = findViewById<EditText>(R.id.costOfService)
        val radioGroup = findViewById<RadioGroup>(R.id.tipOptions)
        val roundSwitch = findViewById<Switch>(R.id.roundUpSwitch)
        val resultText = findViewById<TextView>(R.id.tipResult)
        val button = findViewById<Button>(R.id.calculateButton)

        button.setOnClickListener {

            // Get cost entered by user

            if (cost == null) {
                resultText.text = "Please enter the service cost"
                return@setOnClickListener
            }

            // Get selected tip percentage
            val tipPercent = when (radioGroup.checkedRadioButtonId) {
                R.id.option_20_percent -> 0.20
                R.id.option_18_percent -> 0.18
                R.id.option_15_percent -> 0.15
                else -> 0.15
            }

            // Calculate tip
            var tip = cost * tipPercent

            // Round tip if switch is ON
            if (roundSwitch.isChecked) {
                tip = ceil(tip)
            }

            // Display result
            resultText.text = "Tip Amount: ₹$tip"
        }
    }
}