package android.fheiland.com.mpsample.main

import android.content.Context
import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.output.OutputEntity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.payment_item.view.*

/**
 * Created by Federico Heiland - Quadion Technologies
 * 16/06/2018
 */
class EntitiesAdapter(private val appContext: Context) : RecyclerView.Adapter<EntitiesAdapter.ViewHolder>() {
    var itemList: List<OutputEntity>? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.payment_item, parent, false))
    override fun getItemCount(): Int = itemList?.size ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList?.get(position)
        item?.let {
            Glide.with(holder.itemView.context)
                    .load(it.cardIssuer?.issuerImageUrl)
                    .into(holder.picture)

            holder.title.text = appContext.getString(R.string.formatted_name, it.cardIssuer?.issuerName.orEmpty(), it.paymentMethod?.methodName.orEmpty())
            holder.installmentText.text = it.selectedInstallment
        }
    }




    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val picture: ImageView = view.imageView
        val title: TextView = view.card
        val installmentText: TextView = view.installment

    }

}