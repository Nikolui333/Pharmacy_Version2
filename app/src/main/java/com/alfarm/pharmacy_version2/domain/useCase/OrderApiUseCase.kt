package com.alfarm.pharmacy_version2.domain.useCase

import android.content.Context
import com.alfarm.pharmacy_version2.domain.repository.OrderApiCall

class OrderApiUseCase(private var orderApiCall: OrderApiCall) {

    fun insert (context:Context, name:String, phone:String, description:String, priceOrder:String) {

       orderApiCall.insert(context, name, phone, description, priceOrder)

    }

}