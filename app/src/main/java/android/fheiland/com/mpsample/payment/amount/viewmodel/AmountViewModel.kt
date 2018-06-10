package android.fheiland.com.mpsample.payment.amount.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.fheiland.com.mpsample.core.extensions.between

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class AmountViewModel : ViewModel() {

    val moveForwardButtonEnabled = MutableLiveData<Boolean>()
    val moveForward = MutableLiveData<Boolean>()
    private var userAmountInput: String? = null

    var midSizeText = MutableLiveData<Boolean>()
    var normalSizeText = MutableLiveData<Boolean>()
    var maxSizeText = MutableLiveData<Boolean>()

    init {
        moveForwardButtonEnabled.postValue(false)
    }

    fun moveForward() = moveForward.postValue(true)

    fun saveInputAmount(amount: String) {
        userAmountInput = amount
        moveForwardButtonEnabled.postValue(amount.isNotEmpty())
        postSizeValues(amount)
    }

    private fun postSizeValues(amount: String) {
        maxSizeText.postValue(amount.count().between(0, 8))
        midSizeText.postValue(amount.count().between(8, 10))
        normalSizeText.postValue(amount.count().between(10, 14))
    }

}