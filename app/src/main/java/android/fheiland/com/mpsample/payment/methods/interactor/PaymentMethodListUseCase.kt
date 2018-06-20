package android.fheiland.com.mpsample.payment.methods.interactor

import android.fheiland.com.mpsample.base.BaseUseCase
import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
open class PaymentMethodListUseCase @Inject
constructor(private val paymentRepository: PaymentRepository) : BaseUseCase<List<PaymentMethodEntity>, Void?>() {
    override fun buildUseCaseObservable(params: Void?): Observable<List<PaymentMethodEntity>> {
        return paymentRepository.getPaymentMethods(BuildConfig.PUBLIC_KEY)
    }
}