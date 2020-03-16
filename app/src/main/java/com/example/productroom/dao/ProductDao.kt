package com.example.productroom.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.productroom.entity.Product

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: Product)

    @Query("DELETE FROM Product WHERE id= :id")
    suspend fun deleteProduct(id: Int)

    @Query("SELECT * FROM Product WHERE name LIKE '%' || :name || '%'")
    fun getProduct(name: String): LiveData<List<Product>>

    @Query("SELECT * FROM Product WHERE price BETWEEN :minPrice AND :maxPrice")
    fun getProduct(minPrice: Double, maxPrice: Double): LiveData<List<Product>>

    @Query("SELECT * FROM Product")
    fun getAllProduct(): LiveData<List<Product>>

}