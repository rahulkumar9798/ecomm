package com.example.firebaseecommerce.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.R
import com.example.firebaseecommerce.databinding.ActivityCategoryPitemBinding

class CategoryPItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryPitemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryPitemBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var arrProductList = ArrayList<ProductModal>().apply {

            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))
            add(ProductModal(R.drawable.earbut, "wireless HeadPhone", "Rs 500/-"))

        }


        binding.recyclerProduct.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerProduct.adapter = RecyclerProductAdpater(this, arrProductList)
    }
}