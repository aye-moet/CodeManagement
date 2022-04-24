package com.am.codemanagement.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(view: View): RecyclerView.ViewHolder(view) {

    var mData: T? = null

    abstract fun bindData(data: T)
}