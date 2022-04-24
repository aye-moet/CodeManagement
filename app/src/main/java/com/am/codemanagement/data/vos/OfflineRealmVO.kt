package com.am.codemanagement.data.vos

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class OfflineRealmVO (
    @PrimaryKey
    var id: Int = 1,

    var adult: String = "",
    var backdrop_path: String = "",
    var genre_ids1: Int = 0,
    var genre_id2: Int = 0,
    var genre_ids3: Int = 0,
    var original_language: String = "",
    var original_title: String = "",
    var overview: String = "",
    var popularity: String = "",
    var poster_path: String = "",
    var release_date: String = "",
    var title: String = "",
    var video: String = "",
    var vote_average: String = "",
    var vote_count: String = "",
    var isFavourite : Boolean = false
    ):Serializable ,RealmObject() {
}