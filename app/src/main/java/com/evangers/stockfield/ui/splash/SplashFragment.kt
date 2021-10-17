package com.evangers.stockfield.ui.splash

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.evangers.stockfield.R
import com.evangers.stockfield.databinding.FragmentSplashBinding
import com.evangers.stockfield.ui.base.NavRootController
import com.evangers.stockfield.ui.base.NavRootControllerImpl
import com.evangers.stockfield.ui.base.StockFieldFragment
import com.evangers.stockfield.ui.util.onBackPressedDispatcher
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment @Inject constructor(
): StockFieldFragment(R.layout.fragment_splash),
    NavRootController by NavRootControllerImpl() {

    private val viewModel: SplashViewModel by viewModels()

    lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUi()
        initBinding()
        viewModel.start()
    }


    override fun bindView(view: View) {
        binding = FragmentSplashBinding.bind(view)
    }

    override fun initUi() {
    }

    override fun initBinding() {
        viewModel.liveData.observe(this, { state ->
            state.navToMainActivity?.getValueIfNotHandled()?.let {
                val action = SplashFragmentDirections.actionSplashFragmentToHomeFragment()
                findNavController().navigate(action)
            }
            state.showAlertDialog?.getValueIfNotHandled()?.let {
                alertView(it)
            }
            state.showUnknownErrorAlertDialog?.getValueIfNotHandled()?.let {
                alertView(getString(R.string.commonErrorMessage))
            }
            state.showServerLoadingMessage?.getValueIfNotHandled()?.let {
                var dot = ""
                for (i in 0..it) {
                    dot += ". "
                }
                val text = "${getString(R.string.checkingServerState)}$dot"
                binding.loadingStatus.text = text
            }
            state.showDataLoadingMessage?.getValueIfNotHandled()?.let {
                var dot = ""
                for (i in 0..it) {
                    dot += ". "
                }
                val text = "${getString(R.string.loadingData)}$dot"
                binding.loadingStatus.text = text
            }
        })
    }

    private fun alertView(message: String) {
        AlertDialog.Builder(requireContext())
            .setCancelable(false)
            .setMessage(message)
            .setPositiveButton(R.string.confirm) { dialog, i ->
                requireActivity().finish()
                dialog.dismiss()
            }.show()
    }

}