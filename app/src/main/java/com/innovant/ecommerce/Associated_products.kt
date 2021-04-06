package com.innovant.ecommerce

data class Associated_products (

	val id : Int,
	val name : String,
	val short_description : String,
	val description : String,
	val sKU : String,
	val regular_price : Double,
	val final_price : Double,
	val currency_code : String,
	val remaining_quantity : Int,
	val is_featured : Int,
	val new_from_date : String,
	val new_to_date : String,
	val brand_name : String,
	val boutique_name : String,
	val image : String,
	val delivery_time : String,
	val delivery_time_text : String
)