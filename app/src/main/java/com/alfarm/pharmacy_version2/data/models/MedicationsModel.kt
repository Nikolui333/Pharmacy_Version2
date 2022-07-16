package com.alfarm.pharmacy_version2.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pharmacy_data_table")
class MedicationsModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "pharmacy_id")
    val id:Int,

    @ColumnInfo(name = "pharmacy_name")
    val name:String,

    @ColumnInfo(name = "pharmacy_image")
    val image:String,

    @ColumnInfo(name = "pharmacy_description")
    val description:String,

    @ColumnInfo(name = "pharmacy_price")
    val price:String

)