package com.bmc.bancamovilapp.splash.ui

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.bancamovilapp.R
import com.bmc.bancamovilapp.databinding.ActivitySplashBinding
import com.bmc.bancamovilapp.splash.di.DaggerSplashComponent
import com.bmc.bancamovilapp.splash.di.SplashModule
import com.bmc.core.base.BaseActivity
import com.bmc.navigation.FeatureNavigationHelper
import java.util.*

class SplashActivity : BaseActivity<SplashActivityState, SplashViewModel, ActivitySplashBinding>() {

    override val layoutId = R.layout.activity_splash
    override val bindingVariable = 0
    override val navControllerId = 0

    override val interactionTimeout: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition { true }
        startSomeNextActivity()
    }

    override fun onStateChange(state: SplashActivityState) {
        state.goToModule?.consume()?.let {
            FeatureNavigationHelper.navigate(this, it)
            finish()
        }
    }

    override fun onInitDependencyInjection() {
        DaggerSplashComponent
            .builder()
            .coreComponent(BancaMovilApplication.coreComponent(applicationContext))
            .splashModule(SplashModule(this))
            .build()
            .inject(this)
    }

    private fun startSomeNextActivity() {
        viewModel.goToInitialModule()
    }
}
