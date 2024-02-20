package com.example.firebaseecommerce.productDetails

import android.content.Context
import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.MainActivity
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.cart.BottomCartFragment
import com.example.firebaseecommerce.cart.CartModel
import com.example.firebaseecommerce.databinding.ProductRowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.UUID

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
        holder.binding.btnAdd.visibility = View.VISIBLE
        holder.binding.btnGotoCart.visibility = View.GONE

        Picasso.get().load(arrProductData[position].productImg).into(holder.binding.productImg);
        holder.binding.productName.text = arrProductData[position].productTitle
        holder.binding.productPrice.text = "\u20B9 ${arrProductData[position].productPrice.toInt()}"


        holder.binding.cartProductDetails.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("pid", arrProductData[position].productId)
            context.startActivity(intent)
        }

        //add product in cart Start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

        val userId = (context as MainActivity).getuserId()
        val cartId = UUID.randomUUID().toString()
        val discountedPrice = arrProductData[position].productPrice - (arrProductData[position].productPrice*(arrProductData[position].productDiscountPer/100))
        val time = Calendar.getInstance().timeInMillis


        GlobalScope.launch(Dispatchers.Main) {
            holder.binding.btnAdd.setOnClickListener {

                FirebaseFirestore
                    .getInstance()
                    .collection("User")
                    .document(userId)
                    .collection("myCart")
                    .document(cartId)
                    .set(
                        CartModel(
                            cartId,
                            arrProductData[position].catId,
                            arrProductData[position].subCatId,
                            arrProductData[position].productId,
                            arrProductData[position].productTitle,
                            arrProductData[position].productDesc,
                            1,
                            arrProductData[position].productPrice,
                            discountedPrice,
                            arrProductData[position].productDiscountPer,
                            arrProductData[position].productImg,
                            "",
                            arrProductData[position].productUnit,
                            time
                        )
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {

                            Toast.makeText(
                                context,
                                "Product Add In Cart successfully",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Toast.makeText(context, "something went wrong", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }


            }
        }

        //add product in cart Close >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    }
}