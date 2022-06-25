package com.github.issuetracker.api

import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.model.GithubIssueTrackerCommentsResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun fetchGithubIssueFromNetwork(): Response<List<GithubIssueTrackerEntity>>

    suspend fun fetchCommentsFromNetwork(issueNumber: Long): Response<List<GithubIssueTrackerCommentsResponse>>
}