/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import jp.co.yumemi.android.code_check.TopActivity.Companion.lastSearchDate
import jp.co.yumemi.android.code_check.databinding.FragmentTwoBinding
import jp.co.yumemi.android.code_check.model.Repository

class TwoFragment : Fragment(R.layout.fragment_two) {

    private val args: TwoFragmentArgs by navArgs()

    private var binding: FragmentTwoBinding? = null
    private val _binding get() = binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("検索した日時", lastSearchDate.toString())

        binding = FragmentTwoBinding.bind(view)

        var repository: Repository = args.repository

        _binding.ownerIconView.load(repository.ownerIconUrl);
        _binding.nameView.text = repository.name;
        _binding.languageView.text = repository.language;
        _binding.starsView.text = "${repository.stargazersCount} stars";
        _binding.watchersView.text = "${repository.watchersCount} watchers";
        _binding.forksView.text = "${repository.forksCount} forks";
        _binding.openIssuesView.text = "${repository.openIssuesCount} open issues";
    }
}
