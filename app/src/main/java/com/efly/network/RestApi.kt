package com.efly.network


import com.efly.models.*
import com.efly.models.inputs.*
import com.efly.models.response.*
import com.eflyrx.app.data.model.inputs.AddressInput
import com.eflyrx.app.data.model.inputs.LoginInput
import com.eflyrx.app.data.model.inputs.PaymentInfoInput
import com.eflyrx.app.data.model.response.*
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface RestApi {
    @POST("rest/V1/integration/customer/token")
    fun login(@Body login: LoginInput): Observable<String>

    @POST("rest/V1/customers")
    fun register(@Body register: RegisterInput): Observable<RegisterResponse>

    @PUT("rest/V1/customers/me")
    fun updateAccount(@Body register: UpdateAccountInput): Observable<RegisterResponse>

    @FormUrlEncoded
    @POST("rest/V1/customers/me/password")
    fun updatePassword(
        @Field("password") password: String,
        @Field("new_password") newPassword: String
    ): Observable<GenericResponse>


    @GET("rest/V1/customers/me")
    fun getAccountInfo(): Observable<Customer>
    //@Query("SID") String sessionId);

    @GET("rest/V1/efly/customers/me/logout")
    fun logout(): Observable<Boolean>

    @PUT("rest/V1/customers/me/password")
    fun resetPassword(@Field("currentPassword") currentPassword: String, @Field("newPassword") newPassword: String): Observable<Boolean>

    @GET("rest/V1/efly/sellers/{store_id}/categories")
    fun categories(@Path("store_id") sellerId: String): Observable<List<CategoryModel>>

    @GET("rest/V1/efly/sellers/{store_id}/{cat_id}/products")
    fun getCategoryById(
        @Path("store_id") storeId: String,
        @Path("cat_id") catId: String
    ): Observable<List<List<ProductModel>>>

    //not working
    @GET("rest/V1/efly/sellers/{store_id}/{cat_id}/products")
    fun products(
        @Path("store_id") storeId: String,
        @Path("cat_id") catId: String
    ): Observable<ProductsResponse>

    @GET("rest/V1/efly/products/{id}")
    fun productDetails(@Path("id") id: String): Observable<ProductDetails>

    @GET("rest/V1/products-render-info")
    fun searchProduct(
        @Query("storeId") storeId: String,
        @Query("currencyCode") currencyCode: String,
        @Query("searchCriteria[filter_groups][0][filters][0][field]") name: String,
        @Query("searchCriteria[filter_groups][0][filters][0][condition_type]") like: String,
        @Query("searchCriteria[filter_groups][0][filters][0][value]") query: String

    ):
    //+   "?storeId={store_id}&currencyCode='usd'&searchCriteria[filter_groups][0][filters][0][field]=name&
    // searchCriteria[filter_groups][0][filters][0][value]={search_query}&
    // searchCriteria[filter_groups][0][filters][0][condition_type]=like")
            Observable<ProductsResponse>

    @GET("rest/V1/products-render-info")
    fun searchProductInCat(
        @Query("storeId") storeId: String,
        @Query("currencyCode") currencyCode: String,
        @Query("searchCriteria[filter_groups][0][filters][0][field]") name: String,
        @Query("searchCriteria[filter_groups][0][filters][0][condition_type]") like: String,
        @Query("searchCriteria[filter_groups][0][filters][0][value]") query: String,
        @Query("searchCriteria[filter_groups][0][filters][0][field]") categorygear: String,
        @Query("searchCriteria[filter_groups][0][filters][0][value]") categoryId: String,
        @Query("searchCriteria[filter_groups][0][filters][0][condition_type]") condType: String
    ): Observable<ProductsResponse>

    @GET("rest/V1/directory/countries")
    fun regions(@Query("SID") sessionId: String): Observable<List<RegionFetched>>

    @GET("rest/V1/directory/countries/US")
    fun regionsWithStores(@Query("SID") sessionId: String): Observable<RegionResponse>

    @GET("rest/V1/efly/location-sellers")
    fun stores(
        @Query("latitude") lat: Double,
        @Query("longitude") lng: Double
    ): Observable<List<StoreItem>>

    @GET("rest/V1/efly/sellers")
    fun stores(): Observable<List<StoreItem>>

//    @GET("mobileapi/store/setStore")
//    Observable<AccountResponse> setStore(@Query("store") String storeCode, @Query("lang") String
//            language, @Query("SID") String sessionId);

//    @GET("mobileapi/store/setLanguage")
//    Observable<GenericResponse> setLanguage(@Query("lang") String language,
//                                            @Query("SID") String sessionId);

    @GET("rest/V1/efly/sellers/{id}/home")
    fun storeInfo(@Path("id") storeId: String): Observable<List<StoreItem>>

    @POST("rest/V1/efly/customers/me/address")
    fun submitAddress(@Body address: AddressInput): Observable<AccountResponse>

    @POST("rest/V1/efly/customers/me/address")
    fun updateAddress(@Body address: AddressInput): Observable<AccountResponse>

    @GET("rest/V1/efly/customers/me/address")
    fun getAddressList(): Observable<AddressListResponse>

//    @GET("/mobileapi/address/delete")
//    Observable<GenericResponse> deleteAddress(@Query("address_id") String id,
//                                              @Query("SID") String sessionId);

    @DELETE("rest/V1/efly/customers/me/address/{id}")
    fun deleteAddress(@Path("id") id: String): Observable<Boolean>

    @GET("/mobileapi/address/getAddress")
    fun getAddress(
        @Query("address_id") id: String,
        @Query("SID") sessionId: String
    ): Observable<AddressResponse>

    @GET("/mobileapi/cart/useCoupon")
    fun sendPromoCode(
        @Query("coupon_code") code: String,
        @Query("SID") sessionId: String
    ): Observable<PromoCodeResponse>



//    @GET("rest/V1/efly/orders/mine?searchCriteria=null")
//    fun getOrderHistory(): Observable<OrderHistoryResponse>


//    @GET("rest/V1/efly/orders/{id}/order-info")
//    fun getOrderHistoryDetail(@Path("id") orderId: String): Observable<OrderHistoryModel>

//    /// cart logged in ////
//    @POST("rest/V1/carts/mine")
//    fun addCartQuote(@Body quote: Quote): Observable<Int>

    @GET("rest/V1/carts/mine/totals")
    fun getCartTotals(): Observable<CollectTotals>

    @POST("rest/V1/carts/mine/collect-totals")
    fun editCartTotals(@Body collectTotals: CollectTotals): Observable<Int>

    @GET("rest/V1/carts/mine/payment-information")
    fun getPaymentInfo(): Observable<PaymentInfoResponse>

    @POST("rest/V1/carts/mine/payment-information")
    fun savePaymentInfo(@Body paymentInfoInput: PaymentInfoInput): Observable<Int>

    @POST("rest/V1/carts/mine/items")
    fun addItemToCart(@Body input: AddCartItemInput): Observable<AddToCartResponse>

    @PUT("rest/V1/carts/mine/items/{id}")
    fun editCartItem(@Path("id") id: String, @Body input: AddCartItemInput): Observable<AddToCartResponse>

    @DELETE("rest/V1/carts/mine/items/{id}")
    fun removeCartItem(@Path("id") id: String): Observable<Boolean>

    @GET("rest/V1/carts/mine")
    fun getCartInfo(): Observable<CartInfoResponse>

    /// cart guest ////
    @POST("rest/V1/guest-carts")
    fun createGuestCart(): Observable<String>

    @DELETE("rest/V1/guest-carts/{session_id}/items/{id}")
    fun removeCartItemGuest(@Path("id") id: String, @Path("session_id") session_id: String): Observable<Boolean>

    @GET("rest/V1/guest-carts/{id}/totals")
    fun getCartTotalsGuest(@Path("id") id: String): Observable<CollectTotals>

    @GET("rest/V1/guest-carts/{session_id}/items")
    fun getCartInfoGuest(@Path("session_id") session_id: String): Observable<CartInfoResponse>

    @PUT("rest/V1/guest-carts/{session_id}/items/{id}")
    fun editCartItemGuest(@Path("id") id: String, @Path("session_id") session_id: String, @Body input: AddCartItemInput): Observable<AddToCartResponse>

    @POST("rest/V1/guest-carts/{session_id}/items")
    fun addItemToCartGuest(@Path("session_id") session_id: String, @Body input: AddCartItemInput): Observable<AddToCartResponse>

    ///////////////////
    @POST("rest/V1/carts/mine/estimate-shipping-methods")
    fun sendShippingAddress(@Body input: ShippingAddressInput): Observable<List<ShippingAddressResponse>>

    @POST("rest/V1/carts/mine/shipping-information")
    fun sendShippingMethod(@Body input: ShippingMethodInput): Observable<ShippingMethodResponse>

    @POST("rest/V1/carts/mine/payment-information")
    fun sendPaymentInfo(@Body input: PaymentInfoInput): Observable<Int>


}