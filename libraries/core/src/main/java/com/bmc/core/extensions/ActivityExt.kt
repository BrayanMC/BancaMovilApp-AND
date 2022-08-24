package com.bmc.core.extensions

import android.app.Activity
import com.bmc.core.interfaces.CoreComponentProvider

fun Activity.coreComponent() = (applicationContext as? CoreComponentProvider)?.provideCoreComponent()
    ?: throw IllegalStateException("CoreComponentProvider not implemented: $applicationContext")
