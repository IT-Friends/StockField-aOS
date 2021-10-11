package com.evangers.stockfield.data.di

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SharedPreferencesModule {

    private const val SHARED_PREFERENCE = "UserPreferences"

    @JvmStatic
    @Provides
    @Singleton
    fun providesSharedPreferences(
        @ApplicationContext context: Context
    ): SharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCE,
        Context.MODE_PRIVATE
    )
}