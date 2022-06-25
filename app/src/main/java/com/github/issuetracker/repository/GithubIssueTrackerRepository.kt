package com.github.issuetracker.repository

import com.github.issuetracker.api.ApiHelper
import com.github.issuetracker.db.dao.GithubIssueTrackerDao
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import javax.inject.Inject

class GithubIssueTrackerRepository @Inject constructor(
    private val githubIssueTrackerDao: GithubIssueTrackerDao,
    private val apiHelper: ApiHelper
) {

    suspend fun fetchGithubIssueFromNetwork() = apiHelper.fetchGithubIssueFromNetwork()

    suspend fun fetchCommentsFromNetwork(issueNumber: Long) = apiHelper.fetchCommentsFromNetwork(issueNumber)

    suspend fun insertGithubIssue(githubIssueTrackerEntity: List<GithubIssueTrackerEntity>) =
        githubIssueTrackerDao.insertGithubIssueTracker(githubIssueTrackerEntity)

    fun getAllGithubIssuesFromDB() = githubIssueTrackerDao.getAllGithubIssueTrackerList()

    fun getGithubIssueDetailFromDB(id: Long) = githubIssueTrackerDao.getGithubIssueTrackerDetail(id)
}