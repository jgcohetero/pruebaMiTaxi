package com.example.pokemonapi.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// PokeApiResponse almacena las propiedades a las que deseamos acceder de la sección Pokemon

data class PokeApiResponse(

    /**
        @SerializedName permite conservar el nombre de la propiedad del JSON
        y poder usar un nombre distinto dentro de nuestro pragrama.
     **/

    @Expose @SerializedName("count") val count: Int,
    @Expose @SerializedName("next") val next: String,
    @Expose @SerializedName("previous") val previous: String,
    @Expose @SerializedName("results") val results: List<PokeResult>
)

// PokeResult Obtiene una lista de Pokemones

data class PokeResult(
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("url") val url: String
)
