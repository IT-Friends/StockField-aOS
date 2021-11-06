package com.evangers.stockfield.ui.setting

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor(
) : ViewModel() {

    private val state = SettingState()
    val liveData = MutableLiveData<SettingStateBind>(state)

    fun onBackPressed() {
        state.update(SettingAction.NavToBack)
        liveData.postValue(state)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}