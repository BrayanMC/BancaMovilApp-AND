package com.bmc.bancamovilapp.splash.di

import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.bmc.bancamovilapp.splash.ui.SplashActivity
import com.bmc.bancamovilapp.splash.ui.SplashViewModel
import com.bmc.core.extensions.viewModel
import dagger.Module
import dagger.Provides

@Module
class SplashModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val activity: SplashActivity
) {
    @Provides
    fun provideSplashViewModel(): SplashViewModel {
        return (activity as AppCompatActivity).viewModel {
            SplashViewModel()
        }
    }
}
