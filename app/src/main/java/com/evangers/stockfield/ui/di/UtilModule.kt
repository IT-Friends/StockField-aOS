package com.evangers.stockfield.ui.di

import android.content.Context
import com.evangers.stockfield.domain.usecase.HasUsedOverHoneymoon
import com.evangers.stockfield.ui.util.AppOpenManager
import com.evangers.stockfield.ui.util.FirebaseRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UtilModule {

    @Singleton
    @Provides
    fun provideAppOpenManager(
        @ApplicationContext appContext: Context,
        firebaseRemote: FirebaseRemote,
        hasUsedOverHoneymoon: HasUsedOverHoneymoon
    ): AppOpenManager {
        return AppOpenManager(
            appContext as StockFieldApp,
            firebaseRemote,
            hasUsedOverHoneymoon
        )
    }

    @Singleton
    @Provides
    fun providesFirebaseRemoteConfig(
    ): FirebaseRemote {
        return FirebaseRemote()
    }

}