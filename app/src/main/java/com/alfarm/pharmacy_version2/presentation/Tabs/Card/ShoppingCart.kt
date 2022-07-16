package com.alfarm.pharmacy_version2.presentation.Tabs.Card

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfarm.pharmacy_version2.R
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.databinding.FragmentShoppingCartBinding
import com.alfarm.pharmacy_version2.presentation.viewModel.CardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.lifecycle.Observer
import com.alfarm.pharmacy_version2.presentation.Tabs.Checkout

class ShoppingCart : Fragment(),View.OnClickListener {

    private var binding: FragmentShoppingCartBinding? = null
    private var cardAdapter: CardAdapter? = null
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shopping_cart, container, false)
        initRecyclerCard()
        loadMedicineFromCard()

        // обработка нажатий по кнопке (очистка корзины)
        binding?.clearCard?.setOnClickListener(this)
        // обработка нажатия по кнопке (оформление заказа)
        binding?.checkoutCard?.setOnClickListener(this)

        return binding?.root
    }

    // инициализация
    private fun initRecyclerCard() {

        binding?.listCard?.layoutManager =
            LinearLayoutManager(context)
        cardAdapter =
            CardAdapter ({ cardModel: CardModel ->
                deleteFromCard(
                    cardModel
                )
            }, { cardModel: CardModel ->
                lessCount(
                    cardModel
                )
            }, { cardModel: CardModel ->
                moreCount(
                    cardModel
                )
            })
        binding?.listCard?.adapter = cardAdapter
    }

    // загрузка всех товаров из корзины
    private fun loadMedicineFromCard() {

        cardViewModel.loadMedicineFromCard.observe(viewLifecycleOwner, Observer {
            cardAdapter?.setList(it)
            cardAdapter?.notifyDataSetChanged()
            // сложение суммарных стоимостей каждого товара (sumOf - суммировать всё)
            val total:Int = it.sumOf<CardModel>
            // суммировать поля totalPrice
            { it.totalPrice.toInt() }

            binding?.totalOrder?.text = total.toString()

        })
    }

    private fun deleteFromCard(cardModel: CardModel){

        cardViewModel.deleteProductFromCard(cardModel.id)
    }

    // обработка кликов по кнопкам
    override fun onClick(view: View) {
        when(view.id) {
            // очистка корзины
            R.id.clearCard -> cardViewModel.clearCard()
            // отправка заказа
            R.id.checkoutCard -> {
                // запуск фрагмента (выезжающей панели) для ввода данных пользователя
                val checkout = Checkout()
                checkout.show((context as FragmentActivity).supportFragmentManager, "checkout")

            }
        }
    }

    // уменьшение колличества единиц товара
    private fun lessCount(cardModel:CardModel) {

        var count: Int = cardModel.count.toInt()
        count--
        if (count<1) { // если count<1 вывести 1
            cardViewModel.updateProductToCard(
                CardModel(cardModel.id, cardModel.name,
                    cardModel.image, cardModel.price, cardModel.idProduct, "1",
                    (cardModel.price.toInt()*1).toString())
            )

        }
        else {

            cardViewModel.updateProductToCard(
                CardModel(cardModel.id, cardModel.name,
                    cardModel.image, cardModel.price, cardModel.idProduct, count.toString(),
                    // получение итоговой стоимости
                    (cardModel.price.toInt()*count).toString())
            )

        }
    }
    // увеличение колличества единиц товара
    private fun moreCount(cardModel:CardModel) {

        // получаем колличество товара
        var count: Int = cardModel.count.toInt()
        count++

        cardViewModel.updateProductToCard(
            CardModel(cardModel.id, cardModel.name,
                cardModel.image, cardModel.price, cardModel.idProduct, count.toString(),
                (cardModel.price.toInt()*count).toString())
        )
    }
}