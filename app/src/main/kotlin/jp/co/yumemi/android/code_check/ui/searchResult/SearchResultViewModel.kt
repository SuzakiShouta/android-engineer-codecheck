/*
 * Copyright © 2021 YUMEMI Inc. All rights reserved.
 */
package jp.co.yumemi.android.code_check.ui.searchResult

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import jp.co.yumemi.android.code_check.MainApplication
import jp.co.yumemi.android.code_check.R
import jp.co.yumemi.android.code_check.api.GithubApi
import kotlinx.coroutines.launch

class SearchResultViewModel(val app: MainApplication) : ViewModel() {

    // githubAPIを叩き、検索結果をLiveDataに投げる
    fun searchRepositories(query: String) {
        viewModelScope.launch {
            app.setRepositories(GithubApi.searchRepositories(query))
        }
    }

    fun validationCheck (query: CharSequence): Boolean {
        // null もしくは 空白のみ
        if (query.isBlank()) {
            Toast.makeText(
                app.applicationContext,
                app.applicationContext.getString(R.string.error_no_query),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        // 256文字以上の時
        if (query.length > 255) {
            Toast.makeText(
                app.applicationContext,
                app.applicationContext.getString(R.string.error_too_long_query),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }
}