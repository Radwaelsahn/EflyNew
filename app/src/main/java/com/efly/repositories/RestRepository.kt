package com.efly.repositories

import android.util.Log
import com.efly.base.BaseRepoistory
import com.efly.models.Customer
import com.efly.models.ProductDetails
import com.efly.models.StoreItem
import com.efly.models.inputs.RegisterInput
import com.efly.models.inputs.UpdateAccountInput
import com.efly.utils.*
import com.eflyrx.app.data.model.inputs.LoginInput
import com.eflyrx.app.data.model.response.RegisterResponse
import com.google.gson.Gson
import io.reactivex.Observable
import java.util.*

class RestRepository : BaseRepoistory() {

    private fun reportNetworkError(tag: String, throwable: Throwable) {
        Log.e("Network Error - ", throwable.toString())
    }

    fun getStores(lat: Double, lng: Double): Observable<List<StoreItem>> {
        return restApi.stores(lat, lng)
            .onErrorReturn { null }
    }

    fun getStoreInfo(storeId: String): Observable<List<StoreItem>> {
        return restApi.storeInfo(storeId)
            .doOnNext({ storeInfo ->
                //                setStoreName(storeInfo.get(0).getStoreName())
//                setStoreCode(storeInfo.get(0).getStoreCode())
            })
            .doOnError({ throwable -> reportNetworkError("loadStoreInfo", throwable) })
    }


    fun login(username: String, password: String): Observable<String> {
        return restApi.login(LoginInput(username, password)).doOnNext { accountResponse ->
            LocalRepository.setIntoSharedPref(KEY_SESSION_ID, accountResponse)
            LocalRepository.setIntoSharedPref(KEY_EMAIL, username)
            LocalRepository.setIntoSharedPref(KEY_PASSWORD, password)
            Log.i("session", accountResponse)
        }
            .onErrorReturn { null }
    }

    fun register(registerInput: RegisterInput): Observable<RegisterResponse> {
        return restApi.register(registerInput).doOnNext { accountResponse ->

            LocalRepository.setIntoSharedPref(KEY_ACCOUNT, Gson().toJson(accountResponse))
            LocalRepository.setIntoSharedPref(KEY_IS_VERIFIED, false)
        }
            .onErrorReturn { null }
    }


    fun getAccountInfo(): Observable<Customer> {
        return restApi.getAccountInfo()
            .onErrorReturn { null }
    }

    fun updateAccount(register: UpdateAccountInput): Observable<RegisterResponse> {
        return restApi.updateAccount(register)
            .doOnNext({ accountResponse ->

            }).doOnError({ throwable -> reportNetworkError("updateAccount", throwable) })
    }




    fun getProductDetails(productId: String): Observable<ProductDetails> {

        return restApi.productDetails(productId).onErrorReturn { null }

    }



}
