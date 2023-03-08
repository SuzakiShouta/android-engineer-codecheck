package jp.co.yumemi.android.code_check

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jp.co.yumemi.android.code_check.model.Repository

class MainApplication: Application() {

    // APIを叩いた結果はList<Repository>に変換されここに入る
    private val _repositories = MutableLiveData<List<Repository>>(listOf())
    val repositories: LiveData<List<Repository>> = _repositories

    fun setRepositories(input: List<Repository>) {
        _repositories.postValue(input)
    }

}