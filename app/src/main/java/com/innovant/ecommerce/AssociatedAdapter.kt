package com.innovant.ecommerce

import android.content.Context
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AssociatedAdapter(var context: Context, var associatedProductslist: List<Associated_products>) :
    RecyclerView.Adapter<AssociatedAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.associated_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val associatedItem = associatedProductslist.get(position)
        val image_url: String = associatedItem.image

        Glide.with(context)
            .load(image_url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.productImage)

        holder.brand.text = associatedItem.brand_name
        holder.currencyCode.text = associatedItem.currency_code
        holder.finalPrice.text = associatedItem.final_price.toString()
        holder.regularPrice.text = Html.fromHtml("<strike>" + associatedItem.regular_price.toString() + "</strike>")
    }

    override fun getItemCount(): Int {
        return associatedProductslist.size
    }

    class ItemHolder(itemHolder: View) : RecyclerView.ViewHolder(itemHolder) {
        var brand: TextView = itemHolder.findViewById(R.id.brand)
        var currencyCode: TextView = itemHolder.findViewById(R.id.currencyCode)
        var finalPrice: TextView = itemHolder.findViewById(R.id.final_price)
        var regularPrice: TextView = itemHolder.findViewById(R.id.regular)
        var productImage: ImageView = itemHolder.findViewById(R.id.product_image)


    }
}
