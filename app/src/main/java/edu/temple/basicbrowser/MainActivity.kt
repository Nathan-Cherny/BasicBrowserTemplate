package edu.temple.basicbrowser

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {

    private lateinit var urlEditText: EditText
    private lateinit var goButton: ImageButton
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        urlEditText = findViewById(R.id.urlEditText)
        goButton = findViewById(R.id.goButton)
        goButton.apply {
            setOnClickListener {
                var text = urlEditText.text.toString()
                text = parseUrl(text)
                webView.loadUrl(text)
            }
        }
        webView = findViewById(R.id.webView)
        webView.settings.javaScriptEnabled = true

        // Allow your browser to intercept hyperlink clicks
        webView.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                url?.run{
                    urlEditText.setText(this)
                }
            }
        }

    }
}

fun parseUrl(url : String) : String{
    if(url.startsWith("https://")) return url
    else return "https://$url"
}