package com.example.handyman.login

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handyman.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnLogRegister.setOnClickListener {
            onBackPressed()
        }

        btn_register.setOnClickListener {
            when {
                TextUtils.isEmpty(nameReg_tf.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@RegisterActivity,
                            "Please enter your name.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(emailReg_tf.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@RegisterActivity,
                            "Please enter your email.",
                            Toast.LENGTH_SHORT
                    ).show()
                }

                TextUtils.isEmpty(passwordReg_tf.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(
                            this@RegisterActivity,
                            "Please enter your password.",
                            Toast.LENGTH_SHORT
                    ).show()
                }
                else -> {
                    val name: String = nameReg_tf.text.toString().trim { it <= ' ' }
                    val email: String = emailReg_tf.text.toString().trim { it <= ' ' }
                    val password: String = passwordReg_tf.text.toString().trim { it <= ' ' }

                    //Create instance and create a register a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                //if registration is successful
                                if (task.isSuccessful) {
                                    //firebase registered user
                                    val firebaseUser: FirebaseUser = task.result!!.user!!

                                    Toast.makeText(this@RegisterActivity,
                                            "You are registered successfully!",
                                            Toast.LENGTH_SHORT
                                    ).show()
                                    /* Here the new user registered is automatically
                                    signed-in so we just sign-out and send him to main screen with user id and email
                                    that user have used for
                                    * */

                                }
                            }
                }
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
    }

}