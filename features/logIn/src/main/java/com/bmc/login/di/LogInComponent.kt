package com.bmc.login.di

import com.bmc.bancamovilapp.di.app.scopes.FeatureScope
import com.bmc.bancamovilapp.di.logIn.LogInRepositoryComponent
import com.bmc.login.LogInActivity
import com.bmc.login.screens.credentials.LogInCredentialsFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [LogInModule::class],
    dependencies = [LogInRepositoryComponent::class]
)
interface LogInComponent {

    fun inject(logInActivity: LogInActivity)

    fun inject(logInCredentialsFragment: LogInCredentialsFragment)
}
