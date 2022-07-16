package com.alfarm.pharmacy_version2.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alfarm.pharmacy_version2.data.models.MedicationsModel

@Dao
interface MedicationsDao {

    // записывает данные в локальную базу данных с сервера
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(medicationsModel: MedicationsModel)

    @Query("SELECT * FROM pharmacy_data_table")
    fun loadMedicines(): LiveData<List<MedicationsModel>>

    @Query("DELETE FROM pharmacy_data_table")
    suspend fun clear()
}