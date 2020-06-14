package com.zedneypfe.loadenpfe.fragments.client

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.zedneypfe.loadenpfe.R
import com.zedneypfe.loadenpfe.fragments.VerifSignInViewModel
import kotlinx.android.synthetic.main.fragment_envoyer_demande.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class EnvoyerDemandeFragment : Fragment() {


    private lateinit var viewModel: EnvoyerDemandeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_envoyer_demande, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(EnvoyerDemandeViewModel::class.java)

        viewModel.getEnvouyerDemande(webview)


        //i deplace it in the ViewModel to respect the mvvm
        /* val url = "https://form.jotform.com/201593494436562"
         webview.settings.javaScriptEnabled = true
         webview.settings.loadWithOverviewMode = true
         webview.settings.useWideViewPort = true
         webview.settings.builtInZoomControls = false
         webview.settings.pluginState = WebSettings.PluginState.ON
         webview.webViewClient = WebViewClient()

         webview.loadUrl(url)*/
    }
}