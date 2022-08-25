package com.mahostudios.netc

import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.http.SslError
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Toast
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

/**
 * A simple [Fragment] subclass.
 * Use the [Speed.newInstance] factory method to
 * create an instance of this fragment.
 */
class Speed : Fragment() {

    lateinit var webView : WebView
    lateinit var swipe : SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        var v : View = inflater.inflate(R.layout.fragment_speed2, container, false)
        swipe = v.findViewById(R.id.swipe_refresh)
        webView = v.findViewById<View>(R.id.webv) as WebView

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                swipe.isRefreshing = false
            }
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl(url)
                return true
            }
            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                handler?.proceed()
            }

        }
        webView.webChromeClient = object : WebChromeClient(){

        }
        webView.settings.userAgentString = "Mozilla/5.0 (Linux; Android 9; Pixel Build/PQ3A.190801.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/77.0.3865.73 Mobile Safari/537.36 WebViewApp Foo/1"
        webView.settings.javaScriptEnabled = true
        webView.settings.javaScriptCanOpenWindowsAutomatically = true
        webView.settings.setSupportMultipleWindows(false)
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        webView.settings.setAppCacheEnabled(true)
        webView.settings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
        webView.settings.allowUniversalAccessFromFileURLs = true
        webView.settings.domStorageEnabled = true
        webView.settings.builtInZoomControls = true
        webView.settings.displayZoomControls = false

        val dialogbuilder = AlertDialog.Builder(context)
        dialogbuilder.setTitle("AVISO")
                .setMessage("La página a cargar puede consumir de su plan de datos. Se recomienda usar conectado a una red WI-FI. Desea continuar?")
                .setNegativeButton("NO"){_,_ ->

                }
                .setPositiveButton("Si"){_,_ ->
                    webView.loadUrl("https://fast.com")
                }
                .setCancelable(false)
        val dialog = dialogbuilder.create()
        dialog.show()


//        webView.loadUrl("https://fast.com")


        swipe.setOnRefreshListener {
            val context : Context = this.requireContext()
            if(!isNetworkAvaliable(context)){
                Toast.makeText(context, "No hay conexión", Toast.LENGTH_SHORT).show()
            }
            webView.reload()
        }
        return v
    }

    fun isNetworkAvaliable(context : Context) : Boolean{
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }


}