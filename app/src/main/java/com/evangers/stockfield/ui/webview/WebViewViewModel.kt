package com.evangers.stockfield.ui.webview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import javax.inject.Inject

@HiltViewModel
class WebViewViewModel @Inject constructor(
) : ViewModel() {

    private val state = WebViewState()
    val liveData = MutableLiveData<WebViewStateBind>(state)

    fun onBackPressed() {
        state.update(WebViewAction.NavToBack)
        liveData.postValue(state)
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}