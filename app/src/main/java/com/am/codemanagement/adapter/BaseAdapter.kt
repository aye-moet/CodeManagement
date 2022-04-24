package com.am.codemanagement.adapter

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.am.codemanagement.viewHolder.BaseViewHolder

abstract class BaseAdapter<T: BaseViewHolder<W>,W> : RecyclerView.Adapter<T>() {

    private var mDataList: MutableList<W> = mutableListOf()

    override fun onBindViewHolder(holder: T, position: Int) {
        //holder.bindData(mDataList[position])
        holder.bindData(mDataList[position])
    }

    override fun getItemCount(): Int {
        if (mDataList != null) {
            return mDataList.size
        }
        return 0
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    fun setData(data: MutableList<W>){
        Log.d("test_data", "data size = ${data.size} ${data}")
        mDataList = data
        //mDataList.addAll(data)
        notifyDataSetChanged()
    }
}