package com.evangers.stockfield.ui.detail

import com.evangers.stockfield.ui.base.Event

interface DetailStateBind {
    var toastMessage: Event<String>?
    var isLoading: Event<Boolean>?
    var updateTitle: Event<Pair<String, String>>?
}

class DetailState(
    override var toastMessage: Event<String>? = null,
    override var isLoading: Event<Boolean>? = null,
    override var updateTitle: Event<Pair<String, String>>? = null
) : DetailStateBind {

    fun update(action: DetailAction) {
        when (action) {
            is DetailAction.ShowToast -> {
                toastMessage = Event(action.text)
            }
            is DetailAction.UpdateLoadingState -> {
                isLoading = Event(action.isLoading)
            }
            is DetailAction.UpdateTitle -> {
                updateTitle = Event(action.titleAndSub)
            }

        }
    }
}