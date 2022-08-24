package com.bmc.login

import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.core.base.BaseActivity
import com.bmc.login.databinding.ActivityLogInBinding
import com.bmc.login.di.DaggerLogInComponent
import com.bmc.login.di.LogInModule
import com.bmc.login.model.LogInState
import com.bmc.login.viewmodel.LogInViewModel
import com.bmc.navigation.FeatureModules
import com.bmc.navigation.FeatureNavigationHelper

class LogInActivity : BaseActivity<LogInState, LogInViewModel, ActivityLogInBinding>() {

    override val layoutId: Int = R.layout.activity_log_in
    override val bindingVariable: Int = BR.viewModel
    override val navControllerId: Int = R.id.fragment_container_view_log_in

    override val interactionTimeout: Long? = null

    override fun onStateChange(state: LogInState) {
        state.navigateToHome?.consume()?.let {
            FeatureNavigationHelper.navigate(this, FeatureModules.homeFeatureModule)
            finish()
        }
    }

    override fun onInitDependencyInjection() {
        DaggerLogInComponent
            .builder()
            .logInRepositoryComponent(
                BancaMovilApplication.logInRepositoryComponent(
                    applicationContext
                )
            )
            .logInModule(LogInModule(this))
            .build()
            .inject(this)
    }
}