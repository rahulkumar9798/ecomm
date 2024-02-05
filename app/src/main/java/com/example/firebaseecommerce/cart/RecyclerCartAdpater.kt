package com.example.firebaseecommerce.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.databinding.MycartRowBinding

class RecyclerCartAdpater(val context: Context, val arrCart:ArrayList<CartModel>) : RecyclerView.Adapter<RecyclerCartAdpater.ViewHolder>() {
    class ViewHolder(val binding: MycartRowBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(MycartRowBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun getItemCount(): Int {
        return arrCart.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.proimg.setImageResource(arrCart[position].img)
        holder.binding.catTitle.text =arrCart[position].productName
        holder.binding.productPrice.text =arrCart[position].productPrice







    }
}