package com.example.projectbookbeaconapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbookbeaconapp.R
import com.example.projectbookbeaconapp.providers.libros_imagenes

class LibrosAdapter(private val libroslist: ArrayList<libros_imagenes>) : RecyclerView.Adapter<LibrosAdapter.LibrosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LibrosViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_libro, parent, false)
        return LibrosViewHolder(view)
    }

    override fun onBindViewHolder(holder: LibrosViewHolder, position: Int) {
        val libroslist = libroslist[position]
        holder.bind(libroslist)
    }

    override fun getItemCount(): Int {
        return libroslist.size
    }

    inner class LibrosViewHolder(itemView : View):RecyclerView.ViewHolder(itemView) {

        val Title : TextView = itemView.findViewById(R.id.tvLibroName)
        val Author : TextView = itemView.findViewById(R.id.tvAutor)
        val genres : TextView = itemView.findViewById(R.id.tvGenero)
        val Link : ImageView = itemView.findViewById(R.id.ivLibro)
        fun bind(librosImagenes: libros_imagenes) {
            Title.text = librosImagenes.Title
            Author.text = librosImagenes.Author
            genres.text = librosImagenes.genres
            Glide.with(Link.context).load(librosImagenes.Link).into(Link)
        }
    }

}
