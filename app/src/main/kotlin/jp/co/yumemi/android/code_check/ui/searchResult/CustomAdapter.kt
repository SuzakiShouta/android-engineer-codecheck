package jp.co.yumemi.android.code_check.ui.searchResult

import android.util.Log
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

    // 昔のデータと比較して違った場合のみRecyclerViewのItemを更新
    private class DiffCallback : DiffUtil.ItemCallback<Repository>(){
        override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
            return oldItem == newItem
        }
    }

    // タップされたらRepositoryを返す。これを継承すればitemClickが呼ばれる。
    // リスナはAdapterのインスタンス化する時にもらっている。
    interface OnItemClickListener{
        fun itemClick(item: Repository)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)

    // parentにlayout_itemをinflateしてViewHolderを生成
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.layout_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository: Repository = getItem(position)
        val repositoryNameView = holder.itemView.findViewById<View>(R.id.repositoryNameView) as TextView
        repositoryNameView.text = repository.name

        // itemViewそのものを押した時リスナを通してイベントを送る
        holder.itemView.setOnClickListener{
            itemClickListener.itemClick(repository)
        }
    }
}