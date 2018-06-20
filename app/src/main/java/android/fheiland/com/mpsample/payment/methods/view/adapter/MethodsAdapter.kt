package android.fheiland.com.mpsample.payment.methods.view.adapter

import android.fheiland.com.mpsample.R
import android.fheiland.com.mpsample.payment.methods.model.PaymentMethod
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
class MethodsAdapter(private val listener: FragmentBridge) : RecyclerView.Adapter<MethodsAdapter.ViewHolder>() {

    var methodList: MutableList<PaymentMethod>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.method_item, parent, false))
    override fun getItemCount(): Int = methodList?.count() ?: 0

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val method = methodList?.get(position)

        method?.apply {
            holder.methodName.text = methodName
            Glide.with(holder.itemView.context)
                    .load(imageUrl)
                    .into(holder.methodImage)
        }

        holder.itemView.setOnClickListener({ listener.onItemClick(method) })
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val methodName: TextView = view.itemName
        val methodImage: ImageView = view.itemImage
    }

    interface FragmentBridge {
        fun onItemClick(method: PaymentMethod?)
    }

}