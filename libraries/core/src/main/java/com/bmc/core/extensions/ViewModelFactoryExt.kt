package com.bmc.core.extensions

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> Fragment.viewModel(
    key: String? = null,
    crossinline factory: () -> VM
): VM {
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factory() as T
        }
    }

    return if (key != null) {
        ViewModelProviders.of(this, viewModelProviderFactory).get(key, VM::class.java)
    } else {
        ViewModelProviders.of(this, viewModelProviderFactory).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> Fragment.sharedViewModel(
    key: String? = null,
    crossinline factory: () -> VM
): VM {
    return requireActivity().viewModel(key, factory)
}

@Suppress("UNCHECKED_CAST")
inline fun <reified VM : ViewModel> FragmentActivity.viewModel(
    key: String? = null,
    crossinline factory: () -> VM
): VM {
    val viewModelProviderFactory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return factory() as T
        }
    }

    return if (key != null) {
        ViewModelProviders.of(this, viewModelProviderFactory).get(key, VM::class.java)
    } else {
        ViewModelProviders.of(this, viewModelProviderFactory).get(VM::class.java)
    }
}

inline fun <reified VM : ViewModel> Fragment.retrieveActivitySharedViewModel(): VM {
    return ViewModelProviders.of(requireActivity()).get(VM::class.java)
}
