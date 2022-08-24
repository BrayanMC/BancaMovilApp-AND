package com.bmc.core.di.component

import android.content.Context
import dagger.Component
import com.bmc.core.di.modules.ContextModule
import javax.inject.Singleton

/**
 * Core component that all module's components depend on.
 */
@Singleton
@Component(
    modules = [
        ContextModule::class
    ]
)
interface CoreComponent {
    fun context(): Context
}
