@file:Suppress("UNCHECKED_CAST")

package android.fheiland.com.mpsample.payment.methods.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.methods.interactor.PaymentMethodListUseCase
import android.fheiland.com.mpsample.payment.methods.viewmodel.MethodViewModel

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodViewModelFactory(var useCase: PaymentMethodListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MethodViewModel::class.java)) {
            return MethodViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}