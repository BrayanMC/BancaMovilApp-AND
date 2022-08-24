package com.bmc.bancamovilapp.di.app.modules

import android.content.Context
import com.bmc.bancamovilapp.BancaMovilApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule {

    @Provides
    fun provideContext(application: BancaMovilApplication): Context = application.applicationContext
}
