package com.github.issuetracker.api

import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.model.GithubIssueTrackerCommentsResponse
import com.github.issuetracker.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET(Constants.GITHUB_ISSUES_LIST_API)
    suspend fun fetchGithubIssueFromNetwork(): Response<List<GithubIssueTrackerEntity>>

    @GET(Constants.GITHUB_ISSUE_COMMENT_LIST_API)
    suspend fun fetchCommentsFromNetwork(@Path("issueNumber") issueNumber: Long):
            Response<List<GithubIssueTrackerCommentsResponse>>
}