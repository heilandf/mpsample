package android.fheiland.com.mpsample.core.di.module

import android.fheiland.com.mpsample.payment.amount.view.AmountFragment
import android.fheiland.com.mpsample.payment.methods.view.MethodsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
@Module
abstract class FragmentsBuilderProvider {
    @ContributesAndroidInjector(modules = [ViewModelModule::class])
    abstract fun contributeAmountFragment() : AmountFragment

    @ContributesAndroidInjector(modules = [(ViewModelModule::class)])
    abstract fun contributeMethodsFragment(): MethodsFragment
}