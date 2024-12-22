package com.example.androidlessons

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast

class ViewPagerFragment : Fragment() {
    private var isLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentWV = view.findViewById<WebView>(R.id.fragmentWV)
        val viewPagerItem = arguments?.getSerializable("page") as Page
        fragmentWV.settings.javaScriptEnabled = true
        fragmentWV.settings.mediaPlaybackRequiresUserGesture = false
        fragmentWV.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                isLoading = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                isLoading = false
            }

            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                if (isLoading) Toast.makeText(context, "Ошибка загрузки страницы", Toast.LENGTH_SHORT).show()
            }

            @Deprecated("Deprecated in Java")
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                url?.let { view?.loadUrl(it) }
                return true
            }
        }
        fragmentWV.loadUrl(viewPagerItem.url)
    }

}