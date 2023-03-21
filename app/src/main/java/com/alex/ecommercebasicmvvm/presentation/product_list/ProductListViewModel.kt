package com.alex.ecommercebasicmvvm.presentation.product_list

import androidx.lifecycle.ViewModel
import com.alex.ecommercebasicmvvm.data.Product
import com.alex.ecommercebasicmvvm.data.getProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProductListViewModel: ViewModel() {
    private var _productList = MutableStateFlow<List<Product>>(emptyList())
    val productList: StateFlow<List<Product>> = _productList

    init {
        _productList.value = getProducts()
    }
}