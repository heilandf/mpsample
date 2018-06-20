package android.fheiland.com.mpsample.payment.installments.view

import android.fheiland.com.mpsample.base.ContainerActivity
import android.os.Bundle

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentsActivity : ContainerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "Installments"
        if (savedInstanceState == null)
            startFragmentTransaction(InstallmentsFragment())
    }
}