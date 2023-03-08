/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.searchResult

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.databinding.FragmentSearchResultBinding
import jp.co.yumemi.android.code_check.model.Repository

class SearchResultFragment: Fragment(R.layout.fragment_search_result){

    private var _binding: FragmentSearchResultBinding? = null
    private val binding get() = _binding!!

    lateinit var app: MainApplication

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchResultBinding.inflate(inflater, container, false)
        app = requireActivity().application as MainApplication
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel = SearchResultViewModel(app)
        val layoutManager = LinearLayoutManager(requireContext())
        val dividerItemDecoration = DividerItemDecoration(requireContext(), layoutManager.orientation)
        val customAdapter = CustomAdapter(object : CustomAdapter.OnItemClickListener{
            override fun itemClick(item: Repository){
                gotoRepositoryFragment(item)
            }
        })

        // エンターキーなどのアクションに反応して検索をする
        binding.searchInputText.setOnEditorActionListener { editText, action, _ ->
            // IMEの検索ボタンに反応
            if (action == EditorInfo.IME_ACTION_SEARCH) {
                // searchResultsの結果はviewModelのrepositoriesというLiveDataが持つ
                if (viewModel.validationCheck(editText.text)) {
                    val query = editText.text.toString()
                    viewModel.searchRepositories(query)
                }
                true
            } else {
                false
            }
        }

        // repositoriesが更新されたらアダプタに渡す
        viewModel.repositories.observe(viewLifecycleOwner) {
            customAdapter.submitList(it)
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
