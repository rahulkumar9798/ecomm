package com.example.firebaseecommerce.productDetails

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.firebaseecommerce.MainActivity
import com.example.firebaseecommerce.R
import com.example.firebaseecommerce.cart.BottomCartFragment
import com.example.firebaseecommerce.cart.CartModel
import com.example.firebaseecommerce.databinding.ActivityProductDetailsBinding
import com.example.firebaseecommerce.dataremote.AppRepository
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.Calendar
import java.util.UUID

class ProductDetailsActivity : AppCompatActivity() {



    lateinit var binding: ActivityProductDetailsBinding
//    lateinit var ProductDetailsViewModel: ProductDetailsViewModel
//   lateinit var discountedPrice = ""






    val arrColors = ArrayList<ColorModal>().apply {
        add(ColorModal("F57C00", R.color.orange))
        add(ColorModal("7B1FA2", R.color.purple))
        add(ColorModal("1976D2", R.color.blue))
        add(ColorModal("ACA298", R.color.grey))
        add(ColorModal("C2185B", R.color.pink))
        add(ColorModal("D32F2F", R.color.red))
        add(ColorModal("FFFFFF", R.color.white))
        add(ColorModal("000000", R.color.black))
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backNow.setOnClickListener {
            finish()
        }



        val pid = intent.getStringExtra("pid")


       val  ProductDetailsViewModel = ViewModelProvider(this,ProductDetailsFragmentModelFactory(AppRepository(), pid!!))[ProductDetailsViewModel::class.java]
        ProductDetailsViewModel.getProductsDetails()
        ProductDetailsViewModel.productModel.observe(this@ProductDetailsActivity){



            //for slider Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            val bannerList = ArrayList<SlideModel>()
            for(eachImg in it!!.productImages){
                bannerList.add(SlideModel(eachImg, "", ScaleTypes.FIT))
            }
            binding.bannerImageSlider.setImageList(bannerList)

            //for slider Close>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
            binding.txtProductTitle.text = it.productTitle

            val categoryId = it.catId
            val subCatId = it.subCatId
            val productId = it.productId
            val productTitle = it.productTitle
            val productDesc = it.productDesc
            val productDiscountPer = it.productDiscountPer
            val productImg = it.productImg
            val productUnit = it.productUnit



            val productPrice = it.productPrice
            binding.txtProductPrice.text =  "\u20B9 ${productPrice.toInt()}"
            binding.txtProductPrice.paintFlags = binding.txtProductPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG



            //setting disCountedPrice Start?????????????????????????????????????????????????????????????????????
            val discountedPrice = it.productPrice - (it.productPrice*(it.productDiscountPer/100))
            binding.txtProductDiscountedPrice.text = "\u20B9 ${discountedPrice.toInt()}"

            //setting disCountedPrice close?????????????????????????????????????????????????????????????????????

            binding.btnproductAdd.setOnClickListener {

                val userId = FirebaseAuth.getInstance().currentUser!!.uid
                val cartId = UUID.randomUUID().toString()



                val time = Calendar.getInstance().timeInMillis

                FirebaseFirestore
                    .getInstance()
                    .collection("User")
                    .document(userId)
                    .collection("myCart")
                    .document(cartId)
                    .set(
                        CartModel(cartId,
                            categoryId,
                            subCatId,
                            productId,
                            productTitle,
                            productDesc,
                            1,
                            productPrice,
                            discountedPrice,
                            productDiscountPer,
                            productImg,
                            "",
                            productUnit,
                            time
                        )
                    ).addOnCompleteListener {
                        if (it.isSuccessful){
//                            binding.btnproductAdd.visibility = View.GONE
//                            binding.btnGotoCart.visibility = View.VISIBLE

//                            binding.btnGotoCart.setOnClickListener {
//                                startActivity(Intent(this@ProductDetailsActivity, BottomCartFragment::class.java))
//
////                                val intent = Intent(this, BottomCartFragment::class.java)
////                                this.startActivity(intent)
//                            }
                            Toast.makeText(this, "Product Add In Cart successfully", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(this, "something went wrong", Toast.LENGTH_SHORT).show()
                        }
                    }


            }



        }






















        binding.recyclerColor.apply {
            layoutManager = LinearLayoutManager(this@ProductDetailsActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = RecyclerColorAdapter(this@ProductDetailsActivity, arrColors)
        }




        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        TabLayoutMediator(binding.tabLayout, binding.viewPager
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Description"
                }
                1 -> {
                    tab.text = "Specifications"
                }
                else -> {
                    tab.text = "Reviews"
                }
            }
        }.attach()

        binding.viewPager.currentItem = 0


    }
}