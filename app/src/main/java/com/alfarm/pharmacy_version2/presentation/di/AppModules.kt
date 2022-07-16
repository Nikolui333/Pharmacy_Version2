package com.alfarm.pharmacy_version2.presentation.di

import androidx.room.Room
import com.alfarm.pharmacy_version2.data.dataSource.MedicationsApiDataSource
import com.alfarm.pharmacy_version2.data.dataSource.MedicationsDataSource
import com.alfarm.pharmacy_version2.data.dataSourceIMPL.MedicationsApiDataSourceIMPL
import com.alfarm.pharmacy_version2.data.dataSourceIMPL.MedicationsDataSourceIMPL
import com.alfarm.pharmacy_version2.data.localDB.DataBasePharmacy
import com.alfarm.pharmacy_version2.data.repository.CardRepository
import com.alfarm.pharmacy_version2.data.repository.MedicationsRepository
import com.alfarm.pharmacy_version2.data.repository.OrderApiRepository
import com.alfarm.pharmacy_version2.domain.repository.CardCall
import com.alfarm.pharmacy_version2.domain.repository.MedicationsCall
import com.alfarm.pharmacy_version2.domain.repository.OrderApiCall
import com.alfarm.pharmacy_version2.domain.useCase.CardUseCase
import com.alfarm.pharmacy_version2.domain.useCase.MedicationsUseCase
import com.alfarm.pharmacy_version2.domain.useCase.OrderApiUseCase
import com.alfarm.pharmacy_version2.presentation.viewModel.CardViewModel
import com.alfarm.pharmacy_version2.presentation.viewModel.MedicationsViewModel
import com.alfarm.pharmacy_version2.presentation.viewModel.OrderApiViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val medicines = module{

    single {
        Room.databaseBuilder(androidContext(), DataBasePharmacy::class.java,
            "dbC").build()
    }

    single { get<DataBasePharmacy>().medicationsDao }


    single<MedicationsDataSource> {
        MedicationsDataSourceIMPL(
            get()
        )
    }

    single<MedicationsApiDataSource> {
        MedicationsApiDataSourceIMPL(
            get()
        )
    }

    single<MedicationsCall> { MedicationsRepository(get(),get()) }

    single { MedicationsUseCase(get()) }

    viewModel { MedicationsViewModel(get()) }

}

val card = module{

    single {
        Room.databaseBuilder(androidContext(), DataBasePharmacy::class.java,
            "dbO").build()
    }

    single { get<DataBasePharmacy>().cardDao }


    single<CardCall> { CardRepository(get()) }

    single { CardUseCase(get()) }

    viewModel { CardViewModel(get()) }

}


val orderApi= module{

    single<OrderApiCall> { OrderApiRepository() }

    single { OrderApiUseCase(get()) }

    viewModel { OrderApiViewModel(get()) }

}

