@file:Suppress("UNCHECKED_CAST")

package android.fheiland.com.mpsample.payment.issuers.viewmodel.factory

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.fheiland.com.mpsample.payment.issuers.interactor.CardIssuerListUseCase
import android.fheiland.com.mpsample.payment.issuers.viewmodel.CardIssuerViewModel

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerViewModelFactory(private var useCase: CardIssuerListUseCase) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardIssuerViewModel::class.java)) {
            return CardIssuerViewModel(useCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}