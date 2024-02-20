package com.example.firebaseecommerce.cart

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseecommerce.MainActivity
import com.example.firebaseecommerce.R
import com.example.firebaseecommerce.databinding.FragmentBottomCartBinding
import com.example.firebaseecommerce.dataremote.AppRepository
import com.example.firebaseecommerce.dataremote.cartRepository
import com.example.firebaseecommerce.oder.OrderModel
import com.example.firebaseecommerce.productDetails.ProductDetailsFragmentModelFactory
import com.example.firebaseecommerce.productDetails.ProductDetailsViewModel
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class BottomCartFragment : Fragment() {
    lateinit var binding: FragmentBottomCartBinding
    val arrCartList = ArrayList<CartModel>()
    var totalAmt = 0.0

    lateinit var cartViewModel:CartFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBottomCartBinding.inflate(layoutInflater, container, false)

         val userId = (context as MainActivity).getuserId()



        cartViewModel = ViewModelProvider(this,CartFragViewModelFactory(cartRepository(),userId))[CartFragmentViewModel::class.java]




        cartViewModel.getCart()

        cartViewModel.mutableCartData.observe(this.viewLifecycleOwner){

            Log.d("size", it.size.toString())
            binding.cartRecycler.layoutManager = LinearLayoutManager(requireContext())
            binding.cartRecycler.adapter =RecyclerCartAdpater(requireContext(), it as ArrayList<CartModel>)

        }








//        GlobalScope.launch(Dispatchers.Main) {
//
//            FirebaseFirestore
//                .getInstance()
//                .collection("User")
//                .document(userId)
//                .collection("myCart").snapshots().collect{ value ->
//
//                    if(value.documents.size>0) {
//                        arrCartList.clear()
//                        totalAmt = 0.0
////
//                        binding.cartRecycler.visibility = View.VISIBLE
//                        binding.imgEmptyCart.visibility = View.GONE
//                        binding.linearLayout.visibility = View.VISIBLE
//
//
//
//                        for (eachCartItem in value.documents) {
//                            val cartModel = eachCartItem.toObject(CartModel::class.java)
//                            totalAmt += (cartModel!!.productQty*cartModel.productDiscountPrice)
//                            arrCartList.add(cartModel)
//                        }
//
//                        binding.txtTotalAmt.text = "\u20B9 ${totalAmt.toInt()}"
//
//
//                        binding.cartRecycler.layoutManager = LinearLayoutManager(requireContext())
//                        binding.cartRecycler.adapter =RecyclerCartAdpater(requireContext(), arrCartList)
//                    } else {
//                        binding.cartRecycler.visibility = View.GONE
//                        binding.imgEmptyCart.visibility = View.VISIBLE
//                        binding.linearLayout.visibility = View.GONE
//                    }
//                }
//        }





        binding.btnCheckOut.setOnClickListener {

            val userid = (context as MainActivity).getuserId()

            var myOrder = OrderModel(
                UUID.randomUUID().toString(),
                arrCartList,
                totalAmt,
                userid,
                1
            )

            FirebaseFirestore
                .getInstance()
                .collection("Orders")
                .add(myOrder).addOnSuccessListener {
                    Toast.makeText(requireContext(), "Order placed!!", Toast.LENGTH_SHORT).show()

                    //cart empty
                    for(eachCartItem in arrCartList){
                        FirebaseFirestore
                            .getInstance()
                            .collection("User")
                            .document(userid)
                            .collection("myCart")
                            .document(eachCartItem.cartId)
                            .delete()
                    }

                }.addOnFailureListener {
                    Toast.makeText(requireContext(), "Can't place your Order: ${it.message}", Toast.LENGTH_SHORT).show()
                }

        }










        // Inflate the layout for this fragment
        return binding.root
    }


}