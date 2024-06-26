package com.example.projectbookbeaconapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.R
import com.example.projectbookbeaconapp.providers.DatosLibros

class LibrosAdapter(private var ListaLibros:List<DatosLibros>) : RecyclerView.Adapter<LibrosViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return LibrosViewHolder(layoutInflater.inflate(R.layout.item_libro, parent, false))
    }

    override fun getItemCount(): Int = ListaLibros.size


    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val item = ListaLibros[position]
        holder.render(item)

    }

    @SuppressLint("NotifyDataSetChanged")
    fun actualizarDatos(nuevosLibros: List<DatosLibros>) {
        this.ListaLibros = nuevosLibros
        notifyDataSetChanged()
    }

}