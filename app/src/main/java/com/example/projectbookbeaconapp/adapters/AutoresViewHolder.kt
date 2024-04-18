package com.example.projectbookbeaconapp.adapters
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.projectbookbeaconapp.providers.Autores
import com.example.projectbookbeaconapp.databinding.ItemAutoresBinding

class AutoresViewHolder(view:View):RecyclerView.ViewHolder(view) {

    val binding = ItemAutoresBinding.bind(view)


    fun render(datosAutores: Autores){
        binding.tvAutor.text = datosAutores.autor
        Glide.with(binding.ivAutor.context).load(datosAutores.photo).into(binding.ivAutor)
    }
}