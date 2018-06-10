package android.fheiland.com.mpsample.payment.methods.view

import android.fheiland.com.mpsample.ContainerActivity
import android.os.Bundle

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodsActivity : ContainerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startFragmentTransaction(MethodsFragment())
    }
}