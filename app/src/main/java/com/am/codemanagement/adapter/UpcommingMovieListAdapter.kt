package com.am.codemanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.UpcommingMovieVO
import com.am.codemanagement.viewHolder.BaseViewHolder
import com.am.codemanagement.viewHolder.UpcomingMovieListViewHolder

class UpcommingMovieListAdapter(private val context: Context) :
    BaseAdapter<BaseViewHolder<UpcommingMovieVO>, UpcommingMovieVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<UpcommingMovieVO> {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_upcomming_movie_list,parent,false)
        return UpcomingMovieListViewHolder(view)
    }

}
