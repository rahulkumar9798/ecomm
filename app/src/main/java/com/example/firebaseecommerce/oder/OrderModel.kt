package com.example.firebaseecommerce.oder

import com.example.firebaseecommerce.cart.CartModel

data class OrderModel(
    var orderId: String = "",
    var cartItems: ArrayList<CartModel> = arrayListOf(),
    var totalAmount: Double = 0.0,
    var userId: String = "",
    var status: Int = 1, //1 -> placed, 2->in transit, 3->returned, 4->refund, 5->cancelled
)