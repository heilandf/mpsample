package android.fheiland.com.mpsample.payment.installments.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.base.DefaultObserver
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.installments.model.Installment
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.fheiland.com.mpsample.payment.repository.data.entity.InstallmentEntities

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentsViewModel(var useCase: InstallmentsUseCase, var storageUseCase: OutputStorageUseCase) : ViewModel() {

    var installmentData = MutableLiveData<List<Installment>>()
    val selectedInstallment = MutableLiveData<String>()
    val outputStoredSuccessfully = MutableLiveData<Boolean>()
    val observableError = MutableLiveData<Boolean>()

    fun fetchInstallmentsByCreditCard(output: OutputEntity) {
        useCase.execute(InstallmentsObserver(), InstallmentsUseCase.Params(
                output.amount,
                output.paymentMethod?.methodId.orEmpty(),
                output.cardIssuer?.issuerId.orEmpty()))
    }

    private fun toDomain(entity: InstallmentEntities): Installment {
        val messages = arrayListOf<String>()
        entity.entities?.forEach({ messages.add(it.recommendedMessage.orEmpty()) })
        return Installment(messages)
    }

    fun storeOutputAndExit(output: OutputEntity?) {
        output?.let { storageUseCase.execute(OutputStorageObserver(), OutputStorageUseCase.Params.withOutput(output)) }
    }

    fun disposeObservables() {
        useCase.dispose()
        storageUseCase.dispose()
    }

    inner class InstallmentsObserver : DefaultObserver<List<InstallmentEntities>>() {
        override fun onNext(t: List<InstallmentEntities>) = installmentData.postValue(t.map { toDomain(it) })
        override fun onError(exception: Throwable) = observableError.postValue(true)
    }

    inner class OutputStorageObserver : DefaultObserver<Boolean>() {
        override fun onNext(t: Boolean) = outputStoredSuccessfully.postValue(t)
        override fun onError(exception: Throwable) = observableError.postValue(true)
    }

}