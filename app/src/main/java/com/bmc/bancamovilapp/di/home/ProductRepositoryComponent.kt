package com.bmc.bancamovilapp.di.home

import com.bmc.bancamovilapp.di.app.scopes.RepositoryScope
import com.bmc.core.di.component.CoreComponent
import com.bmc.domain.home.repository.ProductRepository
import dagger.Component

@RepositoryScope
@Component(
    modules = [ProductRepositoryModule::class],
    dependencies = [CoreComponent::class]
)
interface ProductRepositoryComponent {
    fun productRepository(): ProductRepository
}