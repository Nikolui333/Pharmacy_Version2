package com.alfarm.pharmacy_version2.data.dataSource

import android.content.Context

interface MedicationsApiDataSource {
    fun startMigration (context: Context)
}