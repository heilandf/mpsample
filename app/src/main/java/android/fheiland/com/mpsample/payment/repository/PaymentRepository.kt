package android.fheiland.com.mpsample.payment.repository

import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.http.Query
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class PaymentRepository
@Inject
constructor(var  retrofit: Retrofit) : PaymentMethodsApi {
    // With lazy modifier, the value gets computed only upon first access
    private val serviceApi by lazy { retrofit.create(PaymentMethodsApi::class.java) }
    override fun getPaymentMethods(@Query("public_key") publicKey: String): Observable<List<PaymentMethodEntity>> = serviceApi.getPaymentMethods(publicKey)
}

