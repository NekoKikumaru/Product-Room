package com.example.productroom.repository

import androidx.lifecycle.LiveData
import com.example.productroom.dao.ProductDao
import com.example.productroom.entity.Product

class ProductRepository(private val productDao: ProductDao) {

    var allProduct: LiveData<List<Product>> = productDao.getAllProduct()

    fun getProduct(name: String): LiveData<List<Product>> {
        val productList = productDao.getProduct(name)
        return productList
    }

    fun getProduct(minPrice: Double, maxPrice: Double): LiveData<List<Product>> {
        val productList = productDao.getProduct(minPrice, maxPrice)
        return productList
    }

    suspend fun insertProduct(product: Product) {
        productDao.insertProduct(product)
    }

    suspend fun deleteProduct(id: Int) {
        productDao.deleteProduct(id)
    }

}