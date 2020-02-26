package com.blackflag.addtocartflyanimation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.blackflag.addtocartflyanimation.R
import com.blackflag.addtocartflyanimation.adapters.ProductAdapter.ProductHolder
import com.blackflag.addtocartflyanimation.models.Product

class ProductAdapter(private val mContext: Context, private val mList: List<Product>) : RecyclerView.Adapter<ProductHolder>() {
    private var actionListener: ProductItemActionListener? = null
    fun setActionListener(actionListener: ProductItemActionListener?) {
        this.actionListener = actionListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_single_item, parent, false))
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val product = mList[position]
        holder.itemIV.setImageResource(product.resourceId)
        holder.itemCopyIV.setImageResource(product.resourceId)
        holder.itemCopyIV.setOnClickListener { if (actionListener != null) actionListener!!.onItemTap(holder.itemCopyIV) }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ProductHolder(itemView: View) : ViewHolder(itemView) {
        var itemIV: ImageView
        var itemCopyIV: ImageView

        init {
            itemIV = itemView.findViewById<View>(R.id.itemIV) as ImageView
            itemCopyIV = itemView.findViewById<View>(R.id.itemCopyIV) as ImageView
        }
    }

    interface ProductItemActionListener {
        fun onItemTap(imageView: ImageView?)
    }

}