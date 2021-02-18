package com.evangers.stockfield.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
abstract class SfFragment constructor(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {

    @Deprecated(
        message = "필요시 onPostCreateView() override할 것",
        level = DeprecationLevel.ERROR
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)?.apply {
            onPostCreateView(savedInstanceState)
        }
    }

    @Deprecated(
        message = "onViewCreatedSf() override 할 것",
        level = DeprecationLevel.ERROR
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
        onViewCreatedSf(view, savedInstanceState)
    }

    @Deprecated(
        message = "onDestroyViewSf() override 할 것",
        level = DeprecationLevel.ERROR
    )
    override fun onDestroyView() {
        onDestroyViewSf()
        unbindView()
        super.onDestroyView()
    }

    protected open fun onViewCreatedSf(view: View, savedInstanceState: Bundle?) = Unit
    protected open fun onPostCreateView(savedInstanceState: Bundle?) = Unit
    protected open fun onDestroyViewSf() = Unit
    abstract fun initUi()
    abstract fun initBinding()
    abstract fun bindView(view: View)
    abstract fun unbindView()


}