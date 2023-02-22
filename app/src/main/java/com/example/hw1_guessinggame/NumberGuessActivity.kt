package com.example.hw1_guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.hw1_guessinggame.databinding.ActivityNumberGuessBinding

class NumberGuessActivity : AppCompatActivity() {

    var number = 0
    var counter = 0
    var binding: ActivityNumberGuessBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_number_guess)
        binding = ActivityNumberGuessBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        binding?.btnValidate?.setOnClickListener { validate() }
        binding?.btnRestart?.setOnClickListener { restart() }
        thinkNumber()
        findViewById<Button>(R.id.btnRestart).setClickable(false)
    }

    fun validate() {
        val guessed = findViewById<EditText>(R.id.guessedNumber)
        val attempts = findViewById<TextView>(R.id.amtCounter)
        println("Number: " + number)
        println("Attempts:" + counter)
        println("Guessed: " + guessed.text.toString().toInt())

        counter++;
        attempts.setText(counter.toString() + " attempts")


        if(guessed.text.toString().toInt() > number)
            Toast.makeText(this, "Try a smaller number", Toast.LENGTH_SHORT).show()
        else if(guessed.text.toString().toInt() < number)
            Toast.makeText(this, "Try a larger number", Toast.LENGTH_SHORT).show()
        else if(guessed.text.toString().toInt() == number) {
            Toast.makeText(this, "You're a good guesser", Toast.LENGTH_SHORT).show()
            attempts.setText("It took you " + counter.toString() + " attempts to guess the number!")
            findViewById<Button>(R.id.btnRestart).setClickable(true)
            findViewById<Button>(R.id.btnValidate).setClickable(false)
        }
    }

    fun thinkNumber() {
        number = (0..1000).random()
    }

    fun restart() {
        findViewById<TextView>(R.id.amtCounter).setText("0 attempts")
        findViewById<EditText>(R.id.guessedNumber).setText("")
        thinkNumber()
        counter = 0;
        findViewById<Button>(R.id.btnRestart).setClickable(false)
        findViewById<Button>(R.id.btnValidate).setClickable(true)
    }

}