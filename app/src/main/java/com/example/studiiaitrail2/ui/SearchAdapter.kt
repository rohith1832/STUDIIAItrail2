package com.example.studiiaitrail2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studiiaitrail2.R
import com.example.studiiaitrail2.SearchItem

class SearchAdapter(private var items: List<SearchItem>) : RecyclerView.Adapter<SearchAdapter.ViewHolder>() {

    fun updateData(newItems: List<SearchItem>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_search_result, parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val titleView: TextView = itemView.findViewById(R.id.textTitle)
        private val linkView: TextView = itemView.findViewById(R.id.textLink)

        fun bind(item: SearchItem) {
            titleView.text = item.title
            linkView.text = item.link
        }
    }
}
