package com.NearHero.models

import com.efly.R
import com.efly.utils.error.ErrorCode

class BusinessError : Exception() {

    var errorCode: Int? = null
    var errorMessage: String? = null
    var throwable: Throwable? = null

    companion object {
        fun getErrorMessage(errorCode: Int): Int {
            return when (errorCode) {
                ErrorCode.TIMEOUT -> {
                    R.string.error_timeout
                }
                ErrorCode.CONNECTION_FAILED -> {
                    R.string.error_connection_failed
                }
                ErrorCode.SSL_HANDSHAKE_FAILED -> {
                    R.string.error_no_internet
                }

                else -> {
                    R.string.error_timeout
                }
            }
        }
    }
}
