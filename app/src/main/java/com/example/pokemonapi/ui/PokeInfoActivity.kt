package com.example.pokemonapi.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.pokemonapi.R
import kotlinx.android.synthetic.main.activity_pokeinfo.*

class PokeInfoActivity: AppCompatActivity() {
    // Declarar VM.
    lateinit var viewModel: PokeInfoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokeinfo)

        // Inicializar VM.
        viewModel = ViewModelProvider(this).get(PokeInfoViewModel::class.java)
        initUI()
    }

    private fun initUI(){
        // "intent" recupera el id de PokeListActivity.
        val id = intent.extras?.get("id") as Int

        // Obtenemos
        viewModel.getPokemonInfo(id)

        // Verificamos si hay cambios y lo reasignamos en la vista.
        viewModel.pokemonInfo.observe(this, Observer { pokemon ->
            nameTextView.text = pokemon.name
            heightTextView.text = "Altura ${pokemon.height/10.0}m"
            weightTextView.text = "Peso ${pokemon.weight/10.0}"

            // Glide se encarga de cargar la imagen y lo coloca en su componente asigando de la vista.
            Glide.with(this).load(pokemon.sprites.frontDefault).into(imageView)
        })
    }
}