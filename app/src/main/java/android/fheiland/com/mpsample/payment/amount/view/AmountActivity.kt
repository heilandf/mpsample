package android.fheiland.com.mpsample.payment.amount.view

import android.fheiland.com.mpsample.ContainerActivity
import android.fheiland.com.mpsample.R
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class AmountActivity : ContainerActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startFragmentTransaction(AmountFragment())
    }
}