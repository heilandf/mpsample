package android.fheiland.com.mpsample.payment.issuers.view.adapter

import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.issuers.model.CardIssuer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.method_item.view.*

/**
 * Created by Federico Heiland - Quadion Technologies
 * 12/06/2018
 */
class CardIssuerAdapter(private val listener: FragmentBridge) : RecyclerView.Adapter<CardIssuerAdapter.ViewHolder>() {

    var issuerList: MutableList<CardIssuer>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.method_item, parent, false))
    override fun getItemCount(): Int = issuerList?.count() ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val issuer = issuerList?.get(position)
        issuer?.apply {
            holder.issuerName.text = issuerName
            Glide.with(holder.itemView.context)
                    .load(issuerImageUrl)
                    .into(holder.issuerImage)
        }

        holder.itemView.setOnClickListener({ listener.onItemClick(issuer) })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val issuerImage: ImageView = view.itemImage
        val issuerName: TextView = view.itemName
    }

    interface FragmentBridge {
        fun onItemClick(issuer: CardIssuer?)
    }
}