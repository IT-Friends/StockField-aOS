package com.evangers.stockfield.ui.fundholdings

interface HomeController {
    fun onDateUpdate(pair: Pair<Int, String>)
    fun onUpdateLoadingState(isLoading: Boolean)
    fun onStockClicked(ticker: String, displayName: String)
}