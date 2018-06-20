package android.fheiland.com.mpsample.payment.amount.view

import android.fheiland.com.mpsample.base.ContainerActivity
import android.os.Bundle

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class AmountActivity : ContainerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null)
            startFragmentTransaction(AmountFragment())
    }
}