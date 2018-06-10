package android.fheiland.com.mpsample

import android.content.Intent
import android.fheiland.com.mpsample.payment.amount.view.AmountActivity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addPaymentButton.setOnClickListener({
            startActivity(Intent(this, AmountActivity::class.java))
        })
    }
}
