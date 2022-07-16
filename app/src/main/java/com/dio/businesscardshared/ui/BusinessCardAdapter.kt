package com.elton.businesscardshared.ui

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elton.businesscardshared.data.BusinessCard
import com.elton.businesscardshared.databinding.ItemBusinessCardBinding


class BusinessCardAdapter :
      ListAdapter<BusinessCard, BusinessCardAdapter.ViewHolder>(DiffCallback()) {

    var listenerShare: (View) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessCardAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBusinessCardBinding.inflate(inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BusinessCardAdapter.ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: ItemBusinessCardBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BusinessCard) {
            binding.txtVNome.text = item.nome
            binding.txtVTelefone.text = item.telefone
            binding.txtVEmail.text = item.email
            binding.txtVNomeEmpresa.text = item.empresa
            binding.cardVContent.setCardBackgroundColor(item.fundoPersonalizado)
            binding.cardVContent.setOnClickListener {
                listenerShare(it)
            }
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<BusinessCard>() {
    override fun areItemsTheSame(oldItem: BusinessCard, newItem: BusinessCard) = oldItem == newItem
    override fun areContentsTheSame(oldItem: BusinessCard, newItem: BusinessCard) =
        oldItem.id == newItem.id
}