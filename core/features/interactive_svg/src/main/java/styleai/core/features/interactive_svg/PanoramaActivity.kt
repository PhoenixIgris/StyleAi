package styleai.core.features.interactive_svg

import android.annotation.SuppressLint
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewClientCompat

class PanoramaActivity : ComponentActivity() {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_panorama)
        val webView = findViewById<WebView>(R.id.web_view)
        webView.apply {
            settings.javaScriptEnabled = true
            settings.allowFileAccess = true
            settings.allowContentAccess = true
            settings.domStorageEnabled = true


            val assetLoader: WebViewAssetLoader? = WebViewAssetLoader.Builder()
                .setDomain("appassets.androidplatform.net")
                .addPathHandler("/", WebViewAssetLoader.AssetsPathHandler(context))
                .build()

            val webviewClient = object : WebViewClientCompat() {

                override fun shouldInterceptRequest(
                    view: WebView,
                    request: WebResourceRequest
                ): WebResourceResponse? {
                    return runCatching {
                        assetLoader?.shouldInterceptRequest(request.url)
                    }.getOrNull()
                }
            }

            webViewClient = webviewClient

            webChromeClient = object : WebChromeClient() {
                override fun onConsoleMessage(message: android.webkit.ConsoleMessage?): Boolean {
                    android.util.Log.d("WebViewConsole", message?.message() ?: "")
                    return true
                }
            }

            loadUrl("https://appassets.androidplatform.net/panorama.html")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.web_view)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}