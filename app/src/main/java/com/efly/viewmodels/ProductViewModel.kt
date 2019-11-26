package com.efly.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.base.BaseViewModel
import com.efly.models.ProductDetails
import com.efly.models.StoreItem
import com.efly.repositories.HomeRepository
import com.efly.repositories.LocalRepository
import com.efly.repositories.RestRepository
import com.efly.utils.KEY_STORE
import com.efly.utils.KEY_USER_SELECTED_STORE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProductViewModel : BaseViewModel() {


    @Inject
    lateinit var repository: RestRepository


    val productDetails: MutableLiveData<ProductDetails> = MutableLiveData()

    init {
        appComponent.inject(this)
    }


    fun getProductDetails(productId:String) {
        subscription = repository.getProductDetails(productId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    productDetails.value = it
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

}
