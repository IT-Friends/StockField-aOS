package com.evangers.stockfield.ui.activity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.evangers.stockfield.domain.throwables.ServerIsDownException
import com.evangers.stockfield.domain.usecase.CheckServerState
import com.evangers.stockfield.ui.util.AppOpenManager
import com.google.firebase.crashlytics.FirebaseCrashlytics
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val appOpenManager: AppOpenManager,
    private val checkServerState: CheckServerState
) : ViewModel() {

    private val state = SplashState()
    val liveData = MutableLiveData<SplashStateBind>(state)

    private val handler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        when (throwable) {
            is ServerIsDownException -> {
                state.update(SplashAction.ShowAlertDialog(throwable.noticeMessage))
                liveData.postValue(state)
            }
            else -> {
                state.update(SplashAction.ShowUnknownErrorAlertDialog)
                liveData.postValue(state)
            }
        }
    }

    fun start() {
        viewModelScope.launch(Dispatchers.IO + handler) {
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-7"))
            checkServerState.invoke(Unit)
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-8"))
            appOpenManager.init()
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-9"))
            val response = withContext(Dispatchers.Main) {
                FirebaseCrashlytics.getInstance().recordException(Throwable("app-10"))
                appOpenManager.fetchAd() // 메인쓰레드에서 돌려야 됨
            }
            FirebaseCrashlytics.getInstance().recordException(Throwable("app-11"))
            when (response) {
                is AppOpenManager.Response.LoadSuccess,
                is AppOpenManager.Response.LoadFailure -> { // 오픈 광고 못 불러와도 시작하도록 함
                    FirebaseCrashlytics.getInstance().recordException(Throwable("app-12"))
                    state.update(SplashAction.NavToMainActivity)
                    liveData.postValue(state)
                }
                else -> {
                    FirebaseCrashlytics.getInstance().recordException(Throwable("app-13"))
                }
            }
        }
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}