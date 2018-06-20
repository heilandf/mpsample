package android.fheiland.com.mpsample.payment.issuers.model

import java.io.Serializable

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuer : Serializable {
    var issuerId: String? = null
    var issuerName: String? = null
    var issuerImageUrl: String? = null
}