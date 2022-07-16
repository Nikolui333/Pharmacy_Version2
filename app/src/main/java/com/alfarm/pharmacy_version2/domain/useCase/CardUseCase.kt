package com.alfarm.pharmacy_version2.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardUseCase (private val cardCall: CardCall) {
    // добавление уникального товара в корзину
    suspend fun insert(cardModel: CardModel) {
        cardCall.insert(cardModel)    }
    // увеличение (или уменьшение) количества пачек одного из препоратов
    suspend fun updateProductToCard(cardModel: CardModel) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.updateProductToCard(cardModel)}
    }
    // отправка данный (заказа) на сервер
    fun loadMedicineFromCard(): LiveData<List<CardModel>> {
        return cardCall.loadMedicineFromCard()    }

    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return cardCall.loadMedicineToCardFromCardProduct(idProduct)    }

    suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductFromCard(idProduct)}
    }

    suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            cardCall.deleteProductToCardFromCardProduct(idProduct)}
    }
    // очистка корзины
    suspend fun clear() {
        cardCall.clear()    }
}