package com.example.handyman

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.handyman.R.layout.first_time_user
import com.example.handyman.login.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.first_time_user.*

class FirstTimeUserActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(first_time_user)

        //this code should be moved to another class, just for testing purposes
        val userId = intent.getStringExtra("user_id")
        val emailId = intent.getStringExtra("email_id")

        tv_user_id.text = "User ID:: $userId"
        tv_email_id.text = "Email ID :: $emailId"

        btn_logout.setOnClickListener {
            //logout from app
            FirebaseAuth.getInstance().signOut()

            startActivity(Intent(this@FirstTimeUserActivity, LoginActivity::class.java))
            finish()
        }
    }
}