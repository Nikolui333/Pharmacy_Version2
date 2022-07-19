package com.alfarm.pharmacy_version2.presentation.Tabs.Medications

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.AppCompatImageButton
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.alfarm.pharmacy_version2.R
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import com.alfarm.pharmacy_version2.databinding.FragmentMedicineBinding
import com.alfarm.pharmacy_version2.presentation.viewModel.CardViewModel
import com.alfarm.pharmacy_version2.presentation.viewModel.MedicationsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Medicine : Fragment()/*, SearchView.OnQueryTextListener*/ {

    private var binding: FragmentMedicineBinding? = null
    private var medicationsAdapter: MedicationsAdapter? = null
    private val medicationsViewModel: MedicationsViewModel by viewModel()
    private val cardViewModel: CardViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_medicine, container, false)

        initRecyclerMedications()
        loadMedicine()

        return binding?.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        // TODO Auto-generated method stub
        //  super.onCreateOptionsMenu(menu!!, inflater)
        inflater.inflate(R.menu.main_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val id = item.itemId
        return if (id == R.id.menu_search) {
            //Do whatever you want to do
            true
        } else super.onOptionsItemSelected(item)

    }

    // инициализация адаптера
    private fun initRecyclerMedications() {
        // вертикальный макет recyclerView
        binding?.catalogMedications?.layoutManager =
            LinearLayoutManager(context)
        medicationsAdapter =
            MedicationsAdapter ({ medicationsModel: MedicationsModel ->
                addToCard(
                    medicationsModel
                )
            }, { medicationsModel: MedicationsModel ->
                removeFromCard(
                    medicationsModel
                )
                // отображение кнопок добавления или удаления товара
            }, { idProduct:Int, addToBasket: AppCompatImageButton,
                 removeFromBasket: AppCompatImageButton ->
                loadMedicineToCardFromCardProduct(
                    idProduct, addToBasket, removeFromBasket
                )
            })
        binding?.catalogMedications?.adapter = medicationsAdapter

    }

    private fun loadMedicine() {
        // получение всех необходимых данных для заполнения recyclerView
        medicationsViewModel.loadMedicines.observe(viewLifecycleOwner, Observer {
            // setList наполняет адаптер данными
            medicationsAdapter?.setList(it)
            // notifyDataSetChanged обновляет адаптер
            medicationsAdapter?.notifyDataSetChanged()
        })
    }

    // добавление товара в корзину
    private fun addToCard(medicationsModel: MedicationsModel) {
        cardViewModel.startInsert(medicationsModel.name,
            medicationsModel.image,
            medicationsModel.price,
            medicationsModel.id.toString(),
            "1")
    }

    // удаление товара из корзины
    private fun removeFromCard(medicationsModel: MedicationsModel) {
        cardViewModel.deleteProductToCardFromCardProduct(medicationsModel.id.toString())
    }

    // проверяем, есть ли товар в корзине и узнаём его колличество
    private fun loadMedicineToCardFromCardProduct (idProduct:Int, addToBasket: AppCompatImageButton,
                                                   removeFromBasket: AppCompatImageButton
    ){
        // передаём id, который приходит из адаптера
        cardViewModel.loadMedicineToCardFromCardProduct(idProduct.toString()).observe(viewLifecycleOwner, Observer {

            // в переменную count получаем колличество товара
            val count = it.count() // it - это неявное имя одного параметра в лямбда-функции

            // если колличество больше нуля, убрать кнопку добавления и отобразить кнопку удаления
            if (count>0) {
                addToBasket.visibility = View.GONE
                removeFromBasket.visibility = View.VISIBLE
            }
            else {
                addToBasket.visibility = View.VISIBLE
                removeFromBasket.visibility = View.GONE }
        })

    }
}