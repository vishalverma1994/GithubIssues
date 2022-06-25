package com.github.issuetracker.api

import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.model.GithubIssueTrackerCommentsResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun fetchGithubIssueFromNetwork(): Response<List<GithubIssueTrackerEntity>> =
        apiService.fetchGithubIssueFromNetwork()

    override suspend fun fetchCommentsFromNetwork(issueNumber: Long): Response<List<GithubIssueTrackerCommentsResponse>> =
        apiService.fetchCommentsFromNetwork(issueNumber)

}