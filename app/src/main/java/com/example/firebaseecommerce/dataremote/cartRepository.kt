package com.example.firebaseecommerce.dataremote

import androidx.lifecycle.MutableLiveData
import com.example.firebaseecommerce.cart.CartModel
import com.example.firebaseecommerce.category.CategoryModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.snapshots
import kotlinx.coroutines.flow.FlowCollector

class cartRepository {

    val fireStoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
    val firesBaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    companion object{
        val COLLECTION_CAT = "category"
        val COLLECTION_MYCART = "myCart"

        val COLLECTION_PRODUCT_ID = "productId"
    }




    var totalAmt = 0.0

    suspend fun getCartItem(userId : String) : MutableLiveData<List<CartModel>>{
        //var mutableCartData = MutableLiveData<CartModel?>()
        val mutableCartData = MutableLiveData<List<CartModel>>()

        fireStoreDB
            .collection("User")
            .document(userId)
            .collection(COLLECTION_MYCART)
            .snapshots()
            .collect {
                val arryCart = ArrayList<CartModel>()
                for (eachCartItem in it.documents) {

                    val cartModel = eachCartItem.toObject(CartModel::class.java)
                    arryCart.add(cartModel!!)
                    //totalAmt += (cartModel!!.productQty*cartModel.productDiscountPrice)

                }
                mutableCartData.value = arryCart
            }

        return mutableCartData
    }














}