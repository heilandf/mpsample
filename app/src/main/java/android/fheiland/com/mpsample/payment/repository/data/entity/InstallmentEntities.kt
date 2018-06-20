package android.fheiland.com.mpsample.payment.repository.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentEntities {
    @SerializedName("payment_method_id")
    var method: String? = null
    @SerializedName("payer_costs")
    var entities: List<PayerCost>? = null
}