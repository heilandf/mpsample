package android.fheiland.com.mpsample.payment.repository

import android.fheiland.com.mpsample.payment.repository.data.entity.CardIssuerEntity
import android.fheiland.com.mpsample.payment.repository.data.entity.InstallmentEntities
import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity
import io.reactivex.Observable
import retrofit2.Retrofit
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
open class PaymentRepository
@Inject
constructor(private var  retrofit: Retrofit) : PaymentMethodsApi {


    // With lazy modifier, the value gets computed only upon first access
    private val serviceApi by lazy { retrofit.create(PaymentMethodsApi::class.java) }

    override fun getPaymentMethods(publicKey: String): Observable<List<PaymentMethodEntity>> {
      return serviceApi.getPaymentMethods(publicKey)
    }

    override fun getCardIssuersByMethod(publicKey: String, methodId: String): Observable<List<CardIssuerEntity>> {
        return serviceApi.getCardIssuersByMethod(publicKey, methodId)
    }

    override fun getInstallmentsByCreditCard(publicKey: String, amount: String, methodId: String, issuerId: String): Observable<List<InstallmentEntities>> {
        return serviceApi.getInstallmentsByCreditCard(publicKey, amount, methodId, issuerId)
    }
}

