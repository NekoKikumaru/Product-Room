package com.example.productroom.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.productroom.R
import com.example.productroom.entity.Product
import kotlinx.android.synthetic.main.item_product.view.*

class ProductAdapter(): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    private var products = emptyList<Product>()

    inner class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        fun bind(product: Product) {
            itemView.txtID.text = product.productID.toString()
            itemView.txtName.text = product.productName
            itemView.txtPrice.text = product.productPrice.toString()
            itemView.txtQuantity.text = product.productQuantity.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(products[position])
    }

    fun setProducts(products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
}