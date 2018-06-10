package android.fheiland.com.mpsample.payment.repository

import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
internal interface PaymentMethodsApi {
    @GET("/v1/payment_methods")
    fun getPaymentMethods(@Query("public_key") publicKey: String) : Observable<List<PaymentMethodEntity>>
}