package com.dhruva.userauthentication.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.dhruva.userauthentication.R

class HomeActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)

        // Find the TextView in the layout
        val welcomeTextView: TextView = findViewById(R.id.welcomeTextView)

        // Set the text for the TextView
        welcomeTextView.text = "Welcome to the Home Screen!"
    }
}
