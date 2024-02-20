package com.example.firebaseecommerce.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseecommerce.dataremote.cartRepository

class CartFragViewModelFactory(val cartRepository: cartRepository, private val userId :String):ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CartFragmentViewModel(cartRepository, userId) as T
    }


}