package com.innovant.ecommerce



data class Data (

	val id : Int,
	val name : String,
	val short_description : String,
	val description : String,
	val specification : String,
	val shipping_free_returns : String,
	val sKU : String,
	val is_used : Int,
	val regular_price : Double,
	val final_price : Double,
	val currency_code : String,
	val remaining_quantity : Int,
	val is_featured : Int,
	val is_gift : Int,
	val new_from_date : String,
	val new_to_date : String,
	val brand_name : String,
	val image : String,
	val images : List<String>,
	val video : String,
	val configurable_option : List<Configurable_option>,
	val related_products : List<Related_products>,
	val reviews : List<String>,
	val is_saleable : Int,
	val product_type : String,
	val associated_products : List<Associated_products>,
	val item_in_cart : Int,
	val item_in_wishlist : Int,
	val average_rating : Int,
	val size_guide : String,
	val shop_id : Int,
	val shop : String,
	val shop_description : String,
	val shop_image : String,
	val delivery_time : String,
	val delivery_time_text : String
)