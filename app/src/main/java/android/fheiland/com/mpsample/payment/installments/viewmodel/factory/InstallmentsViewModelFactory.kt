@file:Suppress("UNCHECKED_CAST")

package android.fheiland.com.mpsample.payment.installments.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.installments.interactor.InstallmentsUseCase
import android.fheiland.com.mpsample.payment.installments.interactor.OutputStorageUseCase
import android.fheiland.com.mpsample.payment.installments.viewmodel.InstallmentsViewModel

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentsViewModelFactory(var useCase: InstallmentsUseCase, var storageUseCase: OutputStorageUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(InstallmentsViewModel::class.java)) {
            return InstallmentsViewModel(useCase, storageUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}