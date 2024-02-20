package com.example.firebaseecommerce.cart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.firebaseecommerce.dataremote.AppRepository
import com.example.firebaseecommerce.dataremote.cartRepository
import kotlinx.coroutines.launch

class CartFragmentViewModel(val cartRepository: cartRepository, private val userId :String)  : ViewModel()  {



    var mutableCartData = MutableLiveData<List<CartModel>>()

    fun getCart(){
        viewModelScope.launch {
            mutableCartData = cartRepository.getCartItem(userId)
        }

    }

}