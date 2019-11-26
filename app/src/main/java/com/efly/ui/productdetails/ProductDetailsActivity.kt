package com.efly.ui.productdetails

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.efly.R
import com.efly.base.BaseActivity
import com.efly.models.ProductDetails
import com.efly.models.ProductModel
import com.efly.ui.products.ProductsActivity
import com.efly.ui.products.ProductsAdapter
import com.efly.utils.ARG_PRODUCT
import com.efly.utils.ARG_PRODUCT_ID
import com.efly.utils.ARG_STORE_ID
import com.efly.utils.RoundNumberUtil
import com.efly.viewmodels.CartViewModel
import com.efly.viewmodels.ProductViewModel
import com.efly.viewmodels.StoresViewModel
import kotlinx.android.synthetic.main.activity_product_details.*
import kotlinx.android.synthetic.main.item_product.*

class ProductDetailsActivity : BaseActivity(), ProductsAdapter.ProductsListListener {


    lateinit var viewModel: ProductViewModel
    lateinit var cartViewModel: CartViewModel

    private var listener: ProductsActivity.ProductDetailsListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)

        viewModel = ViewModelProviders.of(this).get(ProductViewModel::class.java)
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel::class.java)

        loadingObserve(viewModel, progress_bar)
        errorObserve(viewModel)
        viewModel.productDetails.observe(this, Observer {
//            showDetails(it)
        })

        var productId = intent.getStringExtra(ARG_PRODUCT_ID)
        viewModel.getProductDetails(productId)
    }

    fun showDetails(model: ProductModel) {
        if (model.desc != null) {
            product_detail_desc.text = model.desc
        } else {
            description_view.setVisibility(View.GONE)
        }


        Log.d(TAG, "showDetails: " + model.toString())

        product_detail_title.setText(model.name)
        product_detail_price.text = String.format(getString(R.string.currency), model.price)

        if (model.totalPrice != model.price) {
            product_save_amount.setVisibility(View.VISIBLE)
            product_detail_price_before_discount.setVisibility(View.VISIBLE)
            val amount = model.totalPrice - model.price
            val amountText =
                getString(R.string.save) + " " + RoundNumberUtil.formatNumber(amount) + " " + getString(
                    R.string.kuwaiti_currency
                )
            product_save_amount.setText(amountText)

            val price = String.format(getString(R.string.currency), model.price)
            product_detail_price_before_discount.setText(price)
            product_detail_price_before_discount.setPaintFlags(product_detail_price_before_discount.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }

//        productModel.setPrice(model.getPrice())
//        productModel.setImageUrl(model.getImageUrl())
//        productModel.setName(model.getName())

        try {
            Glide.with(this).load(model.imageUrl)
                .placeholder(R.drawable.default_placeholder)
                .into(product_detail_img)
        } catch (e: OutOfMemoryError) {
            //do nothing
        }

        //        if (model.getMediaGallery().size() > 0) {
        //            rvMoreImages.setVisibility(View.VISIBLE);
        //            setupMoreImages(model.getMediaGallery());
        //        } else {
        //            rvMoreImages.setVisibility(View.GONE);
        //        }

        if (model.count > 0) {
            count_view.setText(model.count.toString())
            showAddToCartForm(false)
        }

        //        if (model.getRelatedProducts() != null && !model.getRelatedProducts().isEmpty()) {
        //            ProductsListRecyclerViewAdapter adapterRelatedProductRecyclerView
        //                    = new ProductsListRecyclerViewAdapter(model.getRelatedProducts(), getContext
        //                    (), this, TYPE_HORIZONTAL);
        //            relatedProductRecyclerView.setAdapter(adapterRelatedProductRecyclerView);
        //        } else {
        product_detail_similar_items_header.setVisibility(View.GONE)
        //        }
    }


//    fun showDetails(model: ProductDetails) {
//        if (model.desc != null) {
//            product_detail_desc.text = model.desc
//        } else {
//            description_view.setVisibility(View.GONE)
//        }
//
//
//        Log.d(TAG, "showDetails: " + model.toString())
//        product_detail_title.setText(model.name)
//        product_detail_price.text = String.format(getString(R.string.currency), model.price)
//
//
//        if (model.getPrice().getRegular_price() !== model.getPrice().getSpecial_price()) {
//            saveAmount.setVisibility(View.VISIBLE)
//            priceBeforeDiscount.setVisibility(View.VISIBLE)
//            val amount = model.getPrice().getRegular_price() - model.getPrice().getSpecial_price()
//            val amountText =
//                getString(R.string.save) + " " + RoundNumberUtil.formatNumber(amount) + " " + getString(
//                    R.string.kuwaiti_currency
//                )
//            saveAmount.setText(amountText)
//
//            val price = model.getPrice() + " " + getString(R.string.kuwaiti_currency)
//            priceBeforeDiscount.setText(price)
//            priceBeforeDiscount.setPaintFlags(priceBeforeDiscount.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
//        }
//
//        productModel.setPrice(model.getPrice().getSpecial_price() + "")
//        productModel.setImageUrl(model.getImage())
//        productModel.setName(model.getName())
//
//
//        try {
//            GlideApp.with(this).load(model.getImage())
//                .placeholder(R.drawable.default_placeholder)
//                .into(productDetailImgView)
//        } catch (e: OutOfMemoryError) {
//            //do nothing
//        }
//
//        //        if (model.getMedia_gallery_sizes().size() > 0) {
//        //            rvMoreImages.setVisibility(View.VISIBLE);
//        //            setupMoreImages(model.getMediaGallery());
//        //        } else {
//        //            rvMoreImages.setVisibility(View.GONE);
//        //        }
//
//        if (productModel.getCount() > 0) {
//            productDetailCountView.setText(String.valueOf(productModel.getCount()))
//            showAddToCartForm(false)
//        }
//
//        //        if (model.getRelatedProducts() != null && !model.getRelatedProducts().isEmpty()) {
//        //            ProductsListRecyclerViewAdapter adapterRelatedProductRecyclerView
//        //                    = new ProductsListRecyclerViewAdapter(model.getRelatedProducts(), this
//        //                    , this, TYPE_HORIZONTAL);
//        //            relatedProductRecyclerView.setAdapter(adapterRelatedProductRecyclerView);
//        //        } else {
//        productDetailSimilarItemsHeader.setVisibility(View.GONE)
//        //        }
//    }

    private fun showAddToCartForm(show: Boolean) {
        if (show) {
            if (add_to_cart_form.getVisibility() == View.INVISIBLE) {
                add_to_cart_form.setVisibility(View.VISIBLE)
            }
        } else {

            if (add_to_cart_form.getVisibility() == View.VISIBLE) {
                add_to_cart_form.setVisibility(View.INVISIBLE)
            }

        }
    }


    override fun onAddToCart(model: ProductModel?, position: Int, first: Boolean): Boolean {
        return cartViewModel.addToCart(model!!, first)
    }

    override fun onSubtractFromCart(model: ProductModel?, position: Int): Boolean {
        return cartViewModel.subtractFromCart(model!!)
    }

    override fun onViewDetails(model: ProductModel?, position: Int) {
        if (listener != null)
            listener!!.onViewDetails(model!!, position)
    }
}
