package android.fheiland.com.mpsample.payment.issuers.interactor

import android.fheiland.com.mpsample.base.BaseUseCase
import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import android.fheiland.com.mpsample.payment.repository.data.entity.CardIssuerEntity
import dagger.internal.Preconditions
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 20/06/2018
 */
open class CardIssuerListUseCase
@Inject
constructor(private val paymentRepository: PaymentRepository) : BaseUseCase<List<CardIssuerEntity>, CardIssuerListUseCase.Params?>() {

    override fun buildUseCaseObservable(params: Params?): Observable<List<CardIssuerEntity>> {
        Preconditions.checkNotNull(params)
        return paymentRepository.getCardIssuersByMethod(BuildConfig.PUBLIC_KEY, params?.methodId.orEmpty())
    }


    class Params private constructor(val methodId: String) {
        companion object {
            fun forMethod(methodId: String): Params = Params(methodId)
        }
    }
}