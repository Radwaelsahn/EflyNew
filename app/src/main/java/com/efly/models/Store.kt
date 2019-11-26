//package com.efly.models
//
//import com.efly.models.CategoryModel
//import com.efly.models.ProductModel
//import com.google.gson.annotations.Expose
//import com.google.gson.annotations.SerializedName
//
//
//import java.util.Arrays
//
//class Store {
//
//    @SerializedName("entity_id")
//    @Expose
//    var storeCode: String? = null
//
//    @SerializedName("shop_url")
//    @Expose
//    var storeName: String? = null
//
//    @SerializedName("color")
//    @Expose
//    var storeColor: String? = null
//
//    @SerializedName("banner_pic")
//    @Expose
//    val storeBnr: String? = null
//
//    @SerializedName("min_order_value")
//    @Expose
//    var minValue: String? = null
//
//    @SerializedName("hasOffers")
//    @Expose
//    var isHasOffers: Boolean = false
//
//    private val active: String? = null
//    @SerializedName("working_from_time")
//    var from: String? = null
//
//    @SerializedName("working_to_time")
//    var to: String? = null
//
//    @SerializedName("delivery_duration")
//    var delivery: String? = null
//
//    var fees = "4.59"
//
//    @SerializedName("logo_pic")
//    var logo: String? = null
//
//    var icon: String? = null
//
//    private val seller_slider: String? = null
//    //    List<TopSlider> sliders = seller_slider.
//
//    var payment = Arrays.asList(*arrayOf("Stripe"))
//
//    var offersProducts: List<ProductModel>? = null
//
//    var categories: List<CategoryModel>? = null
//
//    var featuredProducts: List<ProductModel>? = null
//
//    val isActive: Boolean?
//        get() = if (active != null && active == "1") true else false
//
//    override fun toString(): String {
//        return "StoresItem{" +
//                "storeCode='" + storeCode + '\''.toString() +
//                //", view=" + view +
//                ", storeName='" + storeName + '\''.toString() +
//                ", storeColor='" + storeColor + '\''.toString() +
//                ", minValue='" + minValue + '\''.toString() +
//                ", hasOffers=" + isHasOffers +
//                ", active=" + active +
//                ", from='" + from + '\''.toString() +
//                ", to='" + to + '\''.toString() +
//                ", delivery='" + delivery + '\''.toString() +
//                ", fees='" + fees + '\''.toString() +
//                ", logo='" + logo + '\''.toString() +
//                ", outer_banner ='" + storeBnr + '\''.toString() +
//                ", icon='" + icon + '\''.toString() +
//                ", payment=" + payment +
//                '}'.toString()
//    }
//
//    //    public List<TopSlider> getSeller_slider() {
//    //        return seller_slider;
//    //    }
//    //
//    //    public void setSeller_slider(List<TopSlider> seller_slider) {
//    //        this.seller_slider = seller_slider;
//    //    }
//}
