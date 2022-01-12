package com.example.pokemonapi.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemonapi.R
import kotlinx.android.synthetic.main.activity_pokelist.*

class PokeListActivity: AppCompatActivity() {

    // Declarar VM
    private lateinit var viewModel: PokeListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokelist)

        // Inicializar VM
        viewModel = ViewModelProvider(this).get(PokeListViewModel::class.java)

        initUI()
    }

    private fun initUI(){
        pokelistRecyclerView.layoutManager = LinearLayoutManager(this)
        pokelistRecyclerView.adapter = PokeListAdapter{

            // Intent crea una comunicación con PokeInfoActivity y así obtener el ID de cada pokemón.
            val intent = Intent(this, PokeInfoActivity::class.java)
            intent.putExtra("id", it)
            // startActivity ininicia la actividad que se declara en Intent, que es envíar el ID
            startActivity(intent)
        }

        // Obtenemos la lista de los pokemones
        viewModel.getPokemonList()


        // Se manda la lista de los pokemones a la vista mediante el método setData()
        viewModel.pokemonList.observe(this, Observer { list ->
            (pokelistRecyclerView.adapter as PokeListAdapter).setData(list)
        })
    }


}