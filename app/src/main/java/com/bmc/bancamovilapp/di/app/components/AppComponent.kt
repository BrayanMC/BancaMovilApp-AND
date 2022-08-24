package com.bmc.bancamovilapp.di.app.components

import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.bancamovilapp.di.app.modules.AppModule
import com.bmc.bancamovilapp.di.app.scopes.AppScope
import com.bmc.core.di.component.CoreComponent
import com.bmc.core.di.component.NetworkComponent
import dagger.Component

@AppScope
@Component(
    dependencies = [CoreComponent::class, NetworkComponent::class],
    modules = [AppModule::class]
)
interface AppComponent {

    /**
     * Inject dependencies on application.
     *
     * @param application The sample application.
     */
    fun inject(application: BancaMovilApplication)
}
