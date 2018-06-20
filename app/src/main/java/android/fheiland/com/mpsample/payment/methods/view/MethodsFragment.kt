package android.fheiland.com.mpsample.payment.methods.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.core.extensions.visibility
import android.fheiland.com.mpsample.payment.issuers.view.CardIssuerActivity
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
import android.fheiland.com.mpsample.payment.methods.view.adapter.MethodsAdapter
import android.fheiland.com.mpsample.payment.methods.viewmodel.MethodViewModel
import android.fheiland.com.mpsample.payment.methods.viewmodel.factory.MethodViewModelFactory
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LayoutAnimationController
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.list_layout.*
import javax.inject.Inject
import android.view.animation.AnimationUtils


/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodsFragment : Fragment(), MethodsAdapter.FragmentBridge {
    @Inject
    lateinit var viewModelFactory: MethodViewModelFactory
    private lateinit var viewModel: MethodViewModel
    private var adapter: MethodsAdapter? = null
    private var output: OutputEntity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.list_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)
        // Init view model with its own factory
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MethodViewModel::class.java)
        viewModel.fetchMethodPaymentList()
        // Retrieve parent output
        output = activity?.intent?.getSerializableExtra("output") as OutputEntity
        // Init layout manager and recycler view
        initRecyclerView()
        // Start observe view model variables
        observeViewModel()

    }

    private fun observeViewModel() {
        viewModel.paymentMethodsList.observe(this, Observer { it?.let { setupAdapterWithDatasource(it) } })
        viewModel.selectedPaymentMethod.observe(this, Observer { it?.let { startCardIssuerActivity(it) } })
    }

    private fun startCardIssuerActivity(method: PaymentMethod) {
        val intent = Intent(activity, CardIssuerActivity::class.java)
        output?.paymentMethod = method
        intent.putExtra("output", output)
        startActivity(intent)
    }

    private fun setupAdapterWithDatasource(it: MutableList<PaymentMethod>) {
        toggleVisibility(it.isEmpty())
        adapter?.methodList = it
        itemsList.adapter = adapter
    }

    private fun toggleVisibility(emptyList: Boolean) {
        loadingProgress.visibility(emptyList)
        itemsList.visibility(!emptyList)
    }

    private fun initRecyclerView() {
        adapter = MethodsAdapter(this)
        val linearLayoutManager = LinearLayoutManager(activity)
        itemsList.layoutAnimation = startLayoutAnimation()
        itemsList.layoutManager = linearLayoutManager
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeObservables()
    }

    override fun onItemClick(method: PaymentMethod?) {
        method?.let { viewModel.selectedMethod(it) }
    }

    private fun startLayoutAnimation(): LayoutAnimationController? = AnimationUtils.loadLayoutAnimation(activity, R.anim.layout_animation_from_bottom)

}