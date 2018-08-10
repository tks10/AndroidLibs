package com.takashi.android_libs.utils

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import com.takashi.android_libs.R

class MyAdapter(private val myDataset: ArrayList<String>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    // RecyclerViewの一要素となるXML要素の型を引数に指定する
    // この場合はdiary_list_item.xmlのTextView
    class ViewHolder(val constraintLayout: ConstraintLayout) : RecyclerView.ViewHolder(constraintLayout)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyAdapter.ViewHolder {
        val constraintLayout = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false) as ConstraintLayout
        return ViewHolder(constraintLayout)
    }

    // 第１引数のViewHolderはこのファイルの上のほうで作成した`class ViewHolder`です。
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textView = holder.constraintLayout.getChildAt(0) as TextView?
        textView?.text = myDataset[position]
    }

    override fun getItemCount() = myDataset.size
}
