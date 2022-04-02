package com.prakhar.socialx

import android.app.Dialog
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
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.login_tab_fragment.*
import kotlinx.android.synthetic.main.signup_tab_fragment.*

class SignUpFragment : Fragment(R.layout.signup_tab_fragment) {

    private lateinit var mProgressDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_register.setOnClickListener {
            when {
                // Checking if any field is not left empty

                TextUtils.isEmpty(et_name.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your name", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(et_email_signup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your email", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(et_contact.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your contact", Toast.LENGTH_SHORT).show()
                }
                TextUtils.isEmpty(et_password_signup.text.toString().trim { it <= ' ' }) -> {
                    Toast.makeText(context, "Please enter your password", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    val email: String = et_email_signup.text.toString().trim{ it <= ' ' }
                    val password: String = et_password_signup.text.toString().trim{ it <= ' ' }

                    //Create an instance and register a user with email and password.
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->

                            //If the registration is successful
                            if (task.isSuccessful) {
                                val firebaseUser: FirebaseUser = task.result!!.user!!
                                //Firebase registered user
                                Toast.makeText(context, "You're registered successfully!", Toast.LENGTH_SHORT).show()

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