package com.efly.ui.main

import android.content.Context

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.efly.R
import com.efly.models.StoreItem
import java.util.ArrayList

import kotlinx.android.synthetic.main.item_store.view.*
import kotlinx.android.synthetic.main.item_store_type.view.*

/**
 * Created by vezikon on 5/2/17.
 */

class StoreRecyclerAdapter(
    val storeList: List<StoreItem>,
    val context: Context,
    val listener: StoreListListener?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    private val filteredStoreList: MutableList<StoreItem>
    private val storeFilter: StoreFilter

    init {

        filteredStoreList = ArrayList()
        filteredStoreList.addAll(storeList)

        storeFilter = StoreFilter(this@StoreRecyclerAdapter)
    }

    override fun getItemViewType(position: Int): Int {
        return if (filteredStoreList[position] is StoreItem)
            VIEW_TYPE_ITEM
        else
            VIEW_TYPE_HEADER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutRes = if (viewType == VIEW_TYPE_HEADER)
            R.layout.item_store_type
        else
            R.layout
                .item_store_card

        val view = LayoutInflater.from(parent.context)
            .inflate(layoutRes, parent, false)

        when (viewType) {
            VIEW_TYPE_HEADER -> return HeaderViewHolder(view)
            else -> return StoreViewHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HEADER -> {
                val hHolder = holder as HeaderViewHolder
                hHolder.item = filteredStoreList[position]

                val header = hHolder.item as String?
                hHolder.headerTitle!!.text = header
            }

            VIEW_TYPE_ITEM -> {
                val sholder = holder as StoreViewHolder
                sholder.item = filteredStoreList[position]

                val storesItem = sholder.item as StoreItem?

                Glide.with(context)
                    .load(storesItem!!.logo)
                    .apply(RequestOptions.circleCropTransform())
//                    .apply(RequestOptions.bitmapTransform(
//                        RoundedCornersTransformation(this, sCorner, sMargin, sColor, sBorder)))
                    .into(sholder.storeImg)

                Glide.with(context).load(storesItem.storeBnr).into(sholder.storeBnr)

                Log.e("IdRad", storesItem?.storeCode!!)
                Log.e("banner", storesItem?.storeBnr!!)

                sholder.storeName!!.text = storesItem?.storeName

                //                if (storesItem != null && !storesItem.isActive()) {
                //                    sholder.storeClosed.setVisibility(View.VISIBLE);
                //                } else {
                //                    sholder.storeClosed.setVisibility(View.GONE);
                //                }

                val res = sholder.view.context.resources
                val workHours = res.getString(
                    R.string.label_store_work_time,
                    storesItem?.from,
                    storesItem?.to
                )

                sholder.storeOpeningTime!!.text = workHours

                val deliveryPeriod = res.getString(
                    R.string.label_store_delivery_period,
                    storesItem?.delivery
                )

                sholder.storeDeliveryPeriod!!.text = deliveryPeriod

                val feeText = res.getString(
                    R.string.label_store_fees,
                    storesItem?.fees
                )

                sholder.storeFees!!.text = feeText

                //                if (storesItem.isHasOffers()) {
                //                    sholder.storeHasOffers.setVisibility(View.VISIBLE);
                //                } else {
                sholder.storeHasOffers!!.visibility = View.INVISIBLE
                //                }

                val paymentMethods = android.text.TextUtils.join(", ", storesItem?.payment)
                val fullPaymentMethodsText = res.getString(
                    R.string.label_store_payment_type,
                    paymentMethods
                )

                sholder.storePaymentType!!.text = fullPaymentMethodsText

                //                if (((StoresItem) sholder.item).getMinValue() != null) {
                //                    String minValue = res.getString(R.string.label_store_min_order, ((StoresItem) sholder.item).getMinValue());
                //                    sholder.storeMinValue.setText(minValue);
                //                }

                sholder.view.setOnClickListener { v ->
                    listener?.onStoreSelected(storesItem!!, sholder.storeBnr)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return filteredStoreList.size
    }

    interface StoreListListener {
        fun onStoreSelected(storesItem: StoreItem, ivBanner: ImageView?)
    }

    internal inner class StoreViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var storeImg = view.store_img
        var storeBnr = view.store_bnr
        var storeName = view.store_name
        var storeOpeningTime = view.store_opening_time
        var storeDeliveryPeriod = view.store_delivery_period
        var storePaymentType = view.store_payment_type
        var storeFees = view.store_fees
        var storeClosed = view.store_closed
        var storeHasOffers = view.store_has_offers
        var item: StoreItem? = null
    }

    internal inner class HeaderViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        var headerTitle = view.store_header_title

        var item: Any? = null

        init {
            view.visibility = View.GONE
        }
    }

    fun filterByType(type: String) {
        filteredStoreList.clear()
        val typeFound = false

        for (`object` in storeList) {
            //            if (object instanceof String) {
            //                if (typeFound) {
            //                    break;
            //                }
            //
            //                if (type.equalsIgnoreCase((String) object)) {
            //                    typeFound = true;
            //                }
            //            } else {
            if (typeFound) {
                filteredStoreList.add(`object`)
            }
            //            }
        }

        notifyDataSetChanged()
    }

    fun clearFilters() {
        filteredStoreList.clear()
        filteredStoreList.addAll(storeList)
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return storeFilter
    }

    inner class StoreFilter(private val adapter: StoreRecyclerAdapter) : Filter() {

        override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
            filteredStoreList.clear()
            val results = Filter.FilterResults()
            if (charSequence.length == 0) {
                filteredStoreList.addAll(storeList)
            } else {
                val filterPattern = charSequence.toString().toLowerCase().trim { it <= ' ' }
                Log.i("filter2", filterPattern)
                for (mWords in storeList) {
                    if (mWords is StoreItem) {
                        if (mWords.storeName!!.toLowerCase().contains(
                                filterPattern
                            )
                        ) {
                            filteredStoreList.add(mWords)
                        }
                    }
                }
            }
            Log.e("Seach Count", "" + filteredStoreList.size.toString())
            results.values = filteredStoreList
            results.count = filteredStoreList.size
            return results
        }

        override fun publishResults(
            charSequence: CharSequence,
            filterResults: Filter.FilterResults
        ) {
            this.adapter.notifyDataSetChanged()
        }
    }

    companion object {

        private val VIEW_TYPE_HEADER = 0
        private val VIEW_TYPE_ITEM = 1
    }
}
