package com.alfarm.pharmacy_version2.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.alfarm.pharmacy_version2.R
import com.alfarm.pharmacy_version2.databinding.ActivityMainBinding
import com.alfarm.pharmacy_version2.presentation.Tabs.Card.ShoppingCart
import com.alfarm.pharmacy_version2.presentation.Tabs.Medications.Medicine
import com.alfarm.pharmacy_version2.presentation.viewModel.MedicationsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding: ActivityMainBinding? = null

    // ViewModel - класс, позволяющий Activity и фрагментам сохранять необходимые им объекты живыми при повороте экрана.
    // by viewModels() - делегат, которые прячет за собой создание вьюмодели
    private val medicationsViewModel: MedicationsViewModel by viewModel() // by viewModel() способ работы с viewModel через koin (в di)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        // как только данные поменяются, запустится эта строка
        medicationsViewModel.migration(this)

        setSupportActionBar(binding?.topMainMenu)
        // запуск фрагмента Home (в данном случае он является фрагментом по умолчанию)
        supportFragmentManager.beginTransaction().replace(R.id.mainContent, Medicine()).commit()

        // обработчик нажатий по вкладнкам
        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->

            when(item.itemId) {
                R.id.medicalBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Medicine()).commit()
                R.id.shoppingCardBottomMainMenu -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, ShoppingCart()).commit()
            }

            return@setOnItemSelectedListener true

        }
        binding?.bottomMainMenu?.selectedItemId = R.id.medicalBottomMainMenu
    }
}