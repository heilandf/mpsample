@file:Suppress("UNCHECKED_CAST")

package android.fheiland.com.mpsample.main

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.repository.StorageRepository

/**
 * Created by Federico Heiland - Quadion Technologies
 * 16/06/2018
 */
class MainViewModelFactory(val storageRepository: StorageRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(storageRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}