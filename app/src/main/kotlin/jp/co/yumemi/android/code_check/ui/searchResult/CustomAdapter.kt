package jp.co.yumemi.android.code_check.ui.searchResult

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.model.Repository

class CustomAdapter(private val itemClickListener: OnItemClickListener)
    : ListAdapter<Repository, CustomAdapter.ViewHolder>(DiffCallback()){

    private class DiffCallback : DiffUtil.ItemCallback<Repository>(){
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    interface OnItemClickListener{
        fun itemClick(item: Repository)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository= getItem(position)
        val repositoryNameView = holder.itemView.findViewById<View>(R.id.repositoryNameView) as TextView
        repositoryNameView.text = repository.name

        holder.itemView.setOnClickListener{
            itemClickListener.itemClick(repository)
        }
    }
}