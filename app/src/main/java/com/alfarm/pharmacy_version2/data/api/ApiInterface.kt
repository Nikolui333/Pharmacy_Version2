package com.alfarm.pharmacy_version2.data.api

import com.alfarm.pharmacy_version2.data.models.MedicationsApiModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiInterface {

    @GET("loadMedicines.php")
    fun loadMedicinesApi(): Call <ArrayList<MedicationsApiModel>>

    @FormUrlEncoded
    @POST("insert.php")
    fun insert(
        @Field("name") name: String?,
        @Field("phone") phone: String?,
        @Field("description") description: String?,
        @Field("priceOrder") priceOrder: String?,
    ): Call<ResponseBody?>?
}