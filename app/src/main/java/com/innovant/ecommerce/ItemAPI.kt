package com.innovant.ecommerce

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemAPI {

    @GET("product-details?")
    fun getItems(
            @Query("product_id") product_id: Int,
            @Query("lang") lang: String,
            @Query("store") store: String
    ): Call<ProductData>

}
