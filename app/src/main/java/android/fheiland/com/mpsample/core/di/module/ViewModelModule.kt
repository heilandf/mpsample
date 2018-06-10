package android.fheiland.com.mpsample.core.di.module

import android.fheiland.com.mpsample.payment.amount.viewmodel.factory.AmountViewModelFactory
import android.fheiland.com.mpsample.payment.methods.viewmodel.factory.MethodViewModelFactory
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
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
    fun providesMethodsViewModelFactory(repository: PaymentRepository): MethodViewModelFactory {
        return MethodViewModelFactory(repository)
    }

}