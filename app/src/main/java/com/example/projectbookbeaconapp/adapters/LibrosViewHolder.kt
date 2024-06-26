package com.example.projectbookbeaconapp.adapters
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbookbeaconapp.databinding.ItemLibroBinding
import com.example.projectbookbeaconapp.providers.DatosLibros

class LibrosViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemLibroBinding.bind(view)


    fun render(DatosLibros: DatosLibros){
        binding.tvLibroName.text = DatosLibros.Title
        binding.tvGenero.text = DatosLibros.genres
        binding.tvAutor.text = DatosLibros.Author
        Glide.with(binding.ivLibro.context).load(DatosLibros.Link).into(binding.ivLibro)
    }
}