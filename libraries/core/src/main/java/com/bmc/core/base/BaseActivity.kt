package com.bmc.core.base

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.bmc.common.extensions.hideKeyboard
import com.bmc.common.liveData.ConnectivityLiveData
import javax.inject.Inject

abstract class BaseActivity<VS : BaseViewState, VM : BaseViewModel<VS>, BR : ViewDataBinding> :
    AppCompatActivity(), LifecycleOwner, BaseFeature {

    @Inject
    open lateinit var viewModel: VM
    protected abstract val layoutId: Int
    protected abstract val bindingVariable: Int
    protected abstract val navControllerId: Int

    lateinit var dataBinding: BR

    protected open val interactionTimeout: Long? = 2 * 60 * 1000
    private var interactionTimeoutFinished = false
    private var isInForeground = true
    private var connectivityLiveData: ConnectivityLiveData? = null

    private var isConnected = false
    private val interactionTimeoutHandler = Handler(Looper.getMainLooper())

    private val interactionTimeoutRunnable = Runnable {
        if (!this.isFinishing) {
            interactionTimeoutFinished = true
//            showNoActivityDialog {
//                doneButtonClickListener {
//                    interactionTimeoutFinished = false
//                    exitSession()
//                }
//            }
            interactionTimeoutFinished = false
            exitSession()
        }
    }

    abstract fun onInitDependencyInjection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        onInitDependencyInjection()
        initConnectivityLiveData()

        dataBinding = DataBindingUtil.setContentView(this, layoutId)
        dataBinding.lifecycleOwner = this
        dataBinding.root.setOnClickListener {
            it.hideKeyboard(this)
        }

        viewModel.let { dataBinding.setVariable(bindingVariable, it) }

        observeState(::onStateChange)
        observeNavigationEvent()
        observeOnGenericErrorEvent()
        startInteractionHandler()
    }

    override fun onResume() {
        super.onResume()
        isInForeground = true
    }

    override fun onPause() {
        isInForeground = false
        super.onPause()
    }

    // reset handler on user interaction
    override fun onUserInteraction() {
        super.onUserInteraction()
        resetInteractionHandler()
    }

    private fun observeState(callback: (state: VS) -> Unit) {
        val observer = Observer<VS> { state -> callback.invoke(state) }
        viewModel.viewState.observe(this, observer)
        observer.onChanged(viewModel.state)
    }

    private fun observeNavigationEvent() {
        viewModel.navigationEvent.observe(this) { navEvent ->
            val consume = navEvent.consume()
            consume?.invoke(Navigation.findNavController(this, navControllerId))
        }
    }

    private fun initConnectivityLiveData() {
        connectivityLiveData = ConnectivityLiveData(applicationContext)
        connectivityLiveData?.observe(this) {
            isConnected = it ?: false
            when {
                !isConnected -> {
                    //if (isInForeground) noInternetDialog.show()
                }
                else -> {
                    //noInternetDialog.dismiss()
                }
            }
        }
    }

    private fun observeOnGenericErrorEvent() {
        viewModel.onGenericErrorEvent.observe(this) { event ->
            event.consume()?.let {
                if (it) {
                    //onGenericError()
                }
            }
        }
    }

    /** Override this in subclasses to listen to state changes */
    protected abstract fun onStateChange(state: VS)
    open fun onNetworkStateChange(isConnected: Boolean) {
        when {
            !isConnected -> {
                //noInternetDialog.show()
            }

            else -> {
                //noInternetDialog.dismiss()
            }
        }
    }

    open fun exitSession() {
        finish()
    }

    // restart countdown
    private fun resetInteractionHandler() {
        stopInteractionHandler()
        startInteractionHandler()
    }

    private fun stopInteractionHandler() {
        interactionTimeoutHandler.removeCallbacks(interactionTimeoutRunnable)
    }

    // start countdown
    private fun startInteractionHandler() {
        interactionTimeout?.let {
            if (!interactionTimeoutFinished) interactionTimeoutHandler.postDelayed(
                interactionTimeoutRunnable,
                it
            )
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        connectivityLiveData?.removeObservers(this)
        connectivityLiveData = null
        dataBinding.unbind()
    }
}
