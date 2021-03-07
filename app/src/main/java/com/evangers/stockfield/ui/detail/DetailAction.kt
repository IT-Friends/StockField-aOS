package com.evangers.stockfield.ui.detail

sealed class DetailAction {
    class ShowToast(val text: String) : DetailAction()
    class UpdateLoadingState(val isLoading: Boolean) : DetailAction()
}