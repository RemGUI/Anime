package com.example.anime.presentation.list

data class AnimeAttributes(
    val slug: String,
    val synopsis: String,
    val titles: AnimeTitles,
    val canonicalTitle: String,
    val posterImage: PosterImage
)

data class AnimeTitles(
    val en: String,
    val en_jp: String,
    val ja_jp: String
)

data class PosterImage(
    val tiny: String,
    val small: String,
    val medium: String
)