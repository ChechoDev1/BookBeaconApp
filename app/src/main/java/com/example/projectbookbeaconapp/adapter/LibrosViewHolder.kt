package com.example.projectbookbeaconapp.adapter
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbookbeaconapp.DatosLibros
import com.example.projectbookbeaconapp.databinding.ItemLibroBinding

class LibrosViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemLibroBinding.bind(view)


    fun render(datosLibros: DatosLibros){
        binding.tvLibroName.text = datosLibros.nombre
        binding.tvAutor.text = datosLibros.autor
        binding.tvGenero.text = datosLibros.genero
        Glide.with(binding.ivLibro.context).load(datosLibros.Imagen).into(binding.ivLibro)
    }
}