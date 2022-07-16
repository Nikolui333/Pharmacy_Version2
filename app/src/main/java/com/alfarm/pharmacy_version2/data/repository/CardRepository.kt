package com.alfarm.pharmacy_version2.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alfarm.pharmacy_version2.data.localDB.CardDao
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.domain.repository.CardCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardRepository (private val dao: CardDao): CardCall {

    override suspend fun insert(cardModel: CardModel) {
       dao.insert(cardModel)    }

    // обновление информации о товаре, после изменения его количества
    override suspend fun updateProductToCard(cardModel: CardModel){
        dao.updateProductToCard(cardModel)
    }

    override fun loadMedicineFromCard(): LiveData<List<CardModel>> {
        return dao.loadMedicineFromCard()    }

    override fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return dao.loadMedicineToCardFromCardProduct(idProduct)    }

    // удаление товара из корзины
    override suspend fun deleteProductFromCard(idProduct:Int) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductFromCard(idProduct)}
    }

    //  удаление товара из карточки товара
    override suspend fun deleteProductToCardFromCardProduct(idProduct:String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.deleteProductToCardFromCardProduct(idProduct)}
    }

    override suspend fun clear() {
        dao.clear()    }
}
