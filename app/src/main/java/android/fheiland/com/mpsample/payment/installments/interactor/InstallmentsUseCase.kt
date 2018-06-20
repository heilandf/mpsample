package android.fheiland.com.mpsample.payment.installments.interactor

import android.fheiland.com.mpsample.base.BaseUseCase
import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import android.fheiland.com.mpsample.payment.repository.data.entity.InstallmentEntities
import dagger.internal.Preconditions
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
open class InstallmentsUseCase
@Inject
constructor(private val paymentRepository: PaymentRepository) : BaseUseCase<List<InstallmentEntities>, InstallmentsUseCase.Params?>() {
    override fun buildUseCaseObservable(params: Params?): Observable<List<InstallmentEntities>> {
        Preconditions.checkNotNull(params)
        return paymentRepository.getInstallmentsByCreditCard(
                BuildConfig.PUBLIC_KEY,
                params?.amount.orEmpty(),
                params?.methodId.orEmpty(),
                params?.issuer.orEmpty())
    }

    class Params constructor(val amount: String, val methodId: String, val issuer: String)
}