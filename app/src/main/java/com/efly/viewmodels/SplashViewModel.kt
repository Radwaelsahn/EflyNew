package com.efly.viewmodels

import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.base.BaseViewModel
import com.efly.repositories.HomeRepository
import com.efly.repositories.LocalRepository
import com.efly.utils.EXTRA_LOGGED_IN
import com.efly.utils.REQUEST_LAT_LNG_PICKER

import javax.inject.Inject

class SplashViewModel : BaseViewModel() {


    @Inject
    lateinit var repository: HomeRepository

    val guest: MutableLiveData<Boolean> = MutableLiveData()
    val openScreen: MutableLiveData<Int> = MutableLiveData()

//    val heroDataSuccess: MutableLiveData<HomePopulateResponseModel> = MutableLiveData()

    init {
        appComponent.inject(this)
    }


    fun start() {

//        var store = LocalRepository.getSavedStore()
//        if (store != null && !store.storeCode.isNullOrBlank()) {
//            guest.value = true
//        }

        launchIntroOrMainActivity()
    }

    private fun launchIntroOrMainActivity() {
        if (LocalRepository.isFirstLogin()) {
            openScreen.value = REQUEST_LAT_LNG_PICKER
//            view.openStoreActivity()
        } else {
            if (!LocalRepository.isLoggedIn()) {
                openScreen.value = REQUEST_LAT_LNG_PICKER
                // view.openStoreActivity(REQUEST_LAT_LNG_PICKER)
            } else {
                openScreen.value = EXTRA_LOGGED_IN
                //view.openStoreActivity(EXTRA_LOGGED_IN)
            }
        }
    }

    fun getHomePopulatingData() {
//        subscription = repository.getHomePopulatingData()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .doOnSubscribe { onRetrieveDataStart() }
//            .doOnTerminate { onRetrieveDataFinish() }
//            .subscribe(
//                {
//
//                    onRetrieveHeroData(it)
//
//                },
//                { error -> onRetrieveDataError(error) }
//            )
    }

//    private fun onRetrieveHeroData(response: HomePopulateResponseModel) {
//
//        loadingVisibility.value = View.GONE
//        heroDataSuccess.value = response
//    }


//    private fun onRetrieveNearByHeroesSuccess(response: NearByHeroesResponseModel) {
//
//        loadingVisibility.value = View.GONE
//        nearByHeroesSuccess.value = response
//    }


}
