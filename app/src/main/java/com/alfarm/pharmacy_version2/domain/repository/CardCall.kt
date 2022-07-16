package com.alfarm.pharmacy_version2.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.models.CardModel

interface CardCall {

    // suspend приостанавливает поток после выплнения метода
    suspend fun insert(cardModel: CardModel)

    // обновление информации о товаре, после изменения его количества
    suspend fun updateProductToCard(cardModel: CardModel)

    fun loadMedicineFromCard(): LiveData<List<CardModel>>

    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>>

    // удаление товара из корзины
    suspend fun deleteProductFromCard(idProduct:Int)

    //  удаление товара из карточки товара
    suspend fun deleteProductToCardFromCardProduct(idProduct:String)

    suspend fun clear()

}