package com.bmc.bancamovilapp.di.logIn

import com.bmc.bancamovilapp.di.app.scopes.RepositoryScope
import com.bmc.core.di.component.CoreComponent
import com.bmc.domain.logIn.repository.LogInRepository
import dagger.Component

@RepositoryScope
@Component(
    modules = [LogInRepositoryModule::class],
    dependencies = [CoreComponent::class]
)
interface LogInRepositoryComponent {
    fun logInRepository(): LogInRepository
}