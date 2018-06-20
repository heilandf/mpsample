package android.fheiland.com.mpsample.main

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.core.extensions.visibility
import android.fheiland.com.mpsample.payment.amount.view.AmountActivity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.empty_state_list.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: MainViewModelFactory
    @Inject
    lateinit var appContext: Context
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: EntitiesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        adapter = EntitiesAdapter(appContext)
        observeViewModel()

        addPaymentButton.setOnClickListener({ startActivity(Intent(this, AmountActivity::class.java)) })
        items_payed.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        emptyMessage.text = getString(R.string.no_payments_hint)
    }

    private fun observeViewModel() {
        viewModel.entities.observe(this, Observer {
            progressBar.visibility(it == null)
            it?.let {
                adapter.itemList = ArrayList(it)
                items_payed.layoutManager = LinearLayoutManager(this)
                items_payed.adapter = adapter
                items_payed.visibility(it.isNotEmpty())
                toggleEmptyState(it.isEmpty())
            }
        })
    }

    private fun toggleEmptyState(flag: Boolean) {
        items_payed.visibility(!flag)
        emptyState.visibility(flag)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposeObservables()
    }


}
