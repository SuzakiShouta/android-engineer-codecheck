/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.searchResult

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchResultBinding
import jp.co.yumemi.android.code_check.model.Repository

class SearchResultFragment: Fragment(R.layout.fragment_search_result){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentSearchResultBinding.bind(view)
        val viewModel = SearchResultViewModel(context!!)
        val layoutManager = LinearLayoutManager(context!!)
        val dividerItemDecoration = DividerItemDecoration(context!!, layoutManager.orientation)
        val customAdapter = CustomAdapter(object : CustomAdapter.OnItemClickListener{
            override fun itemClick(item: Repository){
                gotoRepositoryFragment(item)
            }
        })

        // エンターキーなどのアクションに反応して検索をする
        binding.searchInputText.setOnEditorActionListener { editText, action, _ ->
            // IMEの検索ボタンに反応
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                // クエリを投げて結果をCustomAdapterに投げる。
                val query = editText.text.toString()
                viewModel.searchResults(query).let(customAdapter::submitList)
                true
            } else {
                false
            }
        }

        // RecyclerViewの設定
        binding.recyclerView.also{
            it.layoutManager= layoutManager
            // 要素間に区切り線を追加
            it.addItemDecoration(dividerItemDecoration)
            it.adapter= customAdapter
        }
    }

    fun gotoRepositoryFragment(repository: Repository) {
        val action: NavDirections = SearchResultFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(repository = repository)
        findNavController().navigate(action)
    }
}
