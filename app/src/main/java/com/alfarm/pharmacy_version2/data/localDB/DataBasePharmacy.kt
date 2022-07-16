package com.alfarm.pharmacy_version2.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alfarm.pharmacy_version2.data.models.CardModel
import com.alfarm.pharmacy_version2.data.models.MedicationsModel

// В параметрах аннотации Database указываем, какие Entity будут использоваться, и версию базы.
// Для каждого Entity класса из списка entities будет создана таблица.
// Entity класс используется для создания таблицы

@Database(entities = [CardModel::class, MedicationsModel::class], version = 1)
abstract class DataBasePharmacy : RoomDatabase() {
    abstract val cardDao: CardDao
    abstract val medicationsDao: MedicationsDao
}







