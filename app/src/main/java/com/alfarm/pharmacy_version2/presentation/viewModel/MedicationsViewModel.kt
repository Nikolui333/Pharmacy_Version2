package com.alfarm.pharmacy_version2.presentation.viewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import com.alfarm.pharmacy_version2.domain.useCase.MedicationsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MedicationsViewModel (private val medicationsUseCase: MedicationsUseCase): ViewModel() {

    val loadMedicines = medicationsUseCase.loadMedicines()
    // viewModelScope прекращает работу внутри ViewModel (в данном случае в методе insert) в случае, если пользователь покинул экран
    // проще говоря, если этот метод не используется, viewModelScope не загружает им память
    fun migration(context: Context) = viewModelScope.launch {
        medicationsUseCase.startMigration(context)
    }

    fun searchDatabase(searchQuery: String): LiveData<List<MedicationsModel>> {
        return medicationsUseCase.searchDatabase(searchQuery).asLiveData()
    }

}