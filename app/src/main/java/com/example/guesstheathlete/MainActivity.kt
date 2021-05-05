package com.example.guesstheathlete

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewDebug
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.security.AccessController.getContext

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //CREATING THE VARIABLES
    private lateinit var neymarButton: Button
    private lateinit var lebronButton: Button
    private lateinit var messiButton: Button
    private lateinit var kobeButton: Button
    private lateinit var showResultsButton: Button
    private lateinit var nextButton: Button
    private lateinit var factsTextView: TextView

    //SCORE VARIABLES
    var correctAnswers = 0
    var incorrectAnswers = 0


    private val factsViewModel: FactsViewModel by lazy{
        ViewModelProviders.of(this).get(FactsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //GETTING THE BUTTON REFERENCES
        neymarButton = findViewById(R.id.neymar_button)
        lebronButton = findViewById(R.id.lebron_button)
        messiButton = findViewById(R.id.messi_button)
        kobeButton = findViewById(R.id.kobe_button)
        showResultsButton = findViewById(R.id.show_results_button)
        nextButton = findViewById(R.id.next_button)
        factsTextView = findViewById(R.id.facts_text_view)


        //SETTING UP LISTENERS FOR THE BUTTONS

        showResultsButton.isEnabled = false

        neymarButton.setOnClickListener {
            addToCorrects("neymar")
            addToIncorrects("neymar")
            unableButton("neymar", neymarButton, lebronButton, messiButton, kobeButton)
        }

        lebronButton.setOnClickListener {
            addToCorrects("lebron")
            addToIncorrects("lebron")
            unableButton("lebron", lebronButton, neymarButton, messiButton, kobeButton)
        }

        messiButton.setOnClickListener {
            addToCorrects("messi")
            addToIncorrects("messi")
            unableButton("messi", messiButton,neymarButton,lebronButton,kobeButton)
        }

        kobeButton.setOnClickListener {
            addToCorrects("kobe")
            addToIncorrects("kobe")
            unableButton("kobe", kobeButton,neymarButton,lebronButton,messiButton)
        }

        showResultsButton.setOnClickListener {
            val score = correctAnswers.toString()
            val intent = Intent(this@MainActivity, ShowPictureActivity::class.java)
            intent.putExtra("Score", score)
            startActivity(intent)
        }

        nextButton.setOnClickListener {
            factsViewModel.moveToNextFact()
            updateFacts()
            isFinished()
        }
        updateFacts()
    }
    private fun updateFacts(){
        val factsTextResId = factsViewModel.currentFactText
        factsTextView.setText(factsTextResId)
        enableAllButtons(neymarButton,lebronButton,messiButton,kobeButton)

    }

    private fun addToCorrects(userAnswer: String){
        if (userAnswer == factsViewModel.currentQuestionAnswer){
            correctAnswers += 1
        }
    }

    private fun addToIncorrects(userAnswer: String){
        if (userAnswer != factsViewModel.currentQuestionAnswer){
            incorrectAnswers += 1
        }
    }

    private fun isFinished(){
        if (correctAnswers + incorrectAnswers == factsViewModel.factsBankSize){
            disableNextButton(nextButton)
            disableAllButtons(neymarButton,lebronButton,messiButton,kobeButton)
            showResultsButton.isEnabled = true
            factsTextView.setText(R.string.enter_show_results_button)
            factsTextView.setTextColor(resources.getColor(R.color.colorAccent))

        }
    }

    private fun unableButton(userAnswer: String, button1: Button,button2: Button,button3: Button,button4: Button){
        val correctAnswer = factsViewModel.currentQuestionAnswer
        if (userAnswer == correctAnswer){
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false

        }
        else{
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
        }
    }
    private fun enableAllButtons(button1: Button, button2: Button, button3: Button, button4: Button){
        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
    }

    private fun disableNextButton(button1: Button){
        button1.isEnabled = false
    }

    private fun disableAllButtons(button1: Button, button2: Button, button3: Button, button4: Button){
        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
    }
}
