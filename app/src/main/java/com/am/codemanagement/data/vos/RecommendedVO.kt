package com.am.codemanagement.data.vos

import java.io.Serializable

data class RecommendedVO(
    val adult: String,
    val backdrop_path: String,
    val id: String,
    val genre_ids: ArrayList<Int>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: String,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: String,
    val vote_average: String,
    val vote_count: String
) : Serializable{
}