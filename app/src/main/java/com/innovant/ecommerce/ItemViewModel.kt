package com.innovant.ecommerce

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ItemViewModel : ViewModel() {

    companion object{
        var BASE_URL = "http://mobicraftsv2.com/bloc49/api/v3/"
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ItemAPI::class.java)
    }

    val itemData: MutableLiveData<ProductData> = MutableLiveData()

    fun getItemData(product_id:Int, lang: String, store: String)  {
        val call = service.getItems(product_id, lang, store)

        call.enqueue(object : Callback<ProductData> {
            override fun onResponse(call: Call<ProductData>, response: Response<ProductData>) {
                val item = response.body()!!
                itemData.value = item
            }

            override fun onFailure(call: Call<ProductData>, t: Throwable) {
                t.message
            }
        })
    }
}