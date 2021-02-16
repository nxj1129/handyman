package com.example.handyman

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.handyman.R.id.btnRegLogin

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btnRegLogin = findViewById<Button>(btnRegLogin)
        btnRegLogin.setOnClickListener{
            startActivity(Intent(this, RegisterActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }
    }
}