package com.evangers.stockfield.ui.setting

import android.content.res.Resources
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.BuildConfig
import com.evangers.stockfield.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : ViewModel(), SettingsController {

    private val state = SettingState()
    val liveData = MutableLiveData<SettingStateBind>(state)

    fun start() {
        state.update(
            SettingAction.SettingsUpdated(
                SettingsItem.Calculator(),
                SettingsItem.Version(versionText = "v${BuildConfig.VERSION_NAME}"),
                SettingsItem.OpenSource()
            )
        )
        liveData.postValue(state)
    }

    fun onBackPressed() {
        state.update(SettingAction.NavToBack)
        liveData.postValue(state)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }

    override fun onOpenSourceLicenseClicked() {
        state.update(SettingAction.NavToOpenSource)
        liveData.postValue(state)
    }

    override fun onCalculatorClicked() {
        state.update(SettingAction.NavToCalculator)
        liveData.postValue(state)
    }
}