package android.fheiland.com.mpsample.payment.issuers.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.core.extensions.visibility
import android.fheiland.com.mpsample.payment.installments.view.InstallmentsActivity
import android.fheiland.com.mpsample.payment.issuers.model.CardIssuer
import android.fheiland.com.mpsample.payment.issuers.view.adapter.CardIssuerAdapter
import android.fheiland.com.mpsample.payment.issuers.viewmodel.CardIssuerViewModel
import android.fheiland.com.mpsample.payment.issuers.viewmodel.factory.CardIssuerViewModelFactory
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.list_layout.*
import javax.inject.Inject

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerFragment : Fragment(), CardIssuerAdapter.FragmentBridge {

    @Inject
    lateinit var viewModelFactory: CardIssuerViewModelFactory
    private var viewModel: CardIssuerViewModel? = null
    var output: OutputEntity? = null
    private var adapter: CardIssuerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? = inflater.inflate(R.layout.list_layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        AndroidSupportInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CardIssuerViewModel::class.java)
        viewModel?.fetchIssuersList(output?.paymentMethod)

        initRecyclerView()
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel?.cardIssuerList?.observe(this, Observer { setupAdapterWithDatasource(it)  })
        viewModel?.selectedCardIssuer?.observe(this, Observer { it?.let { startInstallmentsActivity(it)  }})
        viewModel?.loadingError?.observe(this, Observer { it?.let { if (it) showError() } })
    }

    private fun showError() = Snackbar.make(coordinator, "Error at loading card issuers", Snackbar.LENGTH_SHORT).show()

    private fun startInstallmentsActivity(cardIssuer: CardIssuer) {
        output?.cardIssuer = cardIssuer
        val intent = Intent(activity, InstallmentsActivity::class.java)
        intent.putExtra("output", output)
        startActivity(intent)
    }

    private fun initRecyclerView() {
        adapter = CardIssuerAdapter(this)
        val linearLayoutManager = LinearLayoutManager(activity)
        itemsList.layoutManager = linearLayoutManager
    }

    private fun setupAdapterWithDatasource(cardIssuerList: List<CardIssuer>?) {
        toggleVisibility(cardIssuerList)
        adapter?.issuerList = cardIssuerList?.toMutableList()
        itemsList.adapter = adapter
    }

    private fun toggleVisibility(dataSource: List<CardIssuer>?) {
        loadingProgress.visibility(dataSource == null)
        dataSource?.let {
            itemsList.visibility(!dataSource.isEmpty())
            emptyState.visibility(dataSource.isEmpty())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel?.disposeObservables()
    }

    override fun onItemClick(issuer: CardIssuer?) {
        viewModel?.selectedCardIssuer?.postValue(issuer)
    }

    companion object {
        fun newInstance(output: OutputEntity) : CardIssuerFragment {
            val cardIssuerFragment = CardIssuerFragment()
            cardIssuerFragment.output = output
            return cardIssuerFragment
        }
    }
}