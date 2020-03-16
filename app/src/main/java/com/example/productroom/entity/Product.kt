package com.example.productroom.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Product")
class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var productID: Int = 0

    @ColumnInfo(name = "name")
    var productName: String = ""

    @ColumnInfo(name = "price")
    var productPrice: Double = 0.0

    @ColumnInfo(name = "quantity")
    var productQuantity: Int = 0
}