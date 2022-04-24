package com.am.codemanagement.viewHolder

import android.view.View
import com.am.codemanagement.R
import com.am.codemanagement.activity.MovieDetailActivity
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.utils.IMAGE_URL
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_recommended_list.view.*

class RecommendedListViewHolder(val view: View) : BaseViewHolder<UpcommingMovieVO>(view) {
    private lateinit var vo : UpcommingMovieVO
    private var title = ""
    init {
        view.setOnClickListener {
            itemView.context.startActivity(
                MovieDetailActivity.newIntent(itemView.context, vo,"up")
            )
        }
    }
    override fun bindData(data: UpcommingMovieVO) {
        vo = data
        Glide.with(itemView.context)
            .load( IMAGE_URL+data.poster_path)
            .error(R.drawable.welcome)
            .into(itemView.iv_image)
        view.tv_name.text = data.original_title
        view.tv_percent.text = "${(data.vote_average)} % "
    }

}
