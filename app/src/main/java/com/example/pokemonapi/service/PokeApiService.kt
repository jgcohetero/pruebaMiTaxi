package com.example.pokemonapi.service

import com.example.pokemonapi.model.api.PokeApiResponse
import com.example.pokemonapi.model.api.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {

    /**
        Obtenemos un elemento en especifico por ID.
        Recibe un valor Int (ID del elemento) que formara parte de la url.
        Call<Pokemon> recibe el elemento con los argumentos especificados en el modelo Pokemon.
     **/

    @GET("pokemon/{id}")
    fun getPokemonInfo(@Path("id") id: Int): Call<Pokemon>

    /**
        Obtenemos todos los elementos de la sección "pokemon".
        Se requiere un limit y un offset para mostrar solo una parte de los elementos.
        Retorna de acuerdo al modelo PokeApiResponse.
     **/

    @GET("pokemon")
    fun getPokemonList(@Query("limit") limit: Int, @Query("offset") offset: Int): Call<PokeApiResponse>
}