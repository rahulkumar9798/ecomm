package com.example.firebaseecommerce.productDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseecommerce.BottomNavigation.homeFragment.HomeFragmentViewModel
import com.example.firebaseecommerce.dataremote.AppRepository

class ProductDetailsFragmentModelFactory(val appRepository: AppRepository, val pid:String) : ViewModelProvider.Factory  {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductDetailsViewModel(appRepository, pid) as T
    }
}