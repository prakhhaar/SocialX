package com.prakhar.socialx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Setting up tabs
        val fragmentAdapter = FragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(LogInFragment(), "LOGIN")
        fragmentAdapter.addFragment(SignUpFragment(), "SIGN UP")

        view_pager.adapter = fragmentAdapter
        tab_layout.setupWithViewPager(view_pager)

    }
}