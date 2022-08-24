package com.bmc.bancamovilapp.splash.ui

import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewState
import com.bmc.navigation.FeatureModule

data class SplashActivityState(
    val goToModule: SingleEvent<FeatureModule>? = null,
    val isLoading: Boolean = false
) : BaseViewState