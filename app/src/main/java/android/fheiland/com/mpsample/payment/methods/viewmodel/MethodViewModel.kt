package android.fheiland.com.mpsample.payment.methods.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.base.DefaultObserver
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodViewModel(private var useCase: PaymentMethodListUseCase) : ViewModel() {

    var selectedPaymentMethod = MutableLiveData<PaymentMethod>()
    var loadingError = MutableLiveData<Boolean>()

    var paymentMethodsList: MutableLiveData<MutableList<PaymentMethod>> = MutableLiveData()
    fun fetchMethodPaymentList() { useCase.execute(MethodListObserver(), null) }

    /**
     * Map data entity to domain model
     * Data entity brings lot of data that, in what business logic respect, we does not need.
     * We take the necessary data and return the model
     */
    private fun toDomain(entity: PaymentMethodEntity?) : PaymentMethod {
        val method = PaymentMethod()
        method.methodName = entity?.methodName.orEmpty()
        method.imageUrl = entity?.thumbnail.orEmpty()
        method.methodId = entity?.methodId
        method.paymentTypeId = entity?.paymentTypeId

        return method
    }

    fun disposeObservables() = useCase.dispose()
    fun selectedMethod(method: PaymentMethod) = selectedPaymentMethod.postValue(method)

    inner class MethodListObserver : DefaultObserver<List<PaymentMethodEntity>>() {
        override fun onNext(t: List<PaymentMethodEntity>) = paymentMethodsList.postValue((t.map { toDomain(it) }).toMutableList())
        override fun onError(exception: Throwable) = loadingError.postValue(true)
    }
}