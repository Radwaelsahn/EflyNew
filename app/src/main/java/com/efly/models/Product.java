//package com.efly.models;
//
//import android.os.Parcelable;
//
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
//
//public class Product implements Parcelable {
//
//    //{"id":8,"description":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ","price":{"regular_price":19.99,"special_price":18,"min_tier_price":null},"sku":"efly-30087510069","name":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ","image":"https:\/\/73a010df04-8500.nxcli.net\/media\/catalog\/product\/cache\/74c1057f7991b4edb2bc7bdaa94de933\/3\/0\/30087510069_1.jpg","image_resized":"https:\/\/73a010df04-8500.nxcli.net\/static\/version1572177863\/frontend\/Magento\/luma\/en_US\/Magento_Catalog\/images\/product\/placeholder\/.jpg","type_id":"simple","is_salable":1,"media_gallery_sizes":[{"type":"image","full":"https:\/\/73a010df04-8500.nxcli.net\/media\/catalog\/product\/cache\/8d4d2075b1a30681853bef5bdc41b164\/3\/0\/30087510069_1.jpg","thumbnail":"https:\/\/73a010df04-8500.nxcli.net\/media\/catalog\/product\/cache\/8d4d2075b1a30681853bef5bdc41b164\/3\/0\/30087510069_1.jpg","embed_url":""}],"url_path":"","tier_prices":[],"stock":{"qty":100,"is_in_stock":true,"min_qty":0},"product_links":[],"options":[],"custom_attributes":[{"attribute_code":"gift_message_available","value":"0"},{"attribute_code":"short_description","value":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ"},{"attribute_code":"meta_title","value":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ"},{"attribute_code":"meta_keyword","value":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ"},{"attribute_code":"meta_description","value":"Enfamil Gentlease Infant Formula For Fussiness & Gas 12.4OZ"},{"attribute_code":"is_featured_product","value":"1"},{"attribute_code":"category_ids","value":["2"]},{"attribute_code":"is_in_sale","value":"0"},{"attribute_code":"is_tailored_product","value":"0"},{"attribute_code":"is_in_top_rated","value":"0"},{"attribute_code":"is_on_homepage","value":"0"},{"attribute_code":"homepage_sort_order","value":"100"}]}
//
//    @SerializedName("short_description")
//    @Expose
//    private
//    String desc;
//
//    @SerializedName("image")
//    @Expose
//    private
//    String imageUrl;
//
//    @SerializedName("special_price")
//    @Expose
//    private
//    String specialPrice;
//
//    //    @SerializedName("product_id")
//    @SerializedName("entity_id")
//    @Expose
//    private
//    String entityId;
//
//    @SerializedName("price")
//    @Expose
//    private
//    String price;
//
//    @SerializedName("name")
//    @Expose
//    private
//    String name;
//
//    @SerializedName("sku")
//    @Expose
//    private
//    String sku;
//
//    @SerializedName("row_total_incl_tax")
//    @Expose
//    private  double totalPrice;
//
//    @SerializedName("qty")
//    @Expose
//    private int count = 0;
//
////    private List<MediaGallerySize> media_gallery_sizes;
//
////    public String getSymbol() {
////        return symbol;
////    }
////
////    public void setSymbol(String symbol) {
////        this.symbol = symbol;
////    }
//
//    public String getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(String imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public String getEntityId() {
//        return entityId;
//    }
//
//    public void setEntityId(String entityId) {
//        this.entityId = entityId;
//    }
//
//
//    public String getPrice() {
//        return price;
//    }
//
//    public void setPrice(String price) {
//        this.price = price;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getSku() {
//        return sku;
//    }
//
//    public void setSku(String sku) {
//        this.sku = sku;
//    }
//
//    public void add() {
//        setCount(getCount() + 1);
//    }
//
//    public void subtract() {
//        if (getCount() > 0) {
//            setCount(getCount() - 1);
//        }
//    }
//
//    public int getCount() {
//        return count;
//    }
//
//    public void setCount(int count) {
//        this.count = count;
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        ProductModel that = (ProductModel) o;
//
//        return getEntityId() != null ? getEntityId().equals(that.getEntityId()) : that.getEntityId() == null;
//
//    }
//
//    @Override
//    public int hashCode() {
//        return getEntityId() != null ? getEntityId().hashCode() : 0;
//    }
//
//    @Override
//    public String toString() {
//        return
//                "ProductModel{" +
////                        ",symbol = '" + symbol + '\'' +
//                        ",image_url = '" + getImageUrl() + '\'' +
////                        ",regular_price_with_tax = '" + regularPriceWithTax + '\'' +
////                        ",stock_level = '" + stockLevel + '\'' +
//                        ",entity_id = '" + getEntityId() + '\'' +
////                        ",url_key = '" + urlKey + '\'' +
////                        ",final_price_with_tax = '" + finalPriceWithTax + '\'' +
//                        ",price = '" + getPrice() + '\'' +
//                        ",name = '" + getName() + '\'' +
//                        ",sku = '" + getSku() + '\'' +
//                        "}";
//    }
//
//    public String getDesc() {
//        return desc;
//    }
//
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getSpecialPrice() {
//        return specialPrice;
//    }
//
//    public void setSpecialPrice(String specialPrice) {
//        this.specialPrice = specialPrice;
//    }
//
//    public double getTotalPrice() {
//        return totalPrice;
//    }
//
////    public List<MediaGallerySize> getMedia_gallery_sizes() {
////        return media_gallery_sizes;
////    }
////
////    public void setMedia_gallery_sizes(List<MediaGallerySize> media_gallery_sizes) {
////        this.media_gallery_sizes = media_gallery_sizes;
////    }
//}