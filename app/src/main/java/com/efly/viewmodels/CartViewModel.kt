package com.efly.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.base.BaseViewModel
import com.efly.models.ProductModel
import com.efly.repositories.HomeRepository
import com.efly.repositories.LocalRepository

import javax.inject.Inject

class CartViewModel : BaseViewModel() {


    @Inject
    lateinit var repository: HomeRepository

    val guest: MutableLiveData<Boolean> = MutableLiveData()
    val openScreen: MutableLiveData<Int> = MutableLiveData()

    init {
        appComponent.inject(this)
    }


    fun addToCart(product: ProductModel, first: Boolean): Boolean {

        LocalRepository.addToCartLocal(product)

//        if (MyRepository.getCartId().isEmpty()) {
//            if (MyRepository.isLoggedIn()) {
//                callAddCartQuote(product)
//            } else {
//                callCreateGuestCart(product)
//            }
//        } else {
//            if (first)
//                callAddToCart(product)
//            else {
//                callEditCartItem(product)
//            }
//        }
        return true
    }

    fun subtractFromCart(product: ProductModel): Boolean {
//        MyRepository.removeFromCartLocal(product)
//
//        if (product.getCount() === 0) {
//            callRemoveItem(product)
//        } else {
//            callEditCartItem(product)
//        }

        return true
    }


    private fun callCreateGuestCart(product: ProductModel) {

//        val subscription = MyRepository.createGuestCart()
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<String>() {
//                fun onCompleted() {}
//
//                fun onError(e: Throwable) {
//                    view.showNetworkError()
//                    Log.e(TAG, e.toString())
//                }
//
//                fun onNext(response: String) {
//                    Log.i("addCartQuote", response)
//                    callAddToCart(product)
//                }
//            })
//        subscriptions.add(subscription)

    }

    private fun callAddCartQuote(product: ProductModel) {
//        val subscription = MyRepository.addCartQuote(product)
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<Int>() {
//                fun onCompleted() {}
//
//                fun onError(e: Throwable) {
//                    view.showNetworkError()
//                    Log.e(TAG, e.toString())
//                }
//
//                fun onNext(response: Int?) {
//                    Log.i("addCartQuote", response!!.toString())
//                }
//            })
//        subscriptions.add(subscription)

    }

    private fun callAddToCart(product: ProductModel) {
//        val subscription = MyRepository.callAddToCart(product)
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<AddToCartResponse>() {
//                fun onCompleted() {}
//
//                fun onError(e: Throwable) {
//                    view.showNetworkError()
//                    Log.e(TAG, e.toString())
//                }
//
//                fun onNext(response: AddToCartResponse) {
//                    Log.i("callAddToCart", response.toString())
//                }
//            })
//        subscriptions.add(subscription)

    }

    private fun callRemoveItem(product: ProductModel) {

//        val subscription = MyRepository.removeCartItem(product.getEntityId())
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<Boolean>() {
//                fun onCompleted() {}
//
//                fun onError(e: Throwable) {
//                    view.showNetworkError()
//                    Log.e(TAG, e.toString())
//                }
//
//                fun onNext(response: Boolean?) {
//                    Log.i("callAddToCart", response!!.toString())
//                }
//            })
//        subscriptions.add(subscription)

    }

    private fun callEditCartItem(product: ProductModel) {

//        val cartItem = CartItemInput(
//            MyRepository.getCartId(),
//            product.getCount(),
//            String.valueOf(product.getSku())
//        )
//        val input = AddCartItemInput(cartItem)
//
//        val subscription = MyRepository.editCartItem(product.getEntityId(), input)
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<AddToCartResponse>() {
//                fun onCompleted() {}
//
//                fun onError(e: Throwable) {
//                    view.showNetworkError()
//                    Log.e(TAG, e.toString())
//                }
//
//                fun onNext(response: AddToCartResponse) {
//                    Log.i("callAddToCart", response.toString())
//                }
//            })
//        subscriptions.add(subscription)

    }


    fun getCartInfo() {
//        val subscription = MyRepository.getCartInfo()
//            .observeOn(schedulerProvider.ui())
//            .subscribeOn(schedulerProvider.computation())
//            .retry(RequestRetry.RETRY_COUNT)
//            .subscribe(object : Subscriber<CartInfoResponse>() {
//                fun onCompleted() {
//
//                }
//
//                fun onError(e: Throwable) {
//
//                }
//
//                fun onNext(storesResponse: CartInfoResponse) {
//                    view.showCartInfo(storesResponse)
//                    //                        savecartinfo
//                }
//            })
//        subscriptions.add(subscription)
    }


    fun invalidateCart() {
//        MyRepository.invalidateCart()
    }

//    fun getCartInfoModel(): CartInfoResponse {
//
//        return MyRepository.getCartInfoModel()
//    }
}
