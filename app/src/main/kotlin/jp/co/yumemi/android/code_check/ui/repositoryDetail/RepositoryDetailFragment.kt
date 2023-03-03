/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.repositoryDetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    private lateinit var context: Context

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRepositoryDetailBinding.inflate(inflater, container, false)
        context = requireContext()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        val repository: Repository = args.repository

        binding.ownerIconView.load(repository.ownerIconUrl)
        binding.nameView.text = repository.name
        binding.languageView.text = repository.language
        binding.starsView.text = context.getString(R.string.stargazers_count, repository.stargazersCount)
        binding.watchersView.text = context.getString(R.string.watchers_count, repository.watchersCount)
        binding.forksView.text = context.getString(R.string.forks_count, repository.forksCount)
        binding.openIssuesView.text = context.getString(R.string.open_issues_count, repository.openIssuesCount)
    }
}
