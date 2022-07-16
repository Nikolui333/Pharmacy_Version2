package com.alfarm.pharmacy_version2.presentation

import android.app.Application
import com.alfarm.pharmacy_version2.presentation.di.card
import com.alfarm.pharmacy_version2.presentation.di.medicines
import com.alfarm.pharmacy_version2.presentation.di.orderApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Koin Android logger
            // в androidLogger задаётся то, насколько подробно нужно выводить оишбки
            androidLogger(Level.ERROR)
            //inject Android context
            androidContext(this@App)

            modules(medicines, card, orderApi)

        }

    }
}