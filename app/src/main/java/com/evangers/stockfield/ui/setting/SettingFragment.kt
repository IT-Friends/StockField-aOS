package com.evangers.stockfield.ui.setting

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentSettingBinding
import com.evangers.stockfield.ui.base.NavRootController
import com.evangers.stockfield.ui.base.NavRootControllerImpl
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingFragment @Inject constructor(
) : StockFieldFragment(R.layout.fragment_setting),
    NavRootController by NavRootControllerImpl() {

    private val viewModel: SettingViewModel by viewModels()

    lateinit var binding: FragmentSettingBinding

    override fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) {
        super.onViewCreatedSf(view, savedInstanceState)
        initUi()
        initBinding()
    }


    override fun bindView(view: View) {
        binding = FragmentSettingBinding.bind(view)
    }

    override fun initUi() {
        this.onBackPressedDispatcher { viewModel.onBackPressed() }
        with(binding) {
            bannerWebView.settings.javaScriptEnabled = true
            val script = """
                <script src="https://ads-partners.coupang.com/g.js"></script>
                <script>
                	new PartnersCoupang.G(
                {
                "id":527363,
                "template":"carousel",
                "trackingCode":"AF6001029",
                "subId":"sharesource",
                "width":"100%",
                "height":"50"
                }
                );
                </script>
            """.trimIndent()
            val html = "<div>$script</div>"
            bannerWebView.loadData(html, "text/html", "utf-8")
            toolbar.backButton.setOnClickListener {
                viewModel.onBackPressed()
            }
            toolbar.mainTitle.text = getString(R.string.more)
            toolbar.subTitle.isVisible = false
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