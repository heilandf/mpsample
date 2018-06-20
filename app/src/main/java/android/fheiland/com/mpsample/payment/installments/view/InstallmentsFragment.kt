package android.fheiland.com.mpsample.payment.installments.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.installments.model.Installment
import android.fheiland.com.mpsample.payment.installments.viewmodel.InstallmentsViewModel
import android.fheiland.com.mpsample.payment.installments.viewmodel.factory.InstallmentsViewModelFactory
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection

import javax.inject.Inject
import android.content.Intent
import android.fheiland.com.mpsample.main.MainActivity
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import kotlinx.android.synthetic.main.list_layout.*


/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentsFragment : Fragment(), InstallmentsAdapter.FragmentBridge {

    @Inject
    lateinit var viewModelFactory: InstallmentsViewModelFactory
    private var viewModel: InstallmentsViewModel? = null
    private var output: OutputEntity? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.list_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        // Init view model
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(InstallmentsViewModel::class.java)

        observeViewModel()
        retrieveOutputDataAndFetchItems()
    }

    private fun retrieveOutputDataAndFetchItems() {
        activity?.let {
            output = it.intent.getSerializableExtra("output") as OutputEntity
            output?.let { viewModel?.fetchInstallmentsByCreditCard(it) }
        }
    }

    private fun observeViewModel() {
        viewModel?.installmentData?.observe(this, Observer { it?.let { showInstallmentData(it) } })
        viewModel?.outputStoredSuccessfully?.observe(this, Observer { it?.let { if (it) endFlow() } })
        viewModel?.observableError?.observe(this, Observer { it?.let { if (it) showError("Error at loading installments") } })
        viewModel?.selectedInstallment?.observe(this, Observer { it?.let { storeOutput(it) } })
    }

    private fun storeOutput(it: String): Unit? {
        output?.selectedInstallment = it
        return viewModel?.storeOutputAndExit(output)
    }

    private fun showError(message: String) = Snackbar.make(coordinator, message, Snackbar.LENGTH_SHORT).show()

    private fun showInstallmentData(data: List<Installment>) {
        if (data.isNotEmpty()) {
            val adapter = InstallmentsAdapter(data.first().recommendedMessageList, this)
            itemsList.layoutManager = LinearLayoutManager(activity)
            itemsList.adapter = adapter
            itemsList.visibility = VISIBLE
            loadingProgress.visibility = GONE
        }
    }

    private fun endFlow() {
        val intent = Intent(activity, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.disposeObservables()
    }

    override fun onInstallmentClick(installment: String) {
        viewModel?.selectedInstallment?.postValue(installment)
    }
}