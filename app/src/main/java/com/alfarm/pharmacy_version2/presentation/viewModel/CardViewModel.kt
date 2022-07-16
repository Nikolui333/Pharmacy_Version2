package com.alfarm.pharmacy_version2.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.domain.useCase.CardUseCase
import kotlinx.coroutines.launch

class CardViewModel (private val cardUseCase: CardUseCase): ViewModel() {

    fun startInsert(nameProduct:String, imageProduct:String, priceProduct:String, idProduct:String, countProduct:String) {
        insert(
            CardModel(0, nameProduct, imageProduct, priceProduct, idProduct, countProduct, (priceProduct.toInt()*countProduct.toInt()).toString())
        )
    }
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // метод insert отвечает за добавление нового вида товара в корзину
    private fun insert(cardModel: CardModel) = viewModelScope.launch{
        cardUseCase.insert(cardModel)
    }
    // launch позволяет запускать методы в параллельных потоках
    // метод updateProductToCard меняет колличество конкретного вида товара в корзине
    fun updateProductToCard(cardModel: CardModel) = viewModelScope.launch{
        cardUseCase.updateProductToCard(cardModel)
    }

    // так как в методе loadMedicineFromCard() класса CardUseCase нет принимаемых значений, вместо создания метода,
    // возвращающего метод loadMedicineFromCard(), можно присвоить его переменной
    val loadMedicineFromCard = cardUseCase.loadMedicineFromCard()

    // LiveData хранит данные, которые можно получать каждый раз, когда что-то меняется
    // метод loadMedicineToCardFromCardProduct проверяет, есть ли товар в корзине
    fun loadMedicineToCardFromCardProduct(idProduct:String): LiveData<List<CardModel>> {
        return cardUseCase.loadMedicineToCardFromCardProduct(idProduct)
    }
    // методо deleteProductFromCard удаляет выбранный вид лекарства из корзины во вкладке корзины
    fun deleteProductFromCard(idProduct:Int) = viewModelScope.launch{

        cardUseCase.deleteProductFromCard(idProduct)
    }
    // метод deleteProductToCardFromCardProduct удаляет выбранный вид лекарства из корзины на вкладке со списком препаратов
    fun deleteProductToCardFromCardProduct(idProduct:String) = viewModelScope.launch{

        cardUseCase.deleteProductToCardFromCardProduct(idProduct)
    }
    // метод clearCard удаляет все товары из корзины
    fun clearCard() = viewModelScope.launch{

        cardUseCase.clear()
    }

}