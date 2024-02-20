package com.example.firebaseecommerce.BottomNavigation.homeFragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.category.CategoryModel
import com.example.firebaseecommerce.dataremote.AppRepository

class HomeFragmentViewModel(val appRepository: AppRepository)  : ViewModel(){

    var mutableErrorData = MutableLiveData<String>()

    //var mutableBannerData = MutableLiveData<List<BannerModel>>()


//    fun getBanners(){
//        mutableBannerData = appRepository.getBanners()
//    }


    //get Category Start==========================================================
    var mutableCatData = MutableLiveData<List<CategoryModel>>()
    fun getCategories(){
        mutableCatData = appRepository.getCategories()
    }

    //get Category Close==========================================================



    //get Product Start """"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""

    var mutableProductData = MutableLiveData<List<ProductModal>>()

    fun getProducts(){
        mutableProductData = appRepository.getProducts()
    }

    //get Product Close """"""""""""""""""""""""""""""""""""""""""""""""""""""""""""""








}