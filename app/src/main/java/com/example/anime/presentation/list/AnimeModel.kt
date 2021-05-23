package com.example.anime.presentation.list

sealed class AnimeModel

data class AnimeSuccess(val animeList: List<Anime>) : AnimeModel()
object AnimeLoader : AnimeModel()
object AnimeError : AnimeModel()