package com.example.pokemonapi.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pokemonapi.model.api.PokeApiResponse
import com.example.pokemonapi.model.api.PokeResult
import com.example.pokemonapi.service.PokeApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PokeListViewModel(): ViewModel() {

    // retrofit almacena la conexión a la PokeAPI
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://pokeapi.co/api/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // service crea una conexión con los endpoints y los métodos integrados en PokeApiService
    private val service: PokeApiService = retrofit.create(PokeApiService::class.java)

    // pokemonList almacenará la lista de los pokemones
    val pokemonList = MutableLiveData<List<PokeResult>>()

    fun getPokemonList(){

        // call accede a getPokemonList() y pasa los parametros limit y offset
        val call = service.getPokemonList(150, 0)

        /**
            Se envía la solicitud de forma asíncronica con "enqueue" y notificamos la devolución de llamada
            de su respuesta (onResponse()) o si se produjo un error al hablar con el servidor (onFailure()).
         **/
        call.enqueue(object : Callback<PokeApiResponse>{
            override fun onResponse(
                call: Call<PokeApiResponse>,
                response: Response<PokeApiResponse>
            ) {
                // Le asignamos la lista de los pokemones a pokemonList
                response.body()?.results?.let{ list ->
                pokemonList.postValue(list)
                }
            }

            /**
                Invocamos a onFailure() cuando se produce una excepción de red hablando con el servidor
                o cuando se produce una excepción inesperada al crear la solicitud o procesar la respuesta.
             **/
            override fun onFailure(call: Call<PokeApiResponse>, t: Throwable) {
                call.cancel()
            }

        })
    }
}