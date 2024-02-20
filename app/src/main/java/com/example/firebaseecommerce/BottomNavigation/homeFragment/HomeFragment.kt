package com.example.firebaseecommerce.BottomNavigation.homeFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.category.CategoryModel
import com.example.firebaseecommerce.category.RecyclerCategoryAdapter
import com.example.firebaseecommerce.productDetails.RecyclerProductAdpater
import com.example.firebaseecommerce.databinding.FragmentHomeBinding
import com.example.firebaseecommerce.dataremote.AppRepository

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    lateinit var homeFragmentViewModel: HomeFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        homeFragmentViewModel = ViewModelProvider(this,HomeFragmentViewModelFactory(AppRepository()))[HomeFragmentViewModel::class.java]


       // homeFragmentViewModel.getBanners()

        //for Category Start ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        homeFragmentViewModel.getCategories()

        homeFragmentViewModel.mutableCatData.observe(this.viewLifecycleOwner){cat->

            binding.recyclerCategory.layoutManager =LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
            binding.recyclerCategory.adapter =RecyclerCategoryAdapter(requireContext(), cat as ArrayList<CategoryModel>)
        }

        //for Category Close :::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::


        //for Product Start ////////////////////////////////////////////////////////////////////



        homeFragmentViewModel.getProducts()
        homeFragmentViewModel.mutableProductData.observe(this.viewLifecycleOwner){products->
            binding.recyclerProduct.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerProduct.adapter =
                RecyclerProductAdpater(requireContext(), products as ArrayList<ProductModal>)
        }



        //for Product Close ////////////////////////////////////////////////////////////////////





//        homeFragmentViewModel.mutableBannerData.observe(this.viewLifecycleOwner){banners->
//
//        }










//        var arrProductList = ArrayList<ProductModal>().apply {
//
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
//
//        }
//
//
//        binding.recyclerProduct.layoutManager = GridLayoutManager(requireContext(), 2)
//        binding.recyclerProduct.adapter = RecyclerProductAdpater(requireContext(), arrProductList)



        // Inflate the layout for this fragment

        return binding.root
    }

//    override fun onBackPressed() {
//        val exitDialog = AlertDialog.Builder(requireContext())
//
//        exitDialog.setCancelable(false)  //not cancel nahi kya ja sakta
//
//        exitDialog.setTitle("Exit")
//        exitDialog.setMessage("are you sure Exit")
//        exitDialog.setIcon(android.R.drawable.gallery_thumb)
//
//
//
//        exitDialog.setPositiveButton("Yes"
//        ) { p0, p1 -> super.onBackPressedDispatcher.onBackPressed()}
//
//        exitDialog.setNegativeButton("No"
//        ) { p0, p1 -> }
//
//        exitDialog.show()
//    }


}