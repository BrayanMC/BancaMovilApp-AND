package com.bmc.navigation

object FeatureModules {

    val logInFeatureModule = FeatureModule(
        name = "logIn",
        injectorName = "com.bmc.login.di.LoginInjector",
        activityName = "com.bmc.login.LogInActivity"
    )

    val homeFeatureModule = FeatureModule(
        name = "home",
        injectorName = "com.bmc.home.di.HomeInjector",
        activityName = "com.bmc.home.HomeActivity"
    )
}
