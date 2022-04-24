package com.am.codemanagement.viewHolder

import android.view.View
import com.am.codemanagement.R
import com.am.codemanagement.data.vos.CastVO
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.adapter_cast_list.view.*

class CastListViewHolder(val view: View) : BaseViewHolder<CastVO>(view) {
    private lateinit var vo : CastVO
    private var title = ""
    init {
        view.setOnClickListener {

        }
    }
    override fun bindData(data: CastVO) {
        vo = data
        Glide.with(itemView.context)
            .load( data.image)
            .error(R.drawable.welcome)
            .into(itemView.iv_image)
        view.tv_name.text = data.title
    }

}
