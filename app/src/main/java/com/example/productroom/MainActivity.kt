package com.example.productroom

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.productroom.adapter.ProductAdapter
import com.example.productroom.entity.Product
import com.example.productroom.viewmodel.ProductViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val AddActivityCode = 1
    private val SearchActivityCode = 2
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        productAdapter = ProductAdapter()
        recyclerProduct.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = productAdapter
        }

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productViewModel.allProduct.observe(this, Observer { products ->
            products?.let {
                productAdapter.setProducts(it)
            }
        })

        btnAdd.setOnClickListener {
            var intent = Intent(this@MainActivity, AddActivity::class.java)
            startActivityForResult(intent, AddActivityCode)
        }

        btnAll.setOnClickListener {
            productViewModel.allProduct.observe(this, Observer { products ->
                products?.let {
                    productAdapter.setProducts(it)
                }
            })
        }

        btnSearch.setOnClickListener {
            var intent = Intent(this@MainActivity, SearchActivity::class.java)
            startActivityForResult(intent, SearchActivityCode)
        }

        btnDelete.setOnClickListener {
            if (TextUtils.isEmpty(editDelete.text)) {
                Toast.makeText(applicationContext, "Fill in product ID", Toast.LENGTH_LONG).show()
            }
            else {
                val deleteID = editDelete.text.toString().toInt()
                productViewModel.deleteProduct(deleteID)
                productViewModel.allProduct.observe(this, Observer { products ->
                    products?.let {
                        productAdapter.setProducts(it)
                    }
                })
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == AddActivityCode && resultCode == Activity.RESULT_OK) {
            data?.getStringArrayExtra(AddActivity.EXTRA_REPLY)?.let {
                val product = Product()
                product.productName = it.get(0)
                product.productPrice = it.get(1).toDouble()
                product.productQuantity = it.get(2).toInt()
                productViewModel.insertProduct(product)
            }
        }

        if(requestCode == SearchActivityCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(SearchActivity.EXTRA_REPLY)?.let {
                productViewModel.getProduct(it)
                productViewModel.productByName.observe(this, Observer { products ->
                    products?.let {
                        productAdapter.setProducts(it)
                    }
                })
            }
            data?.getStringArrayExtra(SearchActivity.EXTRA_REPLY)?.let {
                productViewModel.getProduct(it[0].toDouble(), it[1].toDouble())
                productViewModel.productByPrice.observe(this, Observer { products ->
                    products?.let {
                        productAdapter.setProducts(it)
                    }
                })
            }
        }
    }
}
