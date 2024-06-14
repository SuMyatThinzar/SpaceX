package com.smtz.betterhr.codetest.spacex.activities

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import com.smtz.betterhr.codetest.spacex.databinding.ActivityArticleBinding

class ArticleActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityArticleBinding.inflate(layoutInflater)
    }

    private var url: String = ""

    companion object {
        private const val EXTRA_WEB_URL = "EXTRA WEB URL"

        fun newIntent(context: Context, url: String) : Intent {
            val intent = Intent(context, ArticleActivity::class.java)
            intent.putExtra(EXTRA_WEB_URL, url)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        url = intent.getStringExtra(EXTRA_WEB_URL)?: ""

        setUpWebView()
    }

    fun setUpWebView() {
        loadWeb()

        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.webViewUrl.reload()
        }

        binding.webViewUrl.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, progress: Int) {
            }
        }

        binding.webViewUrl.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                binding.webViewUrl.loadUrl("file:///android_asset/error.html")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                binding.swipeRefreshLayout.isRefreshing = false
            }
        }
    }

    private fun loadWeb() {
        binding.webViewUrl.settings.javaScriptEnabled = true
        binding.webViewUrl.loadUrl(url)
        binding.swipeRefreshLayout.isRefreshing = true
    }

    override fun onBackPressed() {
        if (binding.webViewUrl.canGoBack()) {
            binding.webViewUrl.goBack()
        } else {
            super.onBackPressed()
        }
    }
}