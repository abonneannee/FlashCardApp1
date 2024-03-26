package com.example.flashcard_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toggleButton: ImageView = findViewById(R.id.toggle123)
        val questionTextView: TextView = findViewById(R.id.flashcard_question) // Correction ici
        val answerTextView: TextView = findViewById(R.id.flashcard_reponse) // Correction ici

        questionTextView.setOnClickListener {
            questionTextView.visibility = View.INVISIBLE
            answerTextView.visibility = View.VISIBLE
        }

        answerTextView.setOnClickListener {
            questionTextView.visibility = View.VISIBLE
            answerTextView.visibility = View.INVISIBLE
        }

        toggleButton.setOnClickListener {
            val intent = Intent(this, AddCard::class.java)
            startActivity(intent)
        }

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val question = data.getStringExtra("question")
                    val answer = data.getStringExtra("answer")

                    // Mettre à jour les TextView dans MainActivity avec les nouvelles données
                    questionTextView.text = question
                    answerTextView.text = answer
                } else {
                    Log.i("MainActivity", "No data returned")
                }
            } else {
                Log.i("MainActivity", "Save operation cancelled")
            }
        }

        toggleButton.setOnClickListener {
            val intent = Intent(this, AddCard::class.java)
            resultLauncher.launch(intent)
        }
    }
}
