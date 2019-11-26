package com.efly.ui.products

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.efly.R
import com.efly.models.ProductModel

class ProductsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)
    }

    interface ProductDetailsListener {
        fun onViewDetails(model: ProductModel, position: Int)
    }
}
