package com.bmc.bancamovilapp.splash.ui

import androidx.lifecycle.viewModelScope
import com.bmc.common.liveData.SingleEvent
import com.bmc.core.base.BaseViewModel
import com.bmc.navigation.FeatureModules
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel : BaseViewModel<SplashActivityState>() {

    override val initialState = SplashActivityState()

    init {
        state = initialState
    }

    fun goToInitialModule() {
        viewModelScope.launch {
            delay(5000)
            state = state.copy(goToModule = SingleEvent(FeatureModules.logInFeatureModule))
        }
    }

    /*private fun isSignupSessionActive(): Boolean {
        return signupSharedPrefs.getSessionId().isNotEmpty()
    }*/

    //private fun isLoginAccountActive() = commonSharedPreferences.isAccountActive()
}
