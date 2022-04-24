package com.am.codemanagement.viewHolder

import android.view.View
import com.am.codemanagement.R
import com.am.codemanagement.activity.MovieDetailActivity
import com.am.codemanagement.data.vos.OfflineRealmCopyVO
import com.am.codemanagement.data.vos.OfflineRealmVO
import com.am.codemanagement.utils.IMAGE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_upcomming_movie_list.view.*

class SearchListViewHolder(val view: View) : BaseViewHolder<OfflineRealmVO>(view) {
    private lateinit var vo : OfflineRealmVO
    private lateinit var voCopy : OfflineRealmCopyVO
    private var title = ""
    init {
        view.setOnClickListener {
            view.context.startActivity(
                MovieDetailActivity.newIntent(itemView.context, voCopy,"offline")
            )
        }

    }
    override fun bindData(data: OfflineRealmVO) {
        vo = data
        voCopy = OfflineRealmCopyVO()
        voCopy.adult = vo.adult
        voCopy.title = vo.title
        voCopy.backdrop_path = vo.backdrop_path
        voCopy.original_language = vo.original_language
        voCopy.original_title = vo.original_title
        voCopy.overview = vo.overview
        voCopy.popularity = vo.popularity
        voCopy.poster_path = vo.poster_path
        voCopy.release_date = vo.release_date
        voCopy.title = vo.title
        voCopy.video = vo.video
        voCopy.vote_average = vo.vote_average
        voCopy.vote_count = vo.vote_count
        voCopy.genre_ids1 = vo.genre_ids1
        voCopy.genre_id2 = vo.genre_id2
        voCopy.genre_ids3 = vo.genre_ids3
        voCopy.isFavourite = vo.isFavourite

        Glide.with(itemView.context)
            .load( IMAGE_URL+data.poster_path)
            .into(itemView.iv_image)
        view.tv_name.text = data.original_title
        view.tv_description.text = data.overview
        view.tv_percent.text = data.vote_average + " % "
        view.tv_count.text = data.vote_count
    }

}
