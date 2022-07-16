package com.alfarm.pharmacy_version2.data.dataSource

import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.models.MedicationsModel

interface MedicationsDataSource {
    fun insert(medicationsModel: MedicationsModel)
    fun loadMedicines(): LiveData<List<MedicationsModel>>
    suspend fun clear()
}