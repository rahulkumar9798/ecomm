package com.example.firebaseecommerce.category

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.dataremote.AppRepository

class CategoryViewModel(val appRepository: AppRepository, val catId : String)  : ViewModel() {




    //get Product By Cat ID Start """"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""

    var mutableProductDataByCat = MutableLiveData<List<ProductModal>>()

    fun getProductsByCat(){
        mutableProductDataByCat = appRepository.getProductByCat(catId)
    }

    //get ProductBy Cat ID Close """"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""



}