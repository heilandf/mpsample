package android.fheiland.com.mpsample.core.di.module

import android.content.Context
import android.fheiland.com.mpsample.base.ApplicationController
import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import android.fheiland.com.mpsample.payment.repository.StorageRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
@Module
class ApplicationModule {

    @Provides
    fun providesApplicationContext() : Context {
        return ApplicationController.appControllerInstance.applicationContext
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(createClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }

        return okHttpClientBuilder.build()
    }

    @Singleton
    @Provides
    fun providesPaymentRepository(retrofit: Retrofit) : PaymentRepository {
        return PaymentRepository(retrofit)
    }

    @Singleton
    @Provides
    fun provideStorageRepository() : StorageRepository {
        return StorageRepository()
    }

    @Provides
    fun provideCardIssuerListUseCase(paymentRepository: PaymentRepository) : CardIssuerListUseCase {
        return CardIssuerListUseCase(paymentRepository)
    }

    @Provides
    fun providePaymentMethodUseCase(paymentRepository: PaymentRepository) : PaymentMethodListUseCase {
        return PaymentMethodListUseCase(paymentRepository)
    }

    @Provides
    fun provideInstallmentUseCase(paymentRepository: PaymentRepository) : InstallmentsUseCase {
        return InstallmentsUseCase(paymentRepository)
    }

    @Provides
    fun provideOutputStorageUseCase(storageRepository: StorageRepository) : OutputStorageUseCase {
        return OutputStorageUseCase(storageRepository)
    }
}