package com.example.flashcard_app

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageView
class AddCard : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_card)
        val editTextField = findViewById<EditText>(R.id.editTextField)
        val editTextField1 = findViewById<EditText>(R.id.editTextField1)
        val ShowingAnswers = findViewById<ImageView>(R.id.icone_X)
        val SaveAnswers = findViewById<ImageView>(R.id.icone_save)

        ShowingAnswers.setOnClickListener {
            val intent = Intent(this, Activity::class.java)
            startActivity(intent)
        }

        SaveAnswers.setOnClickListener {
            val question = editTextField.text.toString()
            val answer = editTextField1.text.toString()

            val intent = Intent()
            intent.putExtra("question", question)
            intent.putExtra("answer", answer)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }
}