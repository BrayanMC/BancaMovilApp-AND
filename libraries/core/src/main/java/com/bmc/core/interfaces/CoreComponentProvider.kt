package com.bmc.core.interfaces

import com.bmc.core.di.component.CoreComponent

interface CoreComponentProvider {
    fun provideCoreComponent(): CoreComponent
}
