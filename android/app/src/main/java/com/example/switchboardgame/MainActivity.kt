package com.example.switchboardgame

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.webkit.WebViewAssetLoader // Added import
import android.webkit.WebViewClient // Added import
import com.example.switchboardgame.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myWebView: WebView = binding.webview

        // Configure WebViewAssetLoader
        val assetLoader = WebViewAssetLoader.Builder()
            .addPathHandler("/assets/", WebViewAssetLoader.AssetsPathHandler(this))
            .build()

        // Configure WebView settings
        myWebView.settings.javaScriptEnabled = true
        // myWebView.settings.allowFileAccessFromFileURLs = true // No longer needed with WebViewAssetLoader
        // myWebView.settings.allowUniversalAccessFromFileURLs = true // No longer needed with WebViewAssetLoader
        myWebView.settings.mediaPlaybackRequiresUserGesture = false
        myWebView.settings.domStorageEnabled = true

        // Set a custom WebViewClient to use WebViewAssetLoader
        myWebView.webViewClient = object : WebViewClient() {
            override fun shouldInterceptRequest(
                view: WebView,
                request: WebResourceRequest
            ): WebResourceResponse? {
                return assetLoader.shouldInterceptRequest(request.url)
            }
        }

        // Add the JavaScript interface
        myWebView.addJavascriptInterface(WebAppInterface(this), "Android")

        // Load the local HTML file using the WebViewAssetLoader's path
        myWebView.loadUrl("https://appassets.androidplatform.net/assets/index.html")
    }
}
