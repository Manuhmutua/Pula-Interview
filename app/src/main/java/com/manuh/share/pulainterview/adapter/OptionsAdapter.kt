package com.manuh.share.pulainterview.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuh.share.pulainterview.R
import com.manuh.share.pulainterview.model.Option

class OptionsAdapter(
    var mList: MutableList<Option>
) :
    RecyclerView.Adapter<OptionsAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var optionName = itemView.findViewById(R.id.textViewOptionName) as TextView
        var optionRadioButton = itemView.findViewById(R.id.radioButtonOption) as RadioButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.option_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val item = mList[position]
        with(holder) {
            optionName.text = item.value
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: MutableList<Option>) {
        mList = list
        notifyDataSetChanged()
    }

}