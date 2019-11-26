package com.efly.viewmodels

import android.text.TextUtils
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.efly.MyApplication.Companion.appComponent
import com.efly.base.BaseViewModel
import com.efly.models.StoreItem
import com.efly.models.inputs.RegisterInput
import com.efly.models.inputs.UpdateAccountInput
import com.efly.repositories.HomeRepository
import com.efly.repositories.RestRepository
import com.eflyrx.app.data.model.response.RegisterResponse
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AccountViewModel : BaseViewModel() {


    @Inject
    lateinit var repository: RestRepository

    val success: MutableLiveData<Boolean> = MutableLiveData()
//    val heroDataSuccess: MutableLiveData<HomePopulateResponseModel> = MutableLiveData()

    init {
        appComponent.inject(this)
    }


    fun register(registerInput: RegisterInput) {
        subscription = repository.register(registerInput)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    login(registerInput.getCustomer().getEmail(), registerInput.getPassword())
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

    fun login(username: String, password: String) {
        subscription = repository.login(username, password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    success.value = true
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

    fun checkAccountInfo() {
        subscription = repository.getAccountInfo()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    success.value = true
//                    view.showAccountInfo(accountResponse)
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )

    }

    fun editAccount(RegisterInput: UpdateAccountInput) {
        repository.updateAccount(RegisterInput)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveDataStart() }
            .doOnTerminate { onRetrieveDataFinish() }
            .subscribe(
                {
                    //                    view.onAccountUpdated()
                },
                { error ->
                    onRetrieveDataError(error)
                }
            )
    }

}
