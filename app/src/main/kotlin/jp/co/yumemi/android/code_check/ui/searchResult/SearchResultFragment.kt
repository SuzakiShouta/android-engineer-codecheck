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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        val _binding= FragmentSearchResultBinding.bind(view)

        val _viewModel= SearchResultViewModel(context!!)

        val _layoutManager= LinearLayoutManager(context!!)
        val _dividerItemDecoration=
            DividerItemDecoration(context!!, _layoutManager.orientation)
        val _adapter= CustomAdapter(object : CustomAdapter.OnItemClickListener{
            override fun itemClick(item: Repository){
                gotoRepositoryFragment(item)
            }
        })

        _binding.searchInputText
            .setOnEditorActionListener{ editText, action, _ ->
                if (action== EditorInfo.IME_ACTION_SEARCH){
                    editText.text.toString().let {
                        _viewModel.searchResults(it).apply{
                            _adapter.submitList(this)
                        }
                    }
                    return@setOnEditorActionListener true
                }
                return@setOnEditorActionListener false
            }

        _binding.recyclerView.also{
            it.layoutManager= _layoutManager
            it.addItemDecoration(_dividerItemDecoration)
            it.adapter= _adapter
        }
    }

    fun gotoRepositoryFragment(repository: Repository)
    {
        val _action= SearchResultFragmentDirections
            .actionRepositoriesFragmentToRepositoryFragment(repository = repository)
        findNavController().navigate(_action)
    }
}

val diff_util= object: DiffUtil.ItemCallback<Repository>(){
    override fun areItemsTheSame(oldItem: Repository, newItem: Repository): Boolean
    {
        return oldItem.name== newItem.name
    }

    override fun areContentsTheSame(oldItem: Repository, newItem: Repository): Boolean
    {
        return oldItem== newItem
    }

}
