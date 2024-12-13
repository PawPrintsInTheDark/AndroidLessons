package com.example.androidlessons

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity

class BrowserActivity : AppCompatActivity() {

    private lateinit var webViewWV: WebView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browser)

        webViewWV = findViewById(R.id.webViewWV)
        webViewWV.webViewClient = WebViewClient()
        val data  = intent.data
        webViewWV.loadUrl(data.toString())

    }
}