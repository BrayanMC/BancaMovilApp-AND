package com.bmc.home.screens.operations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bmc.home.R

class OperationsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_operations, container, false)
    }

    companion object {
        const val TAG = "operationsFragment"

        @JvmStatic
        fun newInstance() = OperationsFragment()
    }
}