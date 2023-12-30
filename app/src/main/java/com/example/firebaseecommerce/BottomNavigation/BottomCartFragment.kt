package com.example.firebaseecommerce.BottomNavigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.firebaseecommerce.CartModel
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.R
import com.example.firebaseecommerce.RecyclerCartAdpater
import com.example.firebaseecommerce.RecyclerProductAdpater
import com.example.firebaseecommerce.databinding.FragmentBottomCartBinding

class BottomCartFragment : Fragment() {
    lateinit var binding: FragmentBottomCartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBottomCartBinding.inflate(layoutInflater, container, false)


        var arrcartList = ArrayList<CartModel>().apply {

            add(CartModel(R.drawable.tshirt, "Women Sweter", "Rs 500/-"))
            add(CartModel(R.drawable.earbut, "Women Sweter", "Rs 500/-"))
            add(CartModel(R.drawable.tshirt, "Women Sweter", "Rs 200/-"))
            add(CartModel(R.drawable.earbut, "Women Sweter", "Rs 500/-"))
            add(CartModel(R.drawable.tshirt, "Women Sweter", "Rs 500/-"))
            add(CartModel(R.drawable.earbut, "Women Sweter", "Rs 500/-"))

        }


        binding.cartRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.cartRecycler.adapter = RecyclerCartAdpater(requireContext(), arrcartList)





        // Inflate the layout for this fragment
        return binding.root
    }


}