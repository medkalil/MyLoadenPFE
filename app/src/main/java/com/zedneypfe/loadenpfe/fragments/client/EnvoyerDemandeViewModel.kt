package com.zedneypfe.loadenpfe.fragments.client

import android.app.Application
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.zedneypfe.loadenpfe.Model.authModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class EnvoyerDemandeViewModel(application: Application) : AndroidViewModel(application) {


   // val proccess_envoyerdemande = MutableLiveData<Boolean>()


    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    fun getEnvouyerDemande(webview: WebView) {
        coroutineScope.launch {
            val url = "https://form.jotform.com/201593494436562"
            webview.settings.javaScriptEnabled = true
            webview.settings.loadWithOverviewMode = true
            webview.settings.useWideViewPort = true
            webview.settings.builtInZoomControls = false
            webview.settings.pluginState = WebSettings.PluginState.ON
            webview.webViewClient = WebViewClient()

            webview.loadUrl(url)

         //   proccess_envoyerdemande.value=true
        }//coroutineScope.launch
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}