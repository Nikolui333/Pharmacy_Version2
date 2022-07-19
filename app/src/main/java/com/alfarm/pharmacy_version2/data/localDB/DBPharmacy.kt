package com.alfarm.pharmacy_version2.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.data.models.MedicationsModel

@Database(entities = [CardModel::class, MedicationsModel::class], version = 1)
abstract class DBPharmacy : RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val medicationsDao: MedicationsDao

}