package android.fheiland.com.mpsample.payment.methods.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.methods.view.adapter.MethodsAdapter
import android.fheiland.com.mpsample.payment.methods.viewmodel.MethodViewModel
import android.fheiland.com.mpsample.payment.methods.viewmodel.factory.MethodViewModelFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.methods_layout.*
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 11/06/2018
 */
class MethodsFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: MethodViewModelFactory

    private lateinit var viewModel: MethodViewModel
    private var adapter: MethodsAdapter? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.methods_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        AndroidSupportInjection.inject(this)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MethodViewModel::class.java)

        adapter = MethodsAdapter()
        val linearLayoutManager = LinearLayoutManager(activity)

        viewModel.paymentMethodsList.observe(this, Observer {
            it?.let {
                adapter?.methodList = it
                methodsList.layoutManager = linearLayoutManager
                methodsList.adapter = adapter
            }
        })
    }

}