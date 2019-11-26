package com.efly.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.NearHero.models.BaseResponse
import com.NearHero.models.BusinessError
import com.efly.MyApplication.Companion.context
import com.efly.utils.error.ErrorCode
import com.google.gson.Gson
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import javax.net.ssl.SSLHandshakeException

open class BaseViewModel : ViewModel() {


    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()
    internal var subscription: Disposable

    init {
        subscription = CompositeDisposable()

    }

    fun onRetrieveDataError(error: Throwable?) {

        getErrorFromBackend(error)

        loadingVisibility.value = View.GONE
//        else
//            errorMessage.value = "Error Occured , Please try again later ..."
    }

    private fun getErrorFromBackend(e: Throwable?) {
        val businessError: BusinessError
        if (e is HttpException) {
            val httpException = e as HttpException?
            val errorBody = httpException?.let { it.response().errorBody() }
            val baseResponse =
                Gson().fromJson<BaseResponse>(errorBody?.string(), BaseResponse::class.java)
            if (baseResponse != null && baseResponse.message?.trim() != "") {
                businessError = BusinessError().apply {
                    // ToDO to be checked and altered accordingly
                    // CODE
                    val CODE = -1234321
                    errorCode = CODE
                    errorMessage = baseResponse.code + "\n" + baseResponse.details
                    throwable = e
                }

                onFailed(businessError)
            }
        } else if (e is NullPointerException) {
            getErrorFromBackend(e.cause)
        } else if (e?.cause is retrofit2.adapter.rxjava2.HttpException) {
            val exception = e.cause as retrofit2.adapter.rxjava2.HttpException?
            val errorBody = exception?.let { it.response().errorBody() }
            val baseResponse =
                Gson().fromJson<BaseResponse>(errorBody?.string(), BaseResponse::class.java)
            businessError = BusinessError().apply {
                // CODE
                val CODE = -1234321
                errorCode = CODE
                errorMessage = baseResponse.code + "\n" + baseResponse.details
                throwable = e
            }

            onFailed(businessError)
        } else if (e is SocketTimeoutException) {
            businessError = BusinessError().apply {
                errorCode = ErrorCode.TIMEOUT
                errorMessage = context.getString(BusinessError.getErrorMessage(errorCode!!))
                throwable = e
            }
            onFailed(businessError)
        } else if (e is ConnectException) {
            businessError = BusinessError().apply {
                errorCode = ErrorCode.CONNECTION_FAILED
                errorMessage = context.getString(BusinessError.getErrorMessage(errorCode!!))
                throwable = e
            }
            onFailed(businessError)
        } else if (e is SSLHandshakeException) {
            businessError = BusinessError().apply {
                errorCode = ErrorCode.SSL_HANDSHAKE_FAILED
                errorMessage = context.getString(BusinessError.getErrorMessage(errorCode!!))
                throwable = e
            }
            onFailed(businessError)
        } else if (e is CompositeException) {
            val compositeException = e as CompositeException?
            val exceptions = compositeException?.exceptions
            exceptions?.forEach {
                getErrorFromBackend(it)
            }

        } else {
            e?.printStackTrace()
//            businessError = BusinessError().apply {
//                errorCode = 0
//                val errorBody = (e as HttpException).response().errorBody()
//                errorMessage =
//                    Gson().fromJson<BaseResponse>(errorBody?.string(), BaseResponse::class.java)
//                        .message
//                throwable = e
//            }
//            onFailed(businessError)
        }
    }

    private fun onFailed(businessError: BusinessError) {

        errorMessage.value = businessError.errorMessage
    }


    fun onRetrieveDataFinish() {

        loadingVisibility.value = View.GONE
    }

    fun onRetrieveDataStart() {
        errorMessage.value = null
        loadingVisibility.value = View.VISIBLE
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}