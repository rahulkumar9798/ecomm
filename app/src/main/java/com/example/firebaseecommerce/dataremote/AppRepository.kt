package com.example.firebaseecommerce.dataremote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.firebaseecommerce.ProductModal
import com.example.firebaseecommerce.category.CategoryModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject

class AppRepository {

    val fireStoreDB : FirebaseFirestore = FirebaseFirestore.getInstance()
    val firesBaseAuth : FirebaseAuth = FirebaseAuth.getInstance()

    companion object{
        val COLLECTION_CAT = "category"
        val COLLECTION_SUB_CAT = "sub-category"
        val COLLECTION_PRODUCT = "product"
        val COLLECTION_BANNERS = "banners"

        val COLLECTION_PRODUCT_ID = "productId"
    }



//    val mutableBannerData = MutableLiveData<List<BannerModel>>()
//    val mutableProductData = MutableLiveData<List<ProductModel>>()


    //login
    //signup

    //home
    //getBanners
//    fun getBanners() : MutableLiveData<List<BannerModel>>{
//
//        fireStoreDB.collection(COLLECTION_BANNERS).get().addOnSuccessListener {
//            val arrBannerData = ArrayList<BannerModel>()
//            for(banner in it.documents){
//                arrBannerData.add(banner.toObject(BannerModel::class.java)!!)
//            }
//            mutableBannerData.postValue(arrBannerData)
//
//        }.addOnFailureListener {
//            val arrBannerData = ArrayList<BannerModel>()
//            mutableBannerData.postValue(arrBannerData)
//        }
//
//        return mutableBannerData
//
//    }

    //getCategory Start??????????????????????????????????????????????????????????????????????????????????
    val mutableCatData = MutableLiveData<List<CategoryModel>>()
    val mutableErrorData = MutableLiveData<String>()
    fun getCategories() : MutableLiveData<List<CategoryModel>>{

        fireStoreDB.collection(COLLECTION_CAT)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener {
            val arrCatData = ArrayList<CategoryModel>()
            for(cat in it.documents){
                arrCatData.add(cat.toObject(CategoryModel::class.java)!!)
            }
            mutableCatData.postValue(arrCatData)

        }.addOnFailureListener {
            val arrCatData = ArrayList<CategoryModel>()
            mutableErrorData.postValue("Error: ${it.message}")
            mutableCatData.postValue(arrCatData)
        }

        return mutableCatData

    }


    //getCategory Close ??????????????????????????????????????????????????????????????????????????????????


    //getProduct Start>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    val mutableProductData = MutableLiveData<List<ProductModal>>()
    val mutableErrorProductData = MutableLiveData<String>()
    fun getProducts() : MutableLiveData<List<ProductModal>>{

        fireStoreDB.collection(COLLECTION_PRODUCT).orderBy("createdAt").get().addOnSuccessListener {
            val arrProductData = ArrayList<ProductModal>()
            for(product in it.documents){
                arrProductData.add(product.toObject(ProductModal::class.java)!!)
            }
            mutableProductData.postValue(arrProductData)

        }.addOnFailureListener {
            val arrProductData = ArrayList<ProductModal>()
            mutableErrorProductData.postValue("Error: ${it.message}")
            mutableProductData.postValue(arrProductData)
        }

        return mutableProductData

    }

    //getProduct Close >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    val mutableProductDataByCat = MutableLiveData<List<ProductModal>>()
    val mutableErrorProductDataByCat = MutableLiveData<String>()
    fun getProductByCat(catId : String) :MutableLiveData<List<ProductModal>>{

        val arrProductDataBycat = ArrayList<ProductModal>()

        fireStoreDB.collection(COLLECTION_PRODUCT).whereEqualTo("catId", catId).get().addOnSuccessListener {


            for(product in it.documents){
                arrProductDataBycat.add(product.toObject(ProductModal::class.java)!!)
            }
            mutableProductDataByCat.postValue(arrProductDataBycat)



        }.addOnFailureListener {
            val arrProductDatacat = ArrayList<ProductModal>()
            mutableErrorProductDataByCat.postValue("Error: ${it.message}")
            mutableProductDataByCat.postValue(arrProductDatacat)
        }




        return mutableProductDataByCat

    }






    val mutableProductDetailsData = MutableLiveData<ProductModal>()
    fun getProductDetails(pid : String) : MutableLiveData<ProductModal?>{
        var productModel = MutableLiveData<ProductModal?>()

        fireStoreDB.collection(COLLECTION_PRODUCT).whereEqualTo("productId", pid).get().addOnSuccessListener {

            if(it.documents.size>0) {
               val product = it.documents[0].toObject(ProductModal::class.java)

                productModel.value = product
            }


        }.addOnFailureListener {
            Log.d("Product Details:", "Product not found!!")
        }

        return productModel

    }

























}