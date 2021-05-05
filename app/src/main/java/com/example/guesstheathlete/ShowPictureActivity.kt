package com.example.guesstheathlete

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.security.AccessControlContext


class ShowPictureActivity : AppCompatActivity() {

    //CREATING THE VARIABLES OF EDIT TEXT AND BUTTON TO SUBMIT NAME
    private lateinit var submitNameButton: Button
    private lateinit var editTextName : EditText
    private lateinit var scoreTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_picture)

        //GETTING THE BUTTON AND EDIT_TEXT REFERENCE
        submitNameButton = findViewById(R.id.submit_name)
        editTextName = findViewById(R.id.edit_text)
        scoreTextView = findViewById(R.id.score_text_view)

        //GET DATA FROM INTENT
        var intent = intent
        val score = intent.getStringExtra("Score")

        //TEXTVIEW
       scoreTextView.text = "Your Score is: " + score

        //FINISH GETTING DATA FROM INTENT


        //SETTING LISTENER ON SUBMIT_NAME_BUTTON
        submitNameButton.setOnClickListener {
            if (editTextName.text.isEmpty()){
                Toast.makeText(this, R.string.enter_something,Toast.LENGTH_SHORT)
                    .show()
            }
            if (score.toInt() <= 2){
                Toast.makeText(this, "Better Luck Next Time " + (editTextName.text) + "!", Toast.LENGTH_LONG)
                    .show()
            }

            else {
                Toast.makeText(this, "Good Job " + (editTextName.text) + "!", Toast.LENGTH_LONG)
                    .show()
            }
        }
    }

}
