package com.example.productroom.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.productroom.database.ProductDatabase
import com.example.productroom.entity.Product
import com.example.productroom.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(application: Application): AndroidViewModel(Application()) {

    private val repository: ProductRepository
    val allProduct: LiveData<List<Product>>
    lateinit var productByName: LiveData<List<Product>>
    lateinit var productByPrice: LiveData<List<Product>>

    init {
        val productDao = ProductDatabase.getDatabase(application).productDao()
        repository = ProductRepository(productDao)
        allProduct = repository.allProduct
    }

    fun getProduct(name: String) = viewModelScope.launch {
        productByName = repository.getProduct(name)
    }

    fun getProduct(minPrice: Double, maxPrice: Double) = viewModelScope.launch {
        productByPrice = repository.getProduct(minPrice, maxPrice)
    }

    fun insertProduct(product: Product) = viewModelScope.launch {
        repository.insertProduct(product)
    }

    fun deleteProduct(id: Int) = viewModelScope.launch {
        repository.deleteProduct(id)
    }
}