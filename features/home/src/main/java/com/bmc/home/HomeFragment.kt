package com.bmc.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.core.base.BaseFragment
import com.bmc.home.databinding.FragmentHomeBinding
import com.bmc.home.di.DaggerHomeComponent
import com.bmc.home.di.HomeModule
import com.bmc.home.model.HomeState
import com.bmc.home.screens.operations.OperationsFragment
import com.bmc.home.screens.products.ProductsFragment
import com.bmc.home.viewmodel.HomeViewModel

class HomeFragment : BaseFragment<HomeState, HomeViewModel, FragmentHomeBinding>() {

    override val layoutId: Int = R.layout.fragment_home
    override val bindingVariable: Int = BR.viewModel
    override val mainNavController: Int = R.id.fragment_container_view_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setBottomNavigation()
        initFragments()
    }

    override fun onStateChange(state: HomeState) {
    }

    override fun onInitDependencyInjection() {
        DaggerHomeComponent
            .builder()
            .productRepositoryComponent(
                BancaMovilApplication.productRepositoryComponent(
                    requireContext()
                )
            )
            .homeModule(HomeModule(this))
            .build()
            .inject(this)
    }

    private fun initFragments() {
        val fragmentTransaction: FragmentTransaction = childFragmentManager.beginTransaction()
        childFragmentManager.fragments.forEach {
            fragmentTransaction.remove(it)
        }

        OperationsFragment.newInstance().let {
            fragmentTransaction.add(
                R.id.frameLayout_home_main,
                it,
                OperationsFragment.TAG
            ).hide(it)
        }
        fragmentTransaction.add(
            R.id.frameLayout_home_main,
            ProductsFragment.newInstance(),
            ProductsFragment.TAG
        ).commit()
    }

    private fun setBottomNavigation() {
        dataBinding.bottomNavigationViewHome.apply {
            inflateMenu(R.menu.home_bottom_navigation_menu)
            setOnItemSelectedListener { item ->
                val showingFragment = getVisibleFragment()
                when (item.itemId) {
                    R.id.menu_item_home_products -> {
                        val fragment =
                            childFragmentManager.findFragmentByTag(ProductsFragment.TAG)!!
                        childFragmentManager.beginTransaction().apply {
                            showingFragment?.let { hide(it) }
                            show(fragment)
                            commit()
                        }
                    }
                    R.id.menu_item_home_operations -> {
                        val fragment =
                            childFragmentManager.findFragmentByTag(OperationsFragment.TAG)!!
                        childFragmentManager.beginTransaction().apply {
                            showingFragment?.let { hide(it) }
                            show(fragment)
                            commit()
                        }
                    }
                }
                true
            }
        }
        setBottomNavigationMenuItemsEnabled()
    }

    private fun setBottomNavigationMenuItemsEnabled() {
        dataBinding.bottomNavigationViewHome.menu.apply {
            findItem(R.id.menu_item_home_products)?.isEnabled = true
            findItem(R.id.menu_item_home_operations)?.isEnabled = false
        }
    }

    private fun getVisibleFragment(): Fragment? {
        return childFragmentManager.fragments.firstOrNull { it.isVisible }
    }
}