package com.innovant.ecommerce

import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private var data: Data? = null
    var isVisible : Boolean? = false
    private lateinit var associatedAdapter: AssociatedAdapter
    private lateinit var relatedAdapter: RelatedAdapter
    private lateinit var related_recyclerView: RecyclerView
    private lateinit var associated_recyclerView: RecyclerView
    private lateinit var itemViewModel: ItemViewModel
    private var brand: TextView? = null
    private var name: TextView? = null
    private var currency_code: TextView? = null
    private var final_price: TextView? = null
    private var regular_price: TextView? = null
    private var shortDesc: TextView? = null
    private var block: TextView? = null
    private lateinit var itemImage: ImageView
    private var plusImage: ImageView? = null
    private lateinit var color_spinner : Spinner
    private lateinit var size_spinner : Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        brand = findViewById(R.id.brand)
        name = findViewById(R.id.name)
        currency_code = findViewById(R.id.currencyCode)
        final_price = findViewById(R.id.final_price)
        regular_price = findViewById(R.id.regular)
        block = findViewById(R.id.block)
        shortDesc = findViewById(R.id.product_description)
        itemImage = findViewById(R.id.item_image)
        plusImage = findViewById(R.id.desc_plus_image)
        color_spinner = findViewById(R.id.color_spinner)
        size_spinner = findViewById(R.id.size_spinner)

        plusImage?.setOnClickListener{
            if(isVisible!!){
                isVisible = false
                shortDesc!!.visibility = View.GONE
            }else{
                isVisible = true
                shortDesc?.text = data!!.description
                shortDesc!!.visibility = View.VISIBLE
            }
        }

        itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        itemViewModel.getItemData(1812, "en", "KW")

        itemViewModel.itemData.observe(this, {

            if (itemViewModel.itemData.value != null) {
                data = itemViewModel.itemData.value!!.data
                
                bindViews()
            }
        })
    }

    private fun bindViews() {
        brand?.text = data!!.brand_name
        name?.text = data!!.name
        currency_code?.text = data!!.currency_code
        final_price?.text = data!!.final_price.toString()
        regular_price?.text = Html.fromHtml("<strike>" + data!!.regular_price.toString() + "</strike>")
        //  shortDesc?.text = data!!.description

        val id = data!!.id
        block?.text =
            data!!.shop.toUpperCase(Locale.ROOT) + " ID : " + id.toString()

        Glide.with(this)
            .load(data!!.image)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(itemImage)

        val color_attributes =
            data!!.configurable_option[1].attributes
        val color_options: ArrayList<String> = ArrayList()
        color_options.add("Select Color")
        for (value in color_attributes) {
            value.value
            color_options.add(value.value)
        }

        val related_products_list = data!!.related_products
        val associated_products_list =
            data!!.associated_products

        val size_attributes =
            data!!.configurable_option[0].attributes
        val size_options: ArrayList<String> = ArrayList()
        size_options.add("Select Size")
        for (value in size_attributes) {
            value.value
            size_options.add(value.value)
        }

        size_spinner.adapter = ArrayAdapter<String>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            size_options
        )
        color_spinner.adapter = ArrayAdapter<String>(
            this,
            R.layout.support_simple_spinner_dropdown_item,
            color_options
        )

        initializeRelatedRecyclerView(related_products_list)
        initializeassociatedRecyclerView(associated_products_list)
    }

    private fun initializeRelatedRecyclerView(relatedProductsList: List<Related_products>) {
        related_recyclerView = findViewById<View>(R.id.related_recycler) as RecyclerView
        relatedAdapter = RelatedAdapter(applicationContext, relatedProductsList)
        related_recyclerView.setAdapter(relatedAdapter)
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        related_recyclerView.setLayoutManager(mLayoutManager)
        related_recyclerView.setHasFixedSize(true)
        related_recyclerView.setItemAnimator(DefaultItemAnimator())
    }

    private fun initializeassociatedRecyclerView(associated_products_list: List<Associated_products>) {
        associated_recyclerView = findViewById<View>(R.id.associated_recycler) as RecyclerView
        associatedAdapter = AssociatedAdapter(applicationContext, associated_products_list)
        associated_recyclerView.setAdapter(associatedAdapter)
        val mLayoutManager: RecyclerView.LayoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        associated_recyclerView.setLayoutManager(mLayoutManager)
        associated_recyclerView.setHasFixedSize(true)
        associated_recyclerView.setItemAnimator(DefaultItemAnimator())
    }

}