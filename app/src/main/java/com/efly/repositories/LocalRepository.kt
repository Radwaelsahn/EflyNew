package com.efly.repositories


import android.content.Context
import android.graphics.*
import android.view.View
import android.view.WindowManager

import com.efly.MyApplication.Companion.context
import com.efly.R
import com.efly.models.ProductModel
import com.efly.models.SelectedRegion
import com.efly.models.StoreItem
import com.efly.utils.*
import com.google.gson.Gson

// Made is as a Util Function so later on it would used whenever an Exchanging Rate is required
// Of course we can change currencyRateMapping -> fetch it from Room or the last saved CurrencyConversionList

object LocalRepository {

    var xAccessToken: String = getAccessToken()
    //    var isCustomer: Boolean = getUserType()
    var cart: MutableMap<String, List<ProductModel>>? = null

    init {
        xAccessToken =
            getAccessToken()
    }

    fun setIntoSharedPref(key: String, value: String) {
        val sharedPref = context?.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
            ?: return
        with(sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun setIntoSharedPref(key: String, value: Double) {
        val sharedPref = context?.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
            ?: return
        with(sharedPref.edit()) {
            putString(key, value.toString())
            apply()
        }
    }

    fun setIntoSharedPref(key: String, value: Any) {
        val sharedPref = context?.getSharedPreferences(
            context.getString(R.string.preference_file_key),
            Context.MODE_PRIVATE
        )
            ?: return
        with(sharedPref.edit()) {
            putString(key, Gson().toJson(value))
            apply()
        }
    }

    fun setAccessToken(token: String?) {
        token?.let {
            setIntoSharedPref(
                SHPK_XACCESSTOKEN,
                it
            )
        }

        xAccessToken = token!!
    }


//    fun setUserType(type: String?) {
//        type?.let {
//            setIntoSharedPref(
//                context,
//                context.getString(R.string.ISCUSTOMER),
//                type
//            )
//        }
//
//    }

    //    fun getUserType(): Boolean {
//        val type = getFromSharedPref(
//            context,
//            context.getString(R.string.ISCUSTOMER)
//        )
//            .toString()
//        return type == "customer"
//    }
//
//
    fun getAccessToken(): String {
        val accessToken = getFromSharedPref(SHPK_XACCESSTOKEN)
            .toString()
        return accessToken
    }

    fun loadBitmapFromView(v: View): Bitmap? {

        if (v.measuredHeight <= 0) {
            v.measure(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            val b = Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            val c = Canvas(b)
            v.layout(0, 0, v.measuredWidth, v.measuredHeight)
            v.draw(c)
            return b
        }
        return null
    }

    fun getFromSharedPref(key: String): String? {
        val sharedPref =
            context?.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        return sharedPref?.getString(key, "")
    }

    fun getBooleanFromSharedPref(key: String): Boolean {
        val sharedPref =
            context?.getSharedPreferences(
                context.getString(R.string.preference_file_key),
                Context.MODE_PRIVATE
            )
        return sharedPref?.getBoolean(key, false)!!
    }


    fun isFirstLogin(): Boolean {
        return getBooleanFromSharedPref(SHPK_IS_FIRST_LOGIN)
    }

    fun isLoggedIn(): Boolean {
        // Gson().fromJson(getFromSharedPref(SHPK_USER),StoreItem::class.java)

        return false
    }


    fun getSavedStore(): StoreItem? {
        var storeItemJson =
            getFromSharedPref(KEY_STORE)
        if (!storeItemJson.isNullOrBlank()) {
            val storeItem = Gson().fromJson(storeItemJson, StoreItem::class.java)
            return storeItem
        } else return null
    }

    fun saveStore(storeItem: StoreItem) {
        setIntoSharedPref(KEY_STORE, Gson().toJson(storeItem))
    }


    fun getLastSelectedRegion(): SelectedRegion {
        var region: SelectedRegion?

        val rm =
            getFromSharedPref(KEY_LAST_SELECTED_REGION)
        if (!rm.isNullOrBlank()) {
            region = Gson().fromJson(rm, SelectedRegion::class.java)
            return region


        } else {
            // radwa, u should remove this.
//            lat = 40.7792047
//            lng = -73.9734726
            return SelectedRegion("", DEFAULT_LAT, DEFAULT_LNG)
        }


    }

    fun setAddressValues(latitude: Double, longitude: Double) {

        setIntoSharedPref(KEY_LAT, latitude)
        setIntoSharedPref(KEY_LNG, longitude)
    }

    fun addToCartLocal(product: ProductModel): Boolean {
//        if (!isStoreActive)
//            return false

//        if (cart.containsKey(product.getEntityId())) {
//            cart.get(product.getEntityId()).add(product)
//        } else {
//            val products = ArrayList<ProductModel>()
//            products.add(product)
//
//            cart.put(product.getEntityId(), products)
//        }
//
//        itemCount++
//        totalPrice += java.lang.Double.parseDouble(product.getPrice())
//
//        val cart = getCart()
//        sendCartEvent(cart)
//        product.add()
//
//        Answers.getInstance().logAddToCart(AddToCartEvent())

        return true
    }

    fun getCartItemCount(productId: String): Int {
//        if(cart == null)
//            return 0
//        return if (cart?.containsKey(productId)!!) cart?.get(productId)!!.size else 0

        return 0
    }
}