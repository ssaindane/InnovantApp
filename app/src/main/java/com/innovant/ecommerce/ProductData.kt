package com.innovant.ecommerce

import com.innovant.ecommerce.Data

data class ProductData (

	val success : Boolean,
	val status : Int,
	val message : String,
	val data : Data
)