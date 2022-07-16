package com.alfarm.pharmacy_version2.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfarm.pharmacy_version2.data.models.MedicationsModel

@Database(entities = [MedicationsModel::class], version = 1)
abstract class MedicationsDB : RoomDatabase() {
    abstract val medicationsDao: MedicationsDao
}