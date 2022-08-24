package com.bmc.bancamovilapp.di.logIn

import com.bmc.data.repository.logIn.LogInRepositoryImpl
import com.bmc.data.repository.logIn.source.LogInDataSource
import com.bmc.data.repository.logIn.source.remote.LogInRemoteDataSource
import com.bmc.domain.logIn.repository.LogInRepository
import dagger.Module
import dagger.Provides

@Module
class LogInRepositoryModule {

    @Provides
    fun provideLogInRepository(
        logInDataSource: LogInDataSource
    ): LogInRepository {
        return LogInRepositoryImpl(logInDataSource)
    }

    @Provides
    fun provideLogInRemoteDataSource(): LogInDataSource {
        return LogInRemoteDataSource()
    }
}