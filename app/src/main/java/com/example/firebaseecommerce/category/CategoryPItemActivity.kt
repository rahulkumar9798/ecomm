package com.example.firebaseecommerce.category

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.databinding.ActivityCategoryPitemBinding
import com.example.firebaseecommerce.dataremote.AppRepository
import com.google.firebase.auth.FirebaseAuth

class CategoryPItemActivity : AppCompatActivity() {
    lateinit var binding: ActivityCategoryPitemBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryPitemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val catId = intent.getStringExtra("catId")

        val  catByProduct = ViewModelProvider(this,CatViewModelFactory(AppRepository(),catId!!))[CategoryViewModel::class.java]
        catByProduct.getProductsByCat()
        catByProduct.mutableProductDataByCat.observe(this@CategoryPItemActivity){

            if(it.size>0) {

                binding.recyclerProduct.visibility = View.VISIBLE
                binding.imgnotFound.visibility = View.GONE

                binding.recyclerProduct.layoutManager = GridLayoutManager(this@CategoryPItemActivity, 2)
                binding.recyclerProduct.adapter =
                    RecyclerProductAdapterbyCategory(this@CategoryPItemActivity, it as ArrayList<ProductModal>)

            }else{
                binding.recyclerProduct.visibility = View.GONE
                binding.imgnotFound.visibility = View.VISIBLE
            }



        }






    }



    fun getuserId(): String {
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}