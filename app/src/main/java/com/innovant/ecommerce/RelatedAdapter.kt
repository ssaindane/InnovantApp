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

class RelatedAdapter(var context: Context, var relatedProductsList: List<Related_products>) :
    RecyclerView.Adapter<RelatedAdapter.ItemHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        val itemHolder =
            LayoutInflater.from(parent.context).inflate(R.layout.related_list_item, parent, false)
        return ItemHolder(itemHolder)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        val relatedItem = relatedProductsList.get(position)
        val image_url: String = relatedItem.image

        Glide.with(context)
            .load(image_url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .into(holder.productImage)

        holder.brand.text = relatedItem.brand_name
        holder.currencyCode.text = relatedItem.currency_code
        holder.finalPrice.text = relatedItem.final_price.toString()
        holder.regularPrice.text = Html.fromHtml("<strike>" + relatedItem.regular_price.toString() + "</strike>")
    }

    override fun getItemCount(): Int {
        return relatedProductsList.size
    }

    class ItemHolder(itemHolder: View) : RecyclerView.ViewHolder(itemHolder) {
        var brand: TextView = itemHolder.findViewById(R.id.brand)
        var currencyCode: TextView = itemHolder.findViewById(R.id.currencyCode)
        var finalPrice: TextView = itemHolder.findViewById(R.id.final_price)
        var regularPrice: TextView = itemHolder.findViewById(R.id.regular)
        var productImage: ImageView = itemHolder.findViewById(R.id.product_image)


    }
}
