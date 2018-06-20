package android.fheiland.com.mpsample.payment.output

import android.fheiland.com.mpsample.payment.issuers.model.CardIssuer
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import java.io.Serializable

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
open class OutputEntity(var amount: String = "") : Serializable {

    var paymentMethod: PaymentMethod? = null
    var cardIssuer: CardIssuer? = null
    var selectedInstallment: String? = null
}