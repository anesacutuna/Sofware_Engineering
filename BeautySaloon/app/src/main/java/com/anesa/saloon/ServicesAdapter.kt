package com.anesa.saloon

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.anesa.saloon.responses.Service

class ServicesAdapter(
    private val services: List<Service>,
    private val onServiceSelected: (serviceId: Int) -> Unit
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.service_item,
            parent,
            false
        )
        return ServiceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = services[position]
        holder.bind(service)
    }

    override fun getItemCount(): Int {
        return services.size
    }

    inner class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleTextView: TextView = itemView.findViewById(R.id.title)

        fun bind(service: Service) {
            titleTextView.text = service.title

            itemView.setOnClickListener {
                val serviceId = service.id
                onServiceSelected(serviceId)
            }
        }
    }
}
