package com.example.projectbookbeaconapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.projectbookbeaconapp.R
import com.example.projectbookbeaconapp.providers.UserBook

class SavedBooksAdapter(private val savedBooksList: List<UserBook>) :
    RecyclerView.Adapter<SavedBooksAdapter.SavedBooksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedBooksViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_saved_book, parent, false)
        return SavedBooksViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedBooksViewHolder, position: Int) {
        val book = savedBooksList[position]
        // Aquí vincula los datos del libro con las vistas en el ViewHolder
        holder.bind(book)

        holder.itemView.findViewById<Button>(R.id.detalles).setOnClickListener {
            showBookDetailsDialog(book, holder.itemView.context)
        }
    }

    private fun showBookDetailsDialog(book: UserBook, context: Context) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.fragment_details, null)

        // Inicializar los elementos de la ventana emergente con los detalles del libro
        dialogView.findViewById<TextView>(R.id.tvTitle).text = book.Title
        dialogView.findViewById<TextView>(R.id.tvAuthors).text = book.Author
        dialogView.findViewById<TextView>(R.id.tvGenres).text = book.genres

        AlertDialog.Builder(context)
            .setView(dialogView)
            .setPositiveButton("Cerrar") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    override fun getItemCount(): Int {
        return savedBooksList.size
    }

    class SavedBooksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(book: UserBook) {
            // Aquí asigna los datos del libro a las vistas en el ViewHolder
            itemView.findViewById<TextView>(R.id.titleTextViewProfile).text = book.Title
            itemView.findViewById<TextView>(R.id.authorTextViewProfile).text = book.Author
            itemView.findViewById<TextView>(R.id.genresTextViewProfile).text = book.genres
            // Aquí puedes agregar más lógica según sea necesario
        }
    }
}