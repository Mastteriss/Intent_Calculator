package com.example.intent_calculator

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {

    private lateinit var input1: EditText
    private lateinit var input2: EditText
    private lateinit var resultButton: Button
    private lateinit var addButton: Button
    private lateinit var subtractButton: Button
    private lateinit var multiplyButton: Button
    private lateinit var divideButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        input1 = findViewById(R.id.input1)
        input2 = findViewById(R.id.input2)
        resultButton = findViewById(R.id.resultButton)
        addButton = findViewById(R.id.addButton)
        subtractButton = findViewById(R.id.subtractButton)
        multiplyButton = findViewById(R.id.multiplyButton)
        divideButton = findViewById(R.id.divideButton)

        val results = { operation: (Double, Double) -> Double ->
            val num1 = input1.text.toString().toDoubleOrNull() ?: 0.0
            val num2 = input2.text.toString().toDoubleOrNull() ?: 0.0
            operation(num1, num2)
        }

        addButton.setOnClickListener {
            val result = results { a, b -> a + b }
            sendResult(result)
        }

        subtractButton.setOnClickListener {
            val result = results { a, b -> a - b }
            sendResult(result)
        }

        multiplyButton.setOnClickListener {
            val result = results { a, b -> a * b }
            sendResult(result)
        }

        divideButton.setOnClickListener {
            val result = results { a, b -> if (b != 0.0) a / b else 0.0 }
            sendResult(result)
        }

        resultButton.setOnClickListener {
            finish()
        }
    }

    private fun sendResult(result: Double) {
        val intent = Intent()
        intent.putExtra("RESULT", result)
        setResult(RESULT_OK, intent)
        finish()
    }
}