package com.example.firebaseecommerce.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseecommerce.dataremote.AppRepository

class CatViewModelFactory (private val appRepository: AppRepository, private val catId:String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CategoryViewModel(appRepository, catId) as T
    }


}