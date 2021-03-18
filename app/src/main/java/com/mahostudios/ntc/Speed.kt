package com.mahostudios.ntc

import android.content.Context
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
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
    ): View? {
        // Inflate the layout for this fragment
        val v : View = inflater.inflate(R.layout.fragment_speed2, container, false)
        swipe = v.findViewById(R.id.swipe_refresh)
        webView = v.findViewById<View>(R.id.webv) as WebView

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {
                view?.loadUrl("http://fast.com")
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                swipe.isRefreshing = false
            }
        }

        webView.settings.javaScriptEnabled = true
        webView.loadUrl("http://fast.com")


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