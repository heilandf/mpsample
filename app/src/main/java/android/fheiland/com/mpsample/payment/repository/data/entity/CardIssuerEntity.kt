package android.fheiland.com.mpsample.payment.repository.data.entity

import com.google.gson.annotations.SerializedName

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerEntity {
    @SerializedName("id")
    var entityId: String? = null
    @SerializedName("name")
    var entityName: String? = null
    @SerializedName("thumbnail")
    var thumbnail: String? = null
}