package com.efly.repositories

import com.efly.base.BaseRepoistory

class HomeRepository : BaseRepoistory() {


//    fun getNearByHeroes(
//        latitude: String,
//        longitude: String,
//        radius: String
//    ): Observable<NearByHeroesResponseModel> {
//        return restApi.getNearByHeroes(latitude, longitude, radius, LocalRepository.xAccessToken)
//            .onErrorReturn {
//                if (it.message?.contains("access token expired")!!) {
//                    restApi.getNearByHeroes(
//                        latitude, longitude, radius,
//                        postRefreshToken().blockingFirst().accessToken ?: ""
//                    ).blockingFirst()
//                } else null
//            }
//    }
//
//    fun getHomePopulatingData(): Observable<HomePopulateResponseModel> {
//        return restApi.getHomePopulatingData("hero", xAccessToken)
//            .onErrorReturn { null }
//    }
}
