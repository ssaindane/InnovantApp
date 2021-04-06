package com.innovant.ecommerce

data class Configurable_option (

	val type : String,
	val attribute_id : Int,
	val attribute_code : String,
	val attributes : List<Attributes>
)