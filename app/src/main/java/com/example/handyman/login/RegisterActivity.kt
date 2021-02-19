package com.example.handyman.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.handyman.FirstTimeUserActivity
import com.example.handyman.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        btnLogRegister.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btn_register.setOnClickListener {
            when {
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
                    //val name: String = nameReg_tf.text.toString().trim { it <= ' ' }
                    val email: String = emailReg_tf.text.toString().trim { it <= ' ' }
                    val password: String = passwordReg_tf.text.toString().trim { it <= ' ' }

                    //Create instance and create a register a user with email and password
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            //if registration is successful
                            if (task.isSuccessful) {
                                //firebase registered user
                                val firebaseUser: FirebaseUser = task.result!!.user!!

                                Toast.makeText(
                                    this@RegisterActivity,
                                    "You are registered successfully!",
                                    Toast.LENGTH_SHORT
                                ).show()
                                /* Here the new user registered is automatically
                                signed-in so we just sign-out and send him to main screen with user id and email
                                that user have used for registering
                                * */
                                val intent =
                                    Intent(this@RegisterActivity, FirstTimeUserActivity::class.java)
                                intent.flags =
                                    Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", firebaseUser.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                                finish()
                            } else {
                                //if registering is not successful then show error message
                                Toast.makeText(
                                    this@RegisterActivity,
                                    task.exception!!.message.toString(),
                                    Toast.LENGTH_SHORT
                                ).show()
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