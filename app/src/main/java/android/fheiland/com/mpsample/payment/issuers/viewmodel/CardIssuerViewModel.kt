package android.fheiland.com.mpsample.payment.issuers.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.base.DefaultObserver
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
import android.fheiland.com.mpsample.payment.issuers.model.CardIssuer
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import android.fheiland.com.mpsample.payment.repository.data.entity.CardIssuerEntity

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerViewModel(private var useCase: CardIssuerListUseCase) : ViewModel() {

    var selectedPaymentMethod: PaymentMethod? = null
    var selectedCardIssuer = MutableLiveData<CardIssuer>()
    var cardIssuerList = MutableLiveData<List<CardIssuer>>()
    var loadingError = MutableLiveData<Boolean>()


    fun fetchIssuersList(method: PaymentMethod?) {
        selectedPaymentMethod = method
        useCase.execute(CardIssuerListObserver(), CardIssuerListUseCase.Params.forMethod(selectedPaymentMethod?.methodId.orEmpty()))
    }

    /**
     * Map data entity to domain model
     * Data entity brings lot of data that, in what business logic respect, we does not need.
     * We take the necessary data and return the model
     */
    private fun toDomain(entity: CardIssuerEntity?) : CardIssuer {
        val issuer = CardIssuer()
        issuer.issuerId = entity?.entityId
        issuer.issuerName = entity?.entityName
        issuer.issuerImageUrl = entity?.thumbnail
        return issuer
    }

    fun disposeObservables() = useCase.dispose()

    inner class CardIssuerListObserver : DefaultObserver<List<CardIssuerEntity>>() {
        override fun onNext(t: List<CardIssuerEntity>) = cardIssuerList.postValue(t.map { toDomain(it) })
        override fun onError(exception: Throwable) = loadingError.postValue(true)
    }

}