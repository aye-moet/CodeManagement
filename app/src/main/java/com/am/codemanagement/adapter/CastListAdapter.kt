package com.am.codemanagement.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.CastVO
import com.am.codemanagement.viewHolder.BaseViewHolder
import com.am.codemanagement.viewHolder.CastListViewHolder
import com.am.codemanagement.viewHolder.RecommendedListViewHolder

class CastListAdapter(private val context: Context) :
    BaseAdapter<BaseViewHolder<CastVO>, CastVO>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<CastVO> {
        val view = LayoutInflater.from(context).inflate(R.layout.adapter_cast_list,parent,false)
        return CastListViewHolder(view)
    }

}
