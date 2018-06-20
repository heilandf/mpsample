package android.fheiland.com.mpsample.payment.issuers.view

import android.fheiland.com.mpsample.base.ContainerActivity
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.os.Bundle

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerActivity : ContainerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Card Issuers"
        val parentOutput = intent.getSerializableExtra("output")
        if (savedInstanceState == null)
            startFragmentTransaction(CardIssuerFragment.newInstance(parentOutput as OutputEntity))
    }
}