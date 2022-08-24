package com.bmc.core.di.component

import dagger.Component
import com.bmc.core.di.modules.ContextModule
import com.bmc.core.di.modules.NetworkModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        NetworkModule::class, ContextModule::class
    ]
)
interface NetworkComponent {
}
