/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.repositoryDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentRepositoryDetailBinding
import jp.co.yumemi.android.code_check.model.Repository

class RepositoryDetailFragment : Fragment(R.layout.fragment_repository_detail) {

    private val args: RepositoryDetailFragmentArgs by navArgs()

    private var _binding: FragmentRepositoryDetailBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        _binding = FragmentRepositoryDetailBinding.bind(view)

        val repository: Repository = args.repository

        binding.ownerIconView.load(repository.ownerIconUrl)
        binding.nameView.text = repository.name
        binding.languageView.text = repository.language
        binding.starsView.text = "${repository.stargazersCount} stars"
        binding.watchersView.text = "${repository.watchersCount} watchers"
        binding.forksView.text = "${repository.forksCount} forks"
        binding.openIssuesView.text = "${repository.openIssuesCount} open issues"
    }
}
