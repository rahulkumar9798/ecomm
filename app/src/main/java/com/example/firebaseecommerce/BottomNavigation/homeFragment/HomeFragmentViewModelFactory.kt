package com.example.firebaseecommerce.BottomNavigation.homeFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.firebaseecommerce.dataremote.AppRepository

class HomeFragmentViewModelFactory(val appRepository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return HomeFragmentViewModel(appRepository) as T
    }

}