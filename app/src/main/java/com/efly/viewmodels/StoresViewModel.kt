package com.efly.viewmodels

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.base.BaseViewModel
import com.efly.models.StoreItem
import com.efly.repositories.HomeRepository
import com.efly.repositories.LocalRepository
import com.efly.repositories.RestRepository
import com.efly.utils.KEY_STORE
import com.efly.utils.KEY_USER_SELECTED_STORE
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class StoresViewModel : BaseViewModel() {


    @Inject
    lateinit var repository: RestRepository


    val storesList: MutableLiveData<List<StoreItem>> = MutableLiveData()
    val storeItem: MutableLiveData<StoreItem> = MutableLiveData()
//    val heroDataSuccess: MutableLiveData<HomePopulateResponseModel> = MutableLiveData()

    init {
        appComponent.inject(this)
    }


    fun getStores(lat: Double, lng: Double) {
        subscription = repository.getStores(lat, lng)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    storesList.value = it
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

    fun getStoreInfo(storeId: String) {
        subscription = repository.getStoreInfo(storeId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    storeItem.value = it.get(0)
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

    fun saveSelectedStore(storeItem: StoreItem) {

        LocalRepository.saveStore(storeItem)

    }

}
