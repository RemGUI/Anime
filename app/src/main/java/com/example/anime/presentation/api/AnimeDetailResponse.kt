package com.example.anime.presentation.api

import com.example.anime.presentation.list.AnimeAttributes

data class AnimeDetailResponse(
    val data: List<Genre>,
    val meta: Meta
)

data class Genre(
    val attributesGenre: AttributesGenre
)

data class AttributesGenre(
    val name: String
)

data class Meta(
    val count: String
)