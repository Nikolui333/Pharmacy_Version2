package com.alfarm.pharmacy_version2.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.dataSource.MedicationsDataSource
import com.alfarm.pharmacy_version2.data.localDB.MedicationsDao
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class MedicationsDataSourceIMPL (private val dao: MedicationsDao):
    MedicationsDataSource {

    override fun insert(medicationsModel: MedicationsModel) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(medicationsModel)}
    }

    override fun loadMedicines(): LiveData<List<MedicationsModel>> {
        return dao.loadMedicines()
    }

    override suspend fun clear() {
        CoroutineScope(Dispatchers.IO).launch {
            dao.clear()}
    }

    override fun searchDatabase(searchQuery: String): Flow<List<MedicationsModel>> {
        return dao.searchDatabase(searchQuery)
    }
}