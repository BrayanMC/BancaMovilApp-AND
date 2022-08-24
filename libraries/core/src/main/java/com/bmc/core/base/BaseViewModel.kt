package com.bmc.core.base

import android.os.Bundle
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.bmc.common.liveData.SingleEvent

abstract class BaseViewModel<VS : BaseViewState> : ViewModel() {

    abstract val initialState: VS

    val viewState: MediatorLiveData<VS> = MediatorLiveData()
    val navigationEvent: MutableLiveData<SingleEvent<NavController.() -> Any>> = MutableLiveData()
    val onExpiredSessionEvent: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()
    val onGenericErrorEvent: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()
    val onTimeoutErrorEvent: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()
    val onSessionTimeoutEvent: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()
    val onOtherSessionLoggedEvent: MutableLiveData<SingleEvent<Boolean>> = MutableLiveData()

    var state: VS
        get() = viewState.value ?: initialState
        set(value) = viewState.setValue(value) // Sets the value synchronously

    var stateAsync: VS
        get() = state
        set(value) = viewState.postValue(value) // Sets the value asynchronously

    fun navigateTo(route: BaseRouteGraph, args: Bundle? = null) {
        withNavController { navigate(route.graph, args) }
    }

    fun navigateTo(
        route: BaseRouteDestination,
        args: Bundle? = null,
        clearStack: Boolean = false,
        navOptions: NavOptions? = null
    ) {
        when {
            route is BaseRouteDestination.Back -> withNavController { popBackStack() }
            clearStack -> withNavController { popBackStack(route.destination, false) }
            else -> withNavController { navigate(route.destination, args, navOptions) }
        }
    }

    fun navigateTo(route: BaseRouteAction, args: Bundle? = null) {
        withNavController { navigate(route.action, args) }
    }

    open fun onBackPressed() {}

    protected fun withNavController(block: NavController.() -> Any) {
        navigationEvent.postValue(SingleEvent(block))
    }

    open fun clearState() {
        viewState.value = initialState
    }

    /*open fun handleResult(result: Result<*>): Boolean {
        return when (result) {
            is Result.Success<*> -> false
            is Result.Error -> {
                return when (result.messageCode) {
                    ErrorCodes.SESSION_EXPIRED -> {
                        onExpiredSessionEvent.postValue(SingleEvent(true))
                        true
                    }
                    ErrorCodes.RESPONSE_TIMEOUT -> {
                        onTimeoutErrorEvent.postValue(SingleEvent(true))
                        true
                    }
                    ErrorCodes.SESSION_TIMEOUT -> {
                        onSessionTimeoutEvent.postValue(SingleEvent(true))
                        true
                    }
                    ErrorCodes.UNAVAILABLE -> {
                        true
                    }
                    ErrorCodes.ANOTHER_SESSION_LOGGED -> {
                        onOtherSessionLoggedEvent.postValue(SingleEvent(true))
                        true
                    }
                    else -> {
                        onGenericErrorEvent.postValue(SingleEvent(true))
                        true
                    }
                }
            }
        }
    }*/
}
