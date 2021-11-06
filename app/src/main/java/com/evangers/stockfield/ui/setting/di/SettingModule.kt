//package com.evangers.stockfield.ui.setting.di
//
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelStore
//import androidx.lifecycle.ViewModelStoreOwner
//import com.evangers.stockfield.ui.setting.SettingFragment
//import com.evangers.stockfield.ui.setting.SettingsController
//import com.evangers.stockfield.ui.setting.presenter.*
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.android.components.ActivityRetainedComponent
//import dagger.hilt.android.components.FragmentComponent
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//
//@InstallIn(FragmentComponent::class)
//@Module
//class SettingModule {
//
//    @Singleton
//    @Provides
//    fun providesFragment(fragment: SettingFragment): SettingFragment = ViewModelProvider(fragment, )
//
//
//    @Provides
//    fun providesOpenSourcePresenter(fragment: SettingFragment): OpenSourcePresenter =
//        OpenSourcePresenterImpl(fragment.viewModel)
//
//    @Provides
//    fun providesVersionPresenter(): VersionPresenter =
//        VersionPresenterImpl()
//
//    @Provides
//    fun providesCalculatorPresenter(fragment: SettingFragment): CalculatorPresenter =
//        CalculatorPresenterImpl(fragment.viewModel)
//
//    @Provides
//    fun providesPresenter(
//        openSourcePresenterImpl: OpenSourcePresenter,
//        calculatorPresenterImpl: CalculatorPresenter,
//        versionPresenterImpl: VersionPresenter
//    ): Presenter =
//        PresenterImpl(openSourcePresenterImpl, versionPresenterImpl, calculatorPresenterImpl)
//
//}