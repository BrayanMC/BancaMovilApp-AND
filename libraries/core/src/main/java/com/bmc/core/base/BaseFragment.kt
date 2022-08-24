package com.bmc.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.Navigation.findNavController
import javax.inject.Inject

abstract class BaseFragment<VS : BaseViewState, VM : BaseViewModel<VS>, BR : ViewDataBinding> :
    Fragment(), BaseFeature,
    LifecycleOwner {

    @Inject
    open lateinit var viewModel: VM
    protected abstract val layoutId: Int
    protected abstract val bindingVariable: Int
    protected abstract val mainNavController: Int

    lateinit var dataBinding: BR

    abstract fun onInitDependencyInjection()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        onInitDependencyInjection()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        dataBinding.lifecycleOwner = viewLifecycleOwner
        viewModel.let { dataBinding.setVariable(bindingVariable, it) }
        setupSharedViewModel(dataBinding)

        return dataBinding.root
    }

    open fun setupSharedViewModel(dataBinding: BR) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity()
            .onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    onBackPressed()
                }
            })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeState(::onStateChange)
        observeNavigationEvent()
        observeOnGenericErrorEvent()
        observeOnTimeoutErrorEvent()
    }

    private fun observeState(callback: (state: VS) -> Unit) {
        val observer = Observer<VS> { state -> callback.invoke(state) }
        viewModel.viewState.observe(viewLifecycleOwner, observer)
        // Deliver initial state because initial state was initialized when there wasn't an observer observing state live data.
        observer.onChanged(viewModel.state)
    }

    /** Override this in subclasses to listen to state changes */
    protected abstract fun onStateChange(state: VS)

    private fun observeNavigationEvent() {
        viewModel.navigationEvent.observe(viewLifecycleOwner) { navEvent ->
            val consume = navEvent.consume()
            consume?.invoke(findNavController(requireView()))
        }
    }

    override fun onPause() {
        super.onPause()
        if (isRemoving)
            dataBinding.unbind()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dataBinding.unbind()
    }

    private fun observeOnGenericErrorEvent() {
        viewModel.onGenericErrorEvent.observe(viewLifecycleOwner) { event ->
            event.consume()?.let { if (it) onGenericError() }
        }
    }

    protected open fun onGenericError() {
        //showGenericErrorDialog { doneButtonClickListener {} }
    }

    private fun observeOnTimeoutErrorEvent() {
        viewModel.onTimeoutErrorEvent.observe(viewLifecycleOwner) { event ->
            event.consume()?.let {
                //if (it) onResponseTimeoutError()
            }
        }
    }

    protected fun onBackPressed() {
        viewModel.onBackPressed()
        onReturnToPreviousScreen()
    }

    protected open fun onReturnToPreviousScreen() {
        val navController = findNavController(requireView())
        navController.popBackStack()
    }

    /** Clears all shared ViewModels stored in parent Fragment or Activity */
    protected fun clearViewModels() {
        parentFragment?.viewModelStore?.clear()
    }

    fun isViewModelInitialised() = ::viewModel.isInitialized

    protected open fun exitSession() {
        activity?.let {
            if (it is BaseActivity<*, *, *>) it.exitSession()
            else it.finish()
        }
    }
}
