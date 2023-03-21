package com.alex.ecommercebasicmvvm.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val imageURL: String,
    val price: Float,
    val description: String
):Parcelable
