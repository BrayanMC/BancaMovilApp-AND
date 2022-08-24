package com.bmc.home.di

import com.bmc.bancamovilapp.di.app.scopes.FeatureScope
import com.bmc.bancamovilapp.di.home.ProductRepositoryComponent
import com.bmc.home.HomeActivity
import com.bmc.home.HomeFragment
import com.bmc.home.screens.products.ProductsFragment
import dagger.Component

@FeatureScope
@Component(
    modules = [HomeModule::class],
    dependencies = [ProductRepositoryComponent::class]
)
interface HomeComponent {

    fun inject(homeActivity: HomeActivity)

    fun inject(homeFragment: HomeFragment)

    fun inject(productsFragment: ProductsFragment)
}