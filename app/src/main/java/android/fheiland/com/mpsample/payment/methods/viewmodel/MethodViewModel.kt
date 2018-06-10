package android.fheiland.com.mpsample.payment.methods.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.BuildConfig
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import android.fheiland.com.mpsample.payment.repository.PaymentRepository
import android.fheiland.com.mpsample.payment.repository.data.entity.PaymentMethodEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodViewModel(private var repository: PaymentRepository) : ViewModel() {

    var paymentMethodsList: MutableLiveData<MutableList<PaymentMethod>> = MutableLiveData()
    init {
        repository.getPaymentMethods(BuildConfig.PUBLIC_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { it.map { toDomain(it) } }
                .subscribe({ paymentMethodsList.postValue(it.toMutableList()) })
    }

    private fun toDomain(entity: PaymentMethodEntity?) : PaymentMethod {
        var method = PaymentMethod()
        method.methodName = entity?.methodName.orEmpty()
        method.imageUrl = entity?.thumbnail.orEmpty()
        method.methodId = entity?.methodId
        method.paymentTypeId = entity?.paymentTypeId

        return method
    }


}