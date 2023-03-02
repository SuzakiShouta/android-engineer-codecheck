/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.searchResult

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchResultBinding
import jp.co.yumemi.android.code_check.model.Repository

class SearchResultFragment: Fragment(R.layout.fragment_search_result){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding= FragmentSearchResultBinding.bind(view)
        val viewModel= SearchResultViewModel(context!!)
        val layoutManager= LinearLayoutManager(context!!)
        val dividerItemDecoration= DividerItemDecoration(context!!, layoutManager.orientation)
        val adapter= CustomAdapter(object : CustomAdapter.OnItemClickListener{
            override fun itemClick(item: Repository){
                gotoRepositoryFragment(item)
            }
        })

        binding.searchInputText
            .setOnEditorActionListener{ editText, action, _ ->
                if (action== EditorInfo.IME_ACTION_SEARCH){
                    editText.text.toString().let {
                        viewModel.searchResults(it).apply{
                            adapter.submitList(this)
                        }
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        binding.recyclerView.also{
            it.layoutManager= layoutManager
            it.addItemDecoration(dividerItemDecoration)
            it.adapter= adapter
        }
    }

    fun gotoRepositoryFragment(repository: Repository) {
        val _action= SearchResultFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(repository = repository)
        findNavController().navigate(_action)
    }
}

val diff_util= object: DiffUtil.ItemCallback<Repository>(){
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean {
        return oldItem == newItem
    }

}
