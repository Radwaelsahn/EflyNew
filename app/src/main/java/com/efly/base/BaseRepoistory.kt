package com.efly.base

import com.efly.MyApplication.Companion.appComponent
import com.efly.network.RestApi
import com.efly.repositories.LocalRepository
import com.efly.utils.SHRP_REFRESHTOKEN

import javax.inject.Inject

abstract class BaseRepoistory {

    @Inject
    lateinit var restApi: RestApi

    var refreshToken: String = ""
    var profileId: String = ""
    var stripeAccountID: String = ""
    var stripeAccCustomerID: String = ""

    // Testing Debit Card Info

    // 	"card_number":"4000056655665556"
    //	"card_exp_month":"12",
    //    "card_exp_year":"2020",
    //    "card_cvc":"123"

    init {
        appComponent.inject(this)
        refreshToken =
            LocalRepository.getFromSharedPref(SHRP_REFRESHTOKEN)
                .toString()

//        stripeAccCustomerID = LocalRepository.getFromSharedPref(context.getString(R.string.STRIPECUSTOMERID))
//            .toString()

    }


//    fun postRefreshToken(): Observable<RefreshTokenResponseModel> {
//
//        return restApi.postRefreshToken(RefreshTokenRequestModel(refreshToken))
//            .onErrorReturn { null }
//    }
}