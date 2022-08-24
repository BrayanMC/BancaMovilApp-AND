package com.bmc.login.di

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bmc.core.base.BaseFeature
import com.bmc.core.extensions.sharedViewModel
import com.bmc.core.extensions.viewModel
import com.bmc.domain.logIn.usecase.LogInUseCase
import com.bmc.login.screens.credentials.viewmodel.LogInCredentialsViewModel
import com.bmc.login.viewmodel.LogInViewModel
import dagger.Module
import dagger.Provides

@Module
class LogInModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val baseFeature: BaseFeature
) {

    @Provides
    fun provideLogInViewModel(
    ): LogInViewModel {
        val factory = { LogInViewModel() }
        return baseFeature.let {
            if (it is Fragment) it.sharedViewModel { factory() }
            else (it as FragmentActivity).viewModel { factory() }
        }
    }

    @Provides
    fun provideLogInCredentialsViewModel(
        logInUseCase: LogInUseCase
    ) = (baseFeature as Fragment).viewModel {
        LogInCredentialsViewModel(logInUseCase)
    }
}