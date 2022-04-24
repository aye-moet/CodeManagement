package com.am.codemanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.OfflineRealmVO
import com.am.codemanagement.viewHolder.BaseViewHolder
import com.am.codemanagement.viewHolder.SearchListViewHolder
import com.am.codemanagement.viewHolder.UpcomingMovieListViewHolder

class SearchListAdapter(private val context: Context) :
    BaseAdapter<BaseViewHolder<OfflineRealmVO>, OfflineRealmVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<OfflineRealmVO> {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_upcomming_movie_list,parent,false)
        return SearchListViewHolder(view)
    }

}
