package com.example.firebaseecommerce.productDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.dataremote.AppRepository

class ProductDetailsViewModel(val appRepository: AppRepository, private val pid :String)  : ViewModel() {





    var productModel = MutableLiveData<ProductModal?>()

    fun getProductsDetails(){
        productModel = appRepository.getProductDetails(pid)
    }
}