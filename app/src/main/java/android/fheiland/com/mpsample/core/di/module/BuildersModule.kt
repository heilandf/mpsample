package android.fheiland.com.mpsample.core.di.module

import android.fheiland.com.mpsample.main.MainActivity
import android.fheiland.com.mpsample.payment.amount.view.AmountActivity
import android.fheiland.com.mpsample.payment.installments.view.InstallmentsActivity
import android.fheiland.com.mpsample.payment.issuers.view.CardIssuerActivity
import android.fheiland.com.mpsample.payment.methods.view.MethodsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [(ViewModelModule::class), (FragmentsBuilderProvider::class)])
    abstract fun contributeAmountActivity(): AmountActivity

    @ContributesAndroidInjector(modules = [(ViewModelModule::class), (FragmentsBuilderProvider::class)])
    abstract fun contributeMethodsActivity(): MethodsActivity

    @ContributesAndroidInjector(modules = [(ViewModelModule::class), (FragmentsBuilderProvider::class)])
    abstract fun contributeCardIssuerActivity(): CardIssuerActivity

    @ContributesAndroidInjector(modules = [(ViewModelModule::class), (FragmentsBuilderProvider::class)])
    abstract fun contributeInstallmentsActivity(): InstallmentsActivity

    @ContributesAndroidInjector(modules = [(ViewModelModule::class)])
    abstract fun contributeMainActivity(): MainActivity



}