package android.fheiland.com.mpsample.payment.installments.view

import android.fheiland.com.mpsample.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.installment_item.view.*

/**
 * Created by Federico Heiland - Quadion Technologies
 * 13/06/2018
 */
class InstallmentsAdapter(private var items: List<String>, private var listener: FragmentBridge) : RecyclerView.Adapter<InstallmentsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.installment_item, parent, false))
    override fun getItemCount(): Int = items.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val installment = items[position]
        holder.itemText.text = installment
        holder.itemView.setOnClickListener({ listener.onInstallmentClick(installment) })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var itemText: TextView = view.installment
    }

    interface FragmentBridge {
        fun onInstallmentClick(installment: String)
    }

}