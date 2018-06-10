package android.fheiland.com.mpsample.payment.amount.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.amount.viewmodel.AmountViewModel
import android.fheiland.com.mpsample.payment.amount.viewmodel.factory.AmountViewModelFactory
import android.fheiland.com.mpsample.payment.methods.view.MethodsActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.CycleInterpolator
import android.view.animation.TranslateAnimation
import com.bumptech.glide.Glide
import com.jakewharton.rxbinding2.widget.RxTextView
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.amount_fragment.*
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 10/06/2018
 */
class AmountFragment : Fragment(), View.OnClickListener {

    @Inject
    lateinit var viewModelFactory: AmountViewModelFactory
    private lateinit var viewModel: AmountViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.amount_fragment, container, false)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide.with(this).load(R.drawable.mercadopago).into(placeholder)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        // Init ViewModel
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AmountViewModel::class.java)

        goPayButton.setOnClickListener(this)
        observeViewModel()
        observeTextChangesOnInput()
    }

    private fun shakeError(): TranslateAnimation {
        val shake = TranslateAnimation(0f, 10f, 0f, 0f)
        shake.duration = 500
        shake.interpolator = CycleInterpolator(7f)
        return shake
    }

    private fun observeViewModel() {
        viewModel.moveForwardButtonEnabled.observe(this, Observer { it?.let { goPayButton.isEnabled = it } })

        viewModel.moveForward.observe(this, Observer {
            it?.let {
                if (it) {
                    val intent = Intent(activity, MethodsActivity::class.java)
                    intent.putExtra("amount", amountInput.text.toString())
                    startActivity(intent)
                }
            }
        })
        viewModel.maxSizeText.observe(this, Observer { if (it == true) amountInput.textSize = 56f })
        viewModel.midSizeText.observe(this, Observer { if (it == true) amountInput.textSize = 36f })
        viewModel.normalSizeText.observe(this, Observer { if (it == true) amountInput.textSize = 24f })
    }

    private fun observeTextChangesOnInput() = RxTextView.textChanges(amountInput).subscribe({ viewModel.saveInputAmount(it.toString()) })
    private fun animateInput() = amountInput.startAnimation(shakeError())

    override fun onClick(p0: View?) {
        if (amountInput?.text.isNullOrEmpty())
            animateInput()
        else
            viewModel.moveForward()
    }
}