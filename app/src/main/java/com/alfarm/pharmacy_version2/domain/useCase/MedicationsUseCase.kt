package com.alfarm.pharmacy_version2.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import com.alfarm.pharmacy_version2.domain.repository.MedicationsCall
import kotlinx.coroutines.flow.Flow

class MedicationsUseCase (private val medicationsCall: MedicationsCall) {

    // получение даннх с локальной базы данных
    fun loadMedicines(): LiveData<List<MedicationsModel>> {

        return medicationsCall.loadMedicines()

    }
    // запуск меграций
    suspend fun startMigration (context: Context) {

        medicationsCall.startMigration(context)

    }

    /*override*/ fun searchDatabase(searchQuery: String): Flow<List<MedicationsModel>> {
        return medicationsCall.searchDatabase(searchQuery)
    }
}