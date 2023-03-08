/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.searchResult

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.api.GithubApi
import jp.co.yumemi.android.code_check.model.Repository
import kotlinx.coroutines.launch

class SearchResultViewModel(val app: MainApplication) : ViewModel() {

    // APIを叩いた結果はList<Repository>に変換されここに入る
    private val _repositories = MutableLiveData<List<Repository>>(listOf())
    val repositories: LiveData<List<Repository>> = _repositories

    // githubAPIを叩き、検索結果をLiveDataに投げる
    fun searchRepositories(query: String) {
        viewModelScope.launch {
            GithubApi.searchRepositories(query, _repositories)
        }
    }
}