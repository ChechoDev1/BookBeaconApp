package com.example.projectbookbeaconapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.R
import com.example.projectbookbeaconapp.providers.DatosLibros


class LibrosAdapter(private val listaLibros: List<DatosLibros>) : RecyclerView.Adapter<LibrosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibrosViewHolder(layoutInflater.inflate(R.layout.item_libro, parent, false))
    }

    override fun getItemCount(): Int = listaLibros.size


    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val item = listaLibros[position]
        holder.render(item)

    }

}