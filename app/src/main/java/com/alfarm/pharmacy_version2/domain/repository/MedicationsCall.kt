package com.alfarm.pharmacy_version2.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import kotlinx.coroutines.flow.Flow

interface MedicationsCall {
    fun loadMedicines(): LiveData<List<MedicationsModel>>
    suspend fun startMigration(context: Context)
    fun searchDatabase(searchQuery: String): Flow<List<MedicationsModel>>
}