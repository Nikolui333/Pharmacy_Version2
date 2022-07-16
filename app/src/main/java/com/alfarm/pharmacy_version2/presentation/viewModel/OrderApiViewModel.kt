package com.alfarm.pharmacy_version2.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfarm.pharmacy_version2.domain.useCase.OrderApiUseCase
import kotlinx.coroutines.launch

class OrderApiViewModel(private val orderApiUseCase: OrderApiUseCase): ViewModel() {
    // отправка заказа на сервер
    fun insert(context:Context, name:String, phone:String, description:String, priceOrder:String) = viewModelScope.launch {
        orderApiUseCase.insert(context, name, phone, description, priceOrder)

    }
}