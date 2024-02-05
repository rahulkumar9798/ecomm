package com.example.firebaseecommerce.BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseecommerce.category.CategoryModal
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.R
import com.example.firebaseecommerce.category.RecyclerCategoryAdapter
import com.example.firebaseecommerce.category.RecyclerProductAdpater
import com.example.firebaseecommerce.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)


        var arrCatList =ArrayList<CategoryModal>().apply {
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
            add(CategoryModal(R.drawable.shoes, "shose"))
        }

        binding.recyclerCategory.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.recyclerCategory.adapter = RecyclerCategoryAdapter(requireContext(), arrCatList)



        var arrProductList = ArrayList<ProductModal>().apply {

            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))

        }


        binding.recyclerProduct.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerProduct.adapter = RecyclerProductAdpater(requireContext(), arrProductList)



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