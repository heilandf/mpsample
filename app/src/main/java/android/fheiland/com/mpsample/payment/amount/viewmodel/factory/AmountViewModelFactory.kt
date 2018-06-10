package android.fheiland.com.mpsample.payment.amount.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.amount.viewmodel.AmountViewModel

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class AmountViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AmountViewModel::class.java)) {
            return AmountViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}