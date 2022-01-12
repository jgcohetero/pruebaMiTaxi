package com.example.pokemonapi.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemonapi.R
import com.example.pokemonapi.model.api.PokeResult
import kotlinx.android.synthetic.main.card_pokemon_search.view.*

class PokeListAdapter(val pokemonClick:(Int) -> Unit):RecyclerView.Adapter<PokeListAdapter.SearchViewHolder>() {

    // pokemonList se inicializa como una lista de vacia de tipo PokeResult.
    var pokemonList: List<PokeResult> = emptyList<PokeResult>()

    // setData() obtiene una lista y se la asigna a pokemonList.
    fun setData(list: List<PokeResult>){
        pokemonList = list

        // Notifica a cualquier observador registrado que el conjunto de datos ha cambiado.
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        // Retornamos la vista card_pokemon_search para su visualización.
        return SearchViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_pokemon_search,parent, false))
    }

        // Retornamos el tamaño de la lista.
    override fun getItemCount(): Int = pokemonList.size

        // Devolvemos a la vista cada pokemon por su posición.
    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val pokemon = pokemonList[position]
        holder.itemView.pokemonText.text = "#${position + 1} - ${pokemon.name}"

        holder.itemView.setOnClickListener {pokemonClick(position + 1)}
    }

    class SearchViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}