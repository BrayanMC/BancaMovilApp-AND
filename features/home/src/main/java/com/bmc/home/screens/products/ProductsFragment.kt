package com.bmc.home.screens.products

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bmc.bancamovilapp.BancaMovilApplication
import com.bmc.core.base.BaseFragment
import com.bmc.home.BR
import com.bmc.home.R
import com.bmc.home.databinding.FragmentProductsBinding
import com.bmc.home.di.DaggerHomeComponent
import com.bmc.home.di.HomeModule
import com.bmc.home.model.HomeState
import com.bmc.home.screens.products.adapter.ProductAdapter
import com.bmc.home.screens.products.model.ProductsState
import com.bmc.home.screens.products.viewmodel.ProductsViewModel
import com.bmc.home.viewmodel.HomeViewModel
import javax.inject.Inject

class ProductsFragment : BaseFragment<ProductsState, ProductsViewModel, FragmentProductsBinding>() {

    override val layoutId: Int = R.layout.fragment_products
    override val bindingVariable: Int = BR.viewModel
    override val mainNavController: Int = 0

    @Inject
    lateinit var sharedViewModel: HomeViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViews()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeSharedState(::onSharedStateChange)
    }

    override fun onStateChange(state: ProductsState) {
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

    private fun observeSharedState(callback: (state: HomeState) -> Unit) {
        val observer = Observer<HomeState> { state -> callback.invoke(state) }
        sharedViewModel.viewState.observe(viewLifecycleOwner, observer)
        observer.onChanged(sharedViewModel.state)
    }

    private fun onSharedStateChange(state: HomeState) {
        state.products.consume()?.let {
            if (!it.isNullOrEmpty()) {
                if (dataBinding.swipeRefreshLayoutProducts.isRefreshing) dataBinding.swipeRefreshLayoutProducts.isRefreshing =
                    false
                viewModel.setProducts(it)
            }
        }
    }

    private fun setupViews() {
        with(dataBinding.recyclerViewProducts) {
            adapter = ProductAdapter {
            }
        }

        with(dataBinding.swipeRefreshLayoutProducts) {
            setOnRefreshListener {
                sharedViewModel.updateProducts()
            }
        }
    }

    companion object {
        const val TAG = "productsFragment"

        @JvmStatic
        fun newInstance() = ProductsFragment()
    }
}
