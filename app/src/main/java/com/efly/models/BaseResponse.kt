package com.NearHero.models

open class BaseResponse {
    val code: String? = null
    val message: String? = null
    val details: ArrayList<Any>? = arrayListOf()
}