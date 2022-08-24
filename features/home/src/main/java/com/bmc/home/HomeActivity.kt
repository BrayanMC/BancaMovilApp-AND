package com.bmc.home

import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.core.base.BaseActivity
import com.bmc.home.databinding.ActivityHomeBinding
import com.bmc.home.di.DaggerHomeComponent
import com.bmc.home.di.HomeModule
import com.bmc.home.model.HomeState
import com.bmc.home.viewmodel.HomeViewModel
import com.bmc.navigation.FeatureModules
import com.bmc.navigation.FeatureNavigationHelper

class HomeActivity : BaseActivity<HomeState, HomeViewModel, ActivityHomeBinding>() {

    override val layoutId: Int = R.layout.activity_home
    override val bindingVariable: Int = BR.viewModel
    override val navControllerId: Int = R.id.fragment_container_view_home

    override fun exitSession() {
        FeatureNavigationHelper.navigate(this, FeatureModules.logInFeatureModule)
        finishAffinity()
    }

    override fun onStateChange(state: HomeState) {
    }

    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .productRepositoryComponent(
                BancaMovilApplication.productRepositoryComponent(
                    applicationContext
                )
            )
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }
}