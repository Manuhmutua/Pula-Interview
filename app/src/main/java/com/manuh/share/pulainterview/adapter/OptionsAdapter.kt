package com.manuh.share.pulainterview.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.manuh.share.pulainterview.R
import com.manuh.share.pulainterview.datastore.OptionManager
import com.manuh.share.pulainterview.model.Option
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class OptionsAdapter(
    var mList: MutableList<Option?>,
    var mContext: Context
) :
    RecyclerView.Adapter<OptionsAdapter.ViewHolder>(), CoroutineScope {

    lateinit var optionManager: OptionManager

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    private var lastCheckedPosition = 0
    var copyOfLastCheckedPosition: Int = 0

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
            optionName.text = item?.value
        }

        holder.optionRadioButton.isChecked = position == lastCheckedPosition

        holder.optionRadioButton.setOnClickListener { v ->
            copyOfLastCheckedPosition = lastCheckedPosition
            lastCheckedPosition = holder.adapterPosition
            notifyItemChanged(copyOfLastCheckedPosition)
            notifyItemChanged(lastCheckedPosition)

            launch {
                setOptionIndex(lastCheckedPosition, v.context)
            }

        }
    }

    suspend fun setOptionIndex(index: Int, context: Context) {
        optionManager = OptionManager(context)
        optionManager.storeIndex(index, context)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(list: MutableList<Option?>) {
        mList = list
        notifyDataSetChanged()
    }

}