package com.evangers.stockfield.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.usecase.GetFund
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFund: GetFund
) : ViewModel() {

    private val homeState = HomeState()
    val liveData = MutableLiveData(homeState)

    fun start() {
        viewModelScope.launch {
            getFund(GetFund.Request("ARKK"))
                .collect {
                    when (it) {
                        is GetFund.Response.Success -> {
                            homeState.update(HomeAction.UpdateFund(it.fund))
                            liveData.postValue(homeState)
                        }
                        is GetFund.Response.Failure -> {
                            homeState.update(HomeAction.ShowToast(it.errorMessage))
                            liveData.postValue(homeState)

                        }
                    }
                }
        }

    }
}