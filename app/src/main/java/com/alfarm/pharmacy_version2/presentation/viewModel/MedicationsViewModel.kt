package com.alfarm.pharmacy_version2.presentation.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfarm.pharmacy_version2.domain.useCase.MedicationsUseCase
import kotlinx.coroutines.launch

class MedicationsViewModel (private val medicationsUseCase: MedicationsUseCase): ViewModel() {

    val loadMedicines = medicationsUseCase.loadMedicines()
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // проще говоря, если этот метод не используется, viewModelScope не загружает им память
    fun migration(context: Context) = viewModelScope.launch {
        medicationsUseCase.startMigration(context)
    }

}