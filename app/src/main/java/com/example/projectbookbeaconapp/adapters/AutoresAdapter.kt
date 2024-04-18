package com.example.projectbookbeaconapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.providers.Autores
import com.example.projectbookbeaconapp.R


class AutoresAdapter(private val listaAutores:List<Autores>) : RecyclerView.Adapter<AutoresViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AutoresViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return AutoresViewHolder(layoutInflater.inflate(R.layout.item_autores, parent, false))
    }

    override fun getItemCount(): Int = listaAutores.size


    override fun onBindViewHolder(holder: AutoresViewHolder, position: Int) {
        val item = listaAutores[position]
        holder.render(item)

    }

}