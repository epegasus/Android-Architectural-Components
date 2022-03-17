package com.architectural.components.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.architectural.components.R
import com.architectural.components.interfaces.OnItemClick
import com.architectural.components.model.Note
import com.google.android.material.button.MaterialButton

class CustomAdapterHome(
    private val noteList: List<Note>,
    private val onItemClick: OnItemClick
) :
    RecyclerView.Adapter<CustomAdapterHome.CustomViewHolderHome>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolderHome {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_note, parent, false)
        return CustomViewHolderHome(view, onItemClick)
    }

    override fun onBindViewHolder(holder: CustomViewHolderHome, position: Int) {
        val note: Note = noteList[position]
        with(note) {
            holder.apply {
                tvText.text = text
            }
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    class CustomViewHolderHome(itemView: View, onItemClick: OnItemClick) :
        RecyclerView.ViewHolder(itemView) {
        var tvText: TextView = itemView.findViewById(R.id.tv_text_List_Item_Note)
        var btnEdit: MaterialButton = itemView.findViewById(R.id.btn_edit_List_Item_Note)

        init {
            btnEdit.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onItemClick.onItemClickListener(adapterPosition)
                }
            }
        }
    }
}