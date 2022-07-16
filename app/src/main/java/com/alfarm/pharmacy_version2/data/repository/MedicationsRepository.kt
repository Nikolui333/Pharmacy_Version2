package com.alfarm.pharmacy_version2.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.alfarm.pharmacy_version2.data.dataSource.MedicationsApiDataSource
import com.alfarm.pharmacy_version2.data.dataSource.MedicationsDataSource
import com.alfarm.pharmacy_version2.data.models.MedicationsModel
import com.alfarm.pharmacy_version2.domain.repository.MedicationsCall

class MedicationsRepository (private val medicationsApiDataSource: MedicationsApiDataSource,
                             private val medicationsDataSource: MedicationsDataSource
): MedicationsCall {

    // загрузка данных из локальной базы данных
    override fun loadMedicines(): LiveData<List<MedicationsModel>> {
        return medicationsDataSource.loadMedicines()    }

    // метод меграций
    override suspend fun startMigration(context: Context) {
       // medicationsDataSource.clear()
        medicationsApiDataSource.startMigration(context)
    }
}