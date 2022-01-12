package com.example.pokemonapi.model.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

// Pokemon almacena las pripiedades que tiene un pokemón en específico

data class Pokemon(

    /**
    @SerializedName permite conservar el nombre de la propiedad del JSON
    y poder usar un nombre distinto dentro de nuestro pragrama.
     **/

    @Expose @SerializedName("id") val id: Int,
    @Expose @SerializedName("name") val name: String,
    @Expose @SerializedName("weight") val weight: Int,
    @Expose @SerializedName("height") val height: Int,
    @Expose @SerializedName("sprites") val sprites: Sprites
)

// Sprites alamcena la pripiedad de la imagen de un pokemon en específico

data class Sprites (
    @Expose @SerializedName("front_default") val frontDefault: String?,
    @Expose @SerializedName("front_shiny") val frontShiny: String

    )
