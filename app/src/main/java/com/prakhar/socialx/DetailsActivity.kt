package com.prakhar.socialx

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.webkit.WebViewFragment
import kotlinx.android.synthetic.main.activity_details.*
import java.net.URL

class DetailsActivity : AppCompatActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val url = intent.getStringExtra("URL")
        if (url != null) {
            detailWebView.settings.javaScriptEnabled = true
            detailWebView.webViewClient = object : WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    progressBar.visibility = View.GONE
                    detailWebView.visibility = View.VISIBLE

                }
            }
            detailWebView.loadUrl(url)
        }
    }
}