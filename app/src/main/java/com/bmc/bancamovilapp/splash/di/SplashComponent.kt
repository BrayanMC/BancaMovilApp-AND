package com.bmc.bancamovilapp.splash.di

import dagger.Component
import com.bmc.bancamovilapp.di.app.scopes.FeatureScope
import com.bmc.bancamovilapp.splash.ui.SplashActivity
import com.bmc.core.di.component.CoreComponent

@FeatureScope
@Component(
    modules = [SplashModule::class],
    dependencies = [CoreComponent::class]
)
interface SplashComponent {

    fun inject(splashActivity: SplashActivity)
}
