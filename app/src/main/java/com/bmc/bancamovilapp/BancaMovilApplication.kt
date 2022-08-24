package com.bmc.bancamovilapp

import android.app.Application
import android.content.Context
import com.bmc.bancamovilapp.di.app.components.DaggerAppComponent
import com.bmc.bancamovilapp.di.home.DaggerProductRepositoryComponent
import com.bmc.bancamovilapp.di.home.ProductRepositoryComponent
import com.bmc.bancamovilapp.di.home.ProductRepositoryModule
import com.bmc.bancamovilapp.di.logIn.DaggerLogInRepositoryComponent
import com.bmc.bancamovilapp.di.logIn.LogInRepositoryComponent
import com.bmc.bancamovilapp.di.logIn.LogInRepositoryModule
import com.bmc.core.di.component.CoreComponent
import com.bmc.core.di.component.DaggerCoreComponent
import com.bmc.core.di.component.DaggerNetworkComponent
import com.bmc.core.di.component.NetworkComponent
import com.bmc.core.di.modules.ContextModule
import com.bmc.core.di.modules.NetworkModule
import com.bmc.core.interfaces.CoreComponentProvider
import timber.log.Timber

class BancaMovilApplication : Application(), CoreComponentProvider {

    companion object {

        /**
         * Obtain core dagger component.
         *
         * @param context The application context
         */
        @JvmStatic
        fun coreComponent(context: Context) =
            (context.applicationContext as? BancaMovilApplication)?.coreComponent

        @JvmStatic
        fun networkComponent(context: Context) =
            (context.applicationContext as? BancaMovilApplication)?.networkComponent

        @JvmStatic
        fun logInRepositoryComponent(context: Context) =
            (context.applicationContext as? BancaMovilApplication)?.logInRepositoryComponent

        @JvmStatic
        fun productRepositoryComponent(context: Context) =
            (context.applicationContext as? BancaMovilApplication)?.productRepositoryComponent
    }

    lateinit var coreComponent: CoreComponent
    lateinit var networkComponent: NetworkComponent
    lateinit var logInRepositoryComponent: LogInRepositoryComponent
    lateinit var productRepositoryComponent: ProductRepositoryComponent

    override fun provideCoreComponent(): CoreComponent = coreComponent

    private fun initCoreDependencyInjection() {
        coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(this))
            .build()
    }

    private fun initNetworkDependencyInjection() {
        networkComponent = DaggerNetworkComponent
            .builder()
            .contextModule(ContextModule(this))
            .networkModule(NetworkModule())
            .build()
    }

    private fun initAppDependencyInjection() {
        DaggerAppComponent
            .builder()
            .networkComponent(networkComponent)
            .coreComponent(coreComponent)
            .build()
            .inject(this)
    }

    private fun initLogInRepositoryComponent() {
        logInRepositoryComponent = DaggerLogInRepositoryComponent
            .builder()
            .coreComponent(coreComponent)
            .logInRepositoryModule(LogInRepositoryModule())
            .build()
    }

    private fun initProductRepositoryComponent() {
        productRepositoryComponent = DaggerProductRepositoryComponent
            .builder()
            .coreComponent(coreComponent)
            .productRepositoryModule(ProductRepositoryModule())
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        //MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        initCoreDependencyInjection()
        initNetworkDependencyInjection()
        initAppDependencyInjection()
        initLogInRepositoryComponent()
        initProductRepositoryComponent()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}