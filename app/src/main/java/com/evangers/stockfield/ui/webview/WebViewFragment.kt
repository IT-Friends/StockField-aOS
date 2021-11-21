package com.evangers.stockfield.ui.webview

import android.os.Bundle
import android.view.View
import android.webkit.WebSettings
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentWebviewBinding
import com.evangers.stockfield.ui.base.NavRootController
import com.evangers.stockfield.ui.base.NavRootControllerImpl
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WebViewFragment @Inject constructor(
) : StockFieldFragment(R.layout.fragment_webview),
    NavRootController by NavRootControllerImpl() {

    val viewModel: WebViewViewModel by viewModels()

    lateinit var binding: FragmentWebviewBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedSf(view, savedInstanceState)
        initUi()
        initBinding()
    }


    override fun bindView(view: View) {
        binding = FragmentWebviewBinding.bind(view)
    }

    override fun initUi() {
        this.onBackPressedDispatcher { viewModel.onBackPressed() }
        with(binding) {
            toolbar.backButton.setOnClickListener {
                viewModel.onBackPressed()
            }
            toolbar.mainTitle.isVisible = false
            toolbar.subTitle.isVisible = false
            webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE

            webView.isVerticalScrollBarEnabled = false

            webView.settings.javaScriptEnabled = true // TODO 안전하게 자바 스크립트 호출할 수 있는 방법 강구
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webView.settings.domStorageEnabled = true
            webView.settings.databaseEnabled = true
            webView.settings.javaScriptCanOpenWindowsAutomatically = true

            val url: String = arguments?.getString("url") ?: ""
            webView.loadUrl(url)
        }
    }

    override fun initBinding() {
        viewModel.liveData.observe(this, { state ->
            state.navToBack?.getValueIfNotHandled()?.let {
                findNavController().popBackStack()
            }
        })
    }


}