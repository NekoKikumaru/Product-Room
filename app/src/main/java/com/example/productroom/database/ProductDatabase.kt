package com.example.productroom.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.productroom.dao.ProductDao
import com.example.productroom.entity.Product

@Database(entities = [Product::class], version = 1)
abstract class ProductDatabase: RoomDatabase() {

    abstract fun productDao(): ProductDao

    companion object {

        private var INSTANCE: ProductDatabase? = null

        fun getDatabase(context: Context): ProductDatabase {

            val tempInstance = INSTANCE

            if(tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(context, ProductDatabase::class.java, "product_database").build()
                INSTANCE = instance
                return instance
            }
        }

    }
}