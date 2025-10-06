package styleai.features.interactive_svg

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebResourceResponse
import android.webkit.WebView
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.webkit.WebViewAssetLoader
import androidx.webkit.WebViewAssetLoader.AssetsPathHandler
import androidx.webkit.WebViewClientCompat
import androidx.webkit.WebViewCompat
import styleai.core.features.interactive_svg.PanoramaActivity


@SuppressLint("RequiresFeature")
@Suppress("SetJavaScriptEnabled")
@Composable
actual fun PanoramaImageView(modifier: Modifier) {
    val context = LocalContext.current
    context.startActivity(Intent(context, PanoramaActivity::class.java))
    AndroidView(
        factory = { context ->

            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                settings.domStorageEnabled = true


                val assetLoader: WebViewAssetLoader? = WebViewAssetLoader.Builder()
                    .setDomain("appassets.androidplatform.net")
                    .addPathHandler("/", AssetsPathHandler(context))
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
        },
        modifier = modifier
    )
}