package com.bmc.login.screens.credentials

import android.os.Bundle
import androidx.lifecycle.Observer
import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.common.extensions.toast
import com.bmc.core.base.BaseFragment
import com.bmc.login.BR
import com.bmc.login.R
import com.bmc.login.databinding.FragmentLogInCredentialsBinding
import com.bmc.login.di.DaggerLogInComponent
import com.bmc.login.di.LogInModule
import com.bmc.login.model.LogInState
import com.bmc.login.screens.credentials.model.LogInCredentialsState
import com.bmc.login.screens.credentials.viewmodel.LogInCredentialsViewModel
import com.bmc.login.viewmodel.LogInViewModel
import javax.inject.Inject

class LogInCredentialsFragment :
    BaseFragment<LogInCredentialsState, LogInCredentialsViewModel, FragmentLogInCredentialsBinding>() {

    override val layoutId: Int = R.layout.fragment_log_in_credentials
    override val bindingVariable: Int = BR.viewModel
    override val mainNavController: Int = R.id.log_in_nav_graph

    @Inject
    lateinit var sharedViewModel: LogInViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeSharedState(::onSharedStateChange)
    }

    override fun onStateChange(state: LogInCredentialsState) {
        state.form.let {
            if (it.errorMessage.isNotEmpty()) {
                context?.toast(it.errorMessage)
            }
        }

        state.onLogInSuccess?.consume()?.let {
            sharedViewModel.onLogInSuccess()
        }
    }

    override fun onInitDependencyInjection() {
        DaggerLogInComponent
            .builder()
            .logInRepositoryComponent(BancaMovilApplication.logInRepositoryComponent(requireContext()))
            .logInModule(LogInModule(this))
            .build()
            .inject(this)
    }

    private fun observeSharedState(callback: (state: LogInState) -> Unit) {
        val observer = Observer<LogInState> { state -> callback.invoke(state) }
        sharedViewModel.viewState.observe(viewLifecycleOwner, observer)
        observer.onChanged(sharedViewModel.state)
    }

    private fun onSharedStateChange(state: LogInState) {
    }
}