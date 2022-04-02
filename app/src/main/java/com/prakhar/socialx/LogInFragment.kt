package com.prakhar.socialx

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login_tab_fragment.*
import kotlinx.android.synthetic.main.signup_tab_fragment.*

class LogInFragment : Fragment(R.layout.login_tab_fragment) {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_log_in.setOnClickListener {
            when {
                //Making sure if any field is not left empty

                TextUtils.isEmpty(et_email.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                }

                TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = et_email.text.toString().trim{ it <= ' ' }
                    val password: String = et_password.text.toString().trim{ it <= ' ' }

                    //LogIn using FirebaseAuth
                    FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            //If the LogIn is successful
                            if (task.isSuccessful) {

                                //Firebase registered user
                                Toast.makeText(context, "You've logged in successfully!", Toast.LENGTH_SHORT).show()

                                val intent = Intent(context, MainActivity::class.java)
                                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                                intent.putExtra("user_id", FirebaseAuth.getInstance().currentUser!!.uid)
                                intent.putExtra("email_id", email)
                                startActivity(intent)
                            }else {
                                //If the registration is not successful then show error message
                                Toast.makeText(context, task.exception!!.message.toString(), Toast.LENGTH_SHORT).show()
                            }
                        }
                }

            }

        }
    }
}