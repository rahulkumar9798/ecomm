package com.example.firebaseecommerce.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.MainActivity
import com.example.firebaseecommerce.databinding.MycartRowBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Picasso

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

//        holder.binding.proimg.setImageResource(arrCart[position].img)
//        holder.binding.catTitle.text =arrCart[position].productName
//        holder.binding.productPrice.text =arrCart[position].productPrice

        Picasso.get().load(arrCart[position].productImg).into(holder.binding.proimg)
        holder.binding.catTitle.text =arrCart[position].productTitle
       // holder.binding.productPrice.text =arrCart[position].productDiscountPrice.toString()
        holder.binding.productPrice.text = "\u20B9 ${arrCart[position].productDiscountPrice.toInt()}"
        holder.binding.txtQty.text = arrCart[position].productQty.toString()

        val userId = (context as MainActivity).getuserId()





        holder.binding.txtAdd.setOnClickListener {


            var qty = arrCart[position].productQty
            qty++

            FirebaseFirestore
                .getInstance()
                .collection("User")
                .document(userId)
                .collection("myCart")
                .document(arrCart[position].cartId)
                .update("productQty", qty).addOnSuccessListener {
                    Toast.makeText(context, "one Product Add", Toast.LENGTH_SHORT).show()

                }.addOnFailureListener {
                    Toast.makeText(context, "Not Product Add failed", Toast.LENGTH_LONG).show()
                }



        }

        holder.binding.txtSub.setOnClickListener {


            var qty = arrCart[position].productQty
            if(qty>1) {
                qty--

                FirebaseFirestore
                    .getInstance()
                    .collection("User")
                    .document(userId)
                    .collection("myCart")
                    .document(arrCart[position].cartId)
                    .update("productQty", qty).addOnSuccessListener {
                        Toast.makeText(context, "one Product Add", Toast.LENGTH_SHORT).show()

                    }.addOnFailureListener {
                        Toast.makeText(context, "Not Product Add failed", Toast.LENGTH_LONG).show()
                    }
            } else {
                FirebaseFirestore
                    .getInstance()
                    .collection("User")
                    .document(userId)
                    .collection("myCart")
                    .document(arrCart[position].cartId)
                    .delete().addOnCompleteListener {
                        if (it.isSuccessful){
                            Toast.makeText(context, "Remove Product to Cart", Toast.LENGTH_LONG).show()
                        }
                    }
            }



        }

        holder.binding.imgDelete.setOnClickListener{

            FirebaseFirestore
                .getInstance()
                .collection("User")
                .document(userId)
                .collection("myCart")
                .document(arrCart[position].cartId)
                .delete().addOnCompleteListener {
                    if (it.isSuccessful){
                        Toast.makeText(context, "delete Product to Cart", Toast.LENGTH_LONG).show()
                    }
                }
        }








    }
}