package com.bmc.home.di

import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.bmc.core.base.BaseFeature
import com.bmc.core.extensions.sharedViewModel
import com.bmc.core.extensions.viewModel
import com.bmc.domain.home.usecase.GetProduceUseCase
import com.bmc.domain.home.usecase.UpdateProductUseCase
import com.bmc.home.screens.products.viewmodel.ProductsViewModel
import com.bmc.home.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides

@Module
class HomeModule(
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    val baseFeature: BaseFeature
) {
    @Provides
    fun provideHomeViewModel(
        getProduceUseCase: GetProduceUseCase,
        updateProductUseCase: UpdateProductUseCase
    ): HomeViewModel {
        val factory = {
            HomeViewModel(
                getProduceUseCase,
                updateProductUseCase
            )
        }
        return baseFeature.let {
            if (it is Fragment) it.sharedViewModel { factory() }
            else (it as FragmentActivity).viewModel { factory() }
        }
    }

    @Provides
    fun provideProductsViewModel() = (baseFeature as Fragment).viewModel {
        ProductsViewModel()
    }
}