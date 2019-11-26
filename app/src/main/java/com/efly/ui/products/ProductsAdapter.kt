package com.efly.ui.products

import android.content.Context
import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.Group

import androidx.recyclerview.widget.RecyclerView

import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.efly.R
import com.efly.models.ProductModel
import com.efly.repositories.LocalRepository
import com.efly.utils.AlertDialogUtil
import com.efly.utils.RoundNumberUtil
import com.google.gson.Gson

import kotlinx.android.synthetic.main.item_product.view.*


/**
 * Created by vezikon on 4/2/17.
 */

class ProductsAdapter(
    private val products: List<ProductModel>,
    private val listener: ProductsListListener?
) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
//    private var viewType = 0xA2

//    constructor(
//        context: Context,
//        listener: ProductsListListener, viewType: Int
//    ) : this(ArrayList<ProductModel>(), context, listener, viewType) {
//    }

    init {
//        this.viewType = viewType

    }


    fun showProducts(products: List<ProductModel>) {
//        val oldCount = this.products!!.size
//        val newCount = products.size
//        this.products.addAll(products)
        //notifyItemRangeChanged(oldCount, newCount)
//        notifyDataSetChanged()
    }

//    fun clearProducts() {
//        this.products!!.clear()
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.item = products!![position]
        if (holder.item!!.imageUrl != null && !holder.item!!.imageUrl!!.isEmpty()) {
            try {
                Glide.with(holder.image!!.context)
                    .load(holder.item!!.imageUrl)
                    .placeholder(R.drawable.default_placeholder)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .override(150, 150)
                    .into(holder.image!!)
            } catch (e: OutOfMemoryError) {
                //do nothing
            }

        }

        holder.title!!.text = holder.item!!.name
        //holder.desc.setText(holder.item.);
        holder.price!!.text = getPriceText(holder.item!!.price.toString(), holder.price!!.context)
        holder.image!!.setOnClickListener { v ->
            listener?.onViewDetails(holder.item, position)
        }
        holder.view.setOnClickListener { v ->
            listener?.onViewDetails(holder.item, position)
        }

        Log.e("product", Gson().toJson(holder.item!!))
        val count = LocalRepository.getCartItemCount(holder.item!!.entityId!!)
        holder.item!!.count = count

        holder.countView!!.text = holder.item!!.count.toString()

        if (holder.item!!.count > 0) {
            holder.showCounterView(true)
        } else {
            holder.showCounterView(false)
        }

        try {
            val originalPrice = java.lang.Double.parseDouble(holder.item!!.price!!.toString())
            val finalPrice = java.lang.Double.parseDouble(holder.item!!.price!!.toString())

            if (originalPrice != finalPrice) {
                holder.originalPrice!!.visibility = View.VISIBLE
                holder.originalPrice!!.text = getPriceText(
                    holder.item!!.price.toString(),
                    holder.originalPrice!!.context
                )
                holder.originalPrice!!.paintFlags =
                    holder.originalPrice!!.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                holder.originalPrice!!.visibility = View.INVISIBLE
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.addBtn!!.setOnClickListener { view -> addToCartItemClick(holder, position, false) }

        holder.subtractBtn!!.setOnClickListener { view ->
            subtractFromCartItemClick(
                holder,
                position
            )
        }

        holder.addToCartView!!.setOnClickListener { view ->
            addToCartItemClick(
                holder,
                position,
                true
            )
        }

        //        InstasallaRepository repository = InstasallaRepository.create(holder.addBtn.getContext()
        //                .getApplicationContext());
        //        String strColor = repository.getStoreColor();

        //        if (!TextUtils.isEmpty(strColor)) {
        //            int color = Color.parseColor(strColor);
        //            holder.addBtn.setBackgroundColor(color);
        //            holder.subtractBtn.setBackgroundColor(color);
        //            holder.countView.setBackgroundColor(ColorUtils.darken(color, 0.2f));
        //        }
    }

    private fun subtractFromCartItemClick(holder: ViewHolder, position: Int) {
        if (null != listener) {
            if (listener.onSubtractFromCart(holder.item, position)) {
                if (holder.item!!.count <= 0) {
                    holder.showCounterView(false)
                }
                holder.countView!!.text = holder.item!!.count.toString()
            }
        }
    }

    private fun addToCartItemClick(holder: ViewHolder, position: Int, first: Boolean) {
        if (null != listener) {
            if (listener.onAddToCart(holder.item, position, first)) {
                if (holder.item!!.count > 0) {
                    holder.showCounterView(true)
                }
                holder.countView!!.text = holder.item!!.count.toString()
            } else {
                val context = holder.countView!!.context
                AlertDialogUtil.showError(context, context.getString(R.string.error_store_closed))
            }
        }
    }


    override fun getItemCount(): Int {
        return products?.size ?: 0
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var image: ImageView? = view.item_menu_image
        var title: TextView? = view.item_menu_title
        var desc: TextView? = view.item_menu_desc
        var price: TextView? = view.item_menu_price
        var originalPrice: TextView? = view.item_menu_original_price
        var addToCartView: View? = view.tv_add_to_cart
        var countView: TextView? = view.count_view
        var contentCounter: Group? = view.content_counter as Group?
        var subtractBtn: TextView? = view.product_detail_subtract_btn
        var addBtn: TextView? = view.product_detail_add_btn

        var item: ProductModel? = null

        fun showCounterView(show: Boolean) {
            if (show) {
                contentCounter!!.setVisibility(View.VISIBLE)
                addToCartView!!.visibility = View.INVISIBLE
                view.setBackgroundResource(R.drawable.card_background_selected)

            } else {
                contentCounter!!.setVisibility(View.INVISIBLE)
                addToCartView!!.visibility = View.VISIBLE
                view.setBackgroundResource(R.drawable.card_background)
            }
        }

        fun cleanup() {
            image!!.setImageDrawable(null)
        }

    }


    private fun getPriceText(price: String?, context: Context): String {
        return RoundNumberUtil.formatNumber(java.lang.Double.parseDouble(price!!)) + context.getString(
            R.string
                .kuwaiti_currency
        )
    }

    override fun onViewRecycled(holder: ViewHolder) {
        super.onViewRecycled(holder)
        holder.cleanup()
    }

    interface ProductsListListener {
        fun onAddToCart(model: ProductModel?, position: Int, first: Boolean): Boolean

        fun onSubtractFromCart(model: ProductModel?, position: Int): Boolean

        fun onViewDetails(model: ProductModel?, position: Int)
    }

    companion object {

        val TYPE_HORIZONTAL = 0xA1
        val TYPE_VERTICAL = 0xA2
    }
}
