package com.evangers.stockfield.ui.fundholdings

interface HomeController {
    fun onDateUpdate(text: String)
    fun onUpdateLoadingState(isLoading: Boolean)
}