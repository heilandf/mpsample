package android.fheiland.com.mpsample.payment.repository.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class PaymentMethodEntity {
    @SerializedName("id")
    var methodId: String? = null

    @SerializedName("name")
    var methodName: String? = null

    @SerializedName("payment_type_id")
    var paymentTypeId: String? = null

    @SerializedName("thumbnail")
    var thumbnail: String? = null
}