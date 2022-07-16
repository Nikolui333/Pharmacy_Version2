package com.alfarm.pharmacy_version2.presentation.Tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.alfarm.pharmacy_version2.R
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.databinding.CheckoutBinding
import com.alfarm.pharmacy_version2.presentation.viewModel.CardViewModel
import com.alfarm.pharmacy_version2.presentation.viewModel.OrderApiViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class Checkout : BottomSheetDialogFragment() {

    private var binding: CheckoutBinding? = null
    private val cardViewModel: CardViewModel by viewModel()
    // viewModel, который отвечает за отправку данных на сервер
    private val orderApiViewModel: OrderApiViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.checkout, container, false)
        binding?.submitCheckout?.setOnClickListener(View.OnClickListener {

            cardViewModel.loadMedicineFromCard.observe(viewLifecycleOwner, Observer {
                // сумма за весь заказ
                val totalOrder:Int = it.sumOf<CardModel> { it.totalPrice.toInt() }
                // заполнение данных для истории покупок
                // joinToString нужно чтобы выводить массив без квадратных скобок
                val descriptionOrder = it.map { it.name + ": количество - " + it.count + ", цена - " + it.totalPrice + " р; " }.joinToString("")

/*                // запись данных в локальную базу данных
                orderLocalViewModel.startInsert(binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(), descriptionOrder,
                    totalOrder.toString() )*/

                // отправка данных на сервер
                orderApiViewModel.insert((context as FragmentActivity), binding?.enterNameCheckout?.text.toString(),
                    binding?.enterPhoneCheckout?.text.toString(), descriptionOrder,
                    totalOrder.toString())

                // очистка текстовых полей
                binding?.enterNameCheckout?.setText("")
                binding?.enterPhoneCheckout?.setText("")

                // закрытие всплывающей панели
                dismiss()
                // очистка корзины
                cardViewModel.clearCard()

            })
        })
        return binding?.root
    }

}