package android.fheiland.com.mpsample.core.di.module

import android.fheiland.com.mpsample.main.MainViewModelFactory
import android.fheiland.com.mpsample.payment.amount.viewmodel.factory.AmountViewModelFactory
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.installments.viewmodel.factory.InstallmentsViewModelFactory
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
import android.fheiland.com.mpsample.payment.issuers.viewmodel.factory.CardIssuerViewModelFactory
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.methods.viewmodel.factory.MethodViewModelFactory
import android.fheiland.com.mpsample.payment.repository.StorageRepository
import dagger.Module
import dagger.Provides

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
@Module
class ViewModelModule {

    @Provides
    fun providesAmountViewModelFactory(): AmountViewModelFactory {
        return AmountViewModelFactory()
    }

    @Provides
    fun providesMethodsViewModelFactory(useCase: PaymentMethodListUseCase): MethodViewModelFactory {
        return MethodViewModelFactory(useCase)
    }

    @Provides
    fun provideCardIssuerViewModelFactory(useCase: CardIssuerListUseCase) : CardIssuerViewModelFactory {
        return CardIssuerViewModelFactory(useCase)
    }

    @Provides
    fun provideInstallmentsViewModelFactory(useCase: InstallmentsUseCase, storageUseCase: OutputStorageUseCase) : InstallmentsViewModelFactory {
        return InstallmentsViewModelFactory(useCase, storageUseCase)
    }

    @Provides
    fun provideMainViewModelFactory(storageApi: StorageRepository) : MainViewModelFactory {
        return MainViewModelFactory(storageApi)
    }

}