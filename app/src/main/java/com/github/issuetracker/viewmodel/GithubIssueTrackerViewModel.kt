package com.github.issuetracker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.model.GithubIssueTrackerCommentsResponse
import com.github.issuetracker.repository.GithubIssueTrackerRepository
import com.github.issuetracker.utils.Constants
import com.github.issuetracker.utils.NetworkHelper
import com.github.issuetracker.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubIssueTrackerViewModel @Inject constructor(
    private val repository: GithubIssueTrackerRepository,
    private val networkHelper: NetworkHelper
) : ViewModel() {

    private val _githubIssueTrackerLiveData = MutableLiveData<Resource<List<GithubIssueTrackerEntity>>>()
    val githubIssueTrackerLiveData: LiveData<Resource<List<GithubIssueTrackerEntity>>> get() = _githubIssueTrackerLiveData

    private val _githubIssueTrackerCommentsLiveData =
        MutableLiveData<Resource<List<GithubIssueTrackerCommentsResponse>>>()
    val githubIssueTrackerCommentsLiveData: LiveData<Resource<List<GithubIssueTrackerCommentsResponse>>>
        get() = _githubIssueTrackerCommentsLiveData

    //fetching all issues from network and saving them in DB
    fun fetchGithubIssueFromNetwork() = viewModelScope.launch {
        _githubIssueTrackerLiveData.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            repository.fetchGithubIssueFromNetwork().let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { githubIssueTrackerEntity ->
                        //inserting data into db
                        repository.insertGithubIssue(githubIssueTrackerEntity)
                        _githubIssueTrackerLiveData.postValue(Resource.success(githubIssueTrackerEntity))
                    }
                } else _githubIssueTrackerLiveData.postValue(Resource.error(response.errorBody().toString(), null))
            }
        } else _githubIssueTrackerLiveData.postValue(Resource.error(Constants.INTERNET_CONNECTION_ERROR_MESSAGE, null))
    }

    //fetching all comments from network
    fun fetchCommentsFromNetwork(issueNumber: Long) = viewModelScope.launch {
        _githubIssueTrackerCommentsLiveData.postValue(Resource.loading(null))
        if (networkHelper.isNetworkConnected()) {
            repository.fetchCommentsFromNetwork(issueNumber).let { response ->
                if (response.isSuccessful) {
                    response.body()?.let { githubIssueTrackerCommentsList ->
                        _githubIssueTrackerCommentsLiveData.postValue(
                            Resource.success(
                                githubIssueTrackerCommentsList
                            )
                        )
                    }
                } else _githubIssueTrackerCommentsLiveData.postValue(
                    Resource.error(
                        response.errorBody().toString(),
                        null
                    )
                )
            }
        } else _githubIssueTrackerCommentsLiveData.postValue(
            Resource.error(
                Constants.INTERNET_CONNECTION_ERROR_MESSAGE,
                null
            )
        )
    }

    //returning LiveData List of Github Issues from DB
    val getGithubIssuesFromDB = repository.getAllGithubIssuesFromDB()

    //returning LiveData Detail of Github Issue from DB
    fun getGithubIssueDetailFromDB(id: Long) = repository.getGithubIssueDetailFromDB(id)
}