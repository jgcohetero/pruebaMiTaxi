package com.example.pokemonapi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapi.model.api.Pokemon
import com.example.pokemonapi.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeInfoViewModel(): ViewModel() {

    // retrofit almacena la conexión a la PokeAPI
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    // service crea una conexión con los endpoints y los métodos integrados en PokeApiService
    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    // pokemonList almacenará un objeto Pokemon
    val pokemonInfo = MutableLiveData<Pokemon>()


    fun getPokemonInfo(id: Int){

        // call accede al método getPokemonInfo()
        val call = service.getPokemonInfo(id)

        /**
        Se envía la solicitud de forma asíncronica con "enqueue" y notificamos la devolución de llamada
        de su respuesta (onResponse()) o si se produjo un error al hablar con el servidor (onFailure()).
         **/
        call.enqueue(object : Callback<Pokemon>{
            override fun onResponse(call: Call<Pokemon>, response: Response<Pokemon>) {
                response.body()?.let { pokemon ->
                    // Le asignamos el objeto pokemon a pokemonInfo
                    pokemonInfo.postValue(pokemon)
                }
            }

            /**
            Invocamos a onFailure() cuando se produce una excepción de red hablando con el servidor
            o cuando se produce una excepción inesperada al crear la solicitud o procesar la respuesta.
             **/
            override fun onFailure(call: Call<Pokemon>, t: Throwable) {
                call.cancel()
            }

        })
    }
}