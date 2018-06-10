@file:Suppress("UNCHECKED_CAST")

package android.fheiland.com.mpsample.payment.methods.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.methods.viewmodel.MethodViewModel
import android.fheiland.com.mpsample.payment.repository.PaymentRepository

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodViewModelFactory(var repository: PaymentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MethodViewModel::class.java)) {
            return MethodViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}