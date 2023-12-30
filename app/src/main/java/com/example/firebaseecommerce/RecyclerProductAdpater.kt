package com.example.firebaseecommerce

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.databinding.ProductRowBinding
import com.example.firebaseecommerce.productDetails.ProductDetailsActivity

class RecyclerProductAdpater(val context: Context, val arrProductData: ArrayList<ProductModal>): RecyclerView.Adapter<RecyclerProductAdpater.ViewHolder>() {
    class ViewHolder(val binding: ProductRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ProductRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrProductData.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.productImg.setImageResource(arrProductData[position].img)
        holder.binding.productName.text = arrProductData[position].productName
        holder.binding.productPrice.text = arrProductData[position].productPrice

        holder.binding.productImg.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            context.startActivity(intent)
        }
    }
}