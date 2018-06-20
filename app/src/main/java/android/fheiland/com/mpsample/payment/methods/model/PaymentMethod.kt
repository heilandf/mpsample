package android.fheiland.com.mpsample.payment.methods.model

import java.io.Serializable

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
open class PaymentMethod: Serializable {
    var methodId: String? = null
    var methodName: String? = null
    var paymentTypeId: String? = null
    var imageUrl: String? = null
}