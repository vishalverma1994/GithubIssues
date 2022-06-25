package com.github.issuetracker.data

import android.content.Context
import androidx.room.Embedded
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.issuetracker.db.GithubIssueTrackerDataBase
import com.github.issuetracker.db.dao.GithubIssueTrackerDao
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.model.LabelsData
import com.github.issuetracker.model.PullRequestData
import com.github.issuetracker.model.ReactionsData
import com.github.issuetracker.model.User
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat

@RunWith(AndroidJUnit4::class)
class GithubIssueDataBaseTest: TestCase() {

    private lateinit var githubIssueTrackerDataBase: GithubIssueTrackerDataBase
    private lateinit var githubIssueTrackerDao: GithubIssueTrackerDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        githubIssueTrackerDataBase = Room.inMemoryDatabaseBuilder(context, GithubIssueTrackerDataBase::class.java).build()
        githubIssueTrackerDao = githubIssueTrackerDataBase.getGithubIssueTrackerDao()
    }

    @After
    fun closeDataBase() {
        githubIssueTrackerDataBase.close()
    }

    @Test
    fun writeAndReadGithubIssue() = runBlocking {
        val githubIssueTrackerEntity = setGithubIssueTrackerEntity()
        githubIssueTrackerDao.insertGithubIssueTrackerEntity(githubIssueTrackerEntity)
        val githubIssueTrackerEntityList = githubIssueTrackerDao.getAllTestGithubIssueTrackerList()
        assertThat(githubIssueTrackerEntityList.contains(githubIssueTrackerEntity)).isTrue()
    }

    private fun setGithubIssueTrackerEntity(): GithubIssueTrackerEntity {
        val githubIssueTrackerEntity = GithubIssueTrackerEntity()
        githubIssueTrackerEntity.url = "https://www.github.com/dummy_issues"
        githubIssueTrackerEntity.repository_url = "https://www.github.com/dummy_repository_url"
        githubIssueTrackerEntity.labels_url = "https://www.github.com/dummy_labels_url"
        githubIssueTrackerEntity.comments_url = "https://www.github.com/dummy_comments_url"
        githubIssueTrackerEntity.events_url = "https://www.github.com/dummy_events_url"
        githubIssueTrackerEntity.html_url = "https://www.github.com/dummy_html_url"
        githubIssueTrackerEntity.id = 99999
        githubIssueTrackerEntity.node_id = "99999_difg"
        githubIssueTrackerEntity.number = 98989
        githubIssueTrackerEntity.title = "dummy_title"
        githubIssueTrackerEntity.title = "dummy_title"
        val user = User()
        user.login = "dummy_user"
        user.user_id = 7890
        user.user_node_id = "7890_hnb"
        user.avatar_url = "https://www.github.com/dummy_avatar_url"
        user.gravatar_id = "f_3423234234"
        user.user_url = "https://www.github.com/dummy_user_url"
        user.user_html_url = "https://www.github.com/dummy_user_html_url"
        user.followers_url = "https://www.github.com/dummy_user_followers_url"
        user.following_url = "https://www.github.com/dummy_user_following_url"
        user.gists_url = "https://www.github.com/dummy_user_gists_url"
        user.starred_url = "https://www.github.com/dummy_user_starred_url"
        user.subscriptions_url = "https://www.github.com/dummy_user_subscriptions_url"
        user.organizations_url = "https://www.github.com/dummy_user_organizations_url"
        user.repos_url = "https://www.github.com/dummy_user_repos_url"
        user.user_events_url = "https://www.github.com/dummy_user_events_url"
        user.received_events_url = "https://www.github.com/dummy_user_received_events_url"
        user.type = "user_dummy_type"
        user.site_admin = false
        githubIssueTrackerEntity.user = user
        val labels = mutableListOf<LabelsData>()
        val labelsData = LabelsData()
        labelsData.id = 9896
        labelsData.url = "https://www.github.com/labels_url"
        labelsData.node_id = "434_node_dummy_id"
        labelsData.color = "blue"
        labelsData.default = false
        labelsData.name = "dummy_labels"
        labels.add(labelsData)
        githubIssueTrackerEntity.labels = labels
        githubIssueTrackerEntity.state = "open"
        githubIssueTrackerEntity.locked = false
        githubIssueTrackerEntity.assignee = null
        val assigneesList = emptyList<Any>()
        githubIssueTrackerEntity.assignees = assigneesList
        githubIssueTrackerEntity.milestone = null
        githubIssueTrackerEntity.comments = 0
        githubIssueTrackerEntity.created_at = "12-06-2022"
        githubIssueTrackerEntity.updated_at = "14-06-2022"
        githubIssueTrackerEntity.closed_at = "28-06-2022"
        githubIssueTrackerEntity.author_association = "dummy_author_association"
        githubIssueTrackerEntity.active_lock_reason = null
        githubIssueTrackerEntity.draft = false
        val pullRequestData = PullRequestData()
        pullRequestData.pull_request_html_url = "https://www.github.com/dummy_pull_request_html_url"
        pullRequestData.diff_url = "https://www.github.com/dummy_diff_url"
        pullRequestData.pull_request_url = "https://www.github.com/dummy_pull_request_url"
        pullRequestData.patch_url = "https://www.github.com/dummy_patch_url"
        pullRequestData.merged_at = null
        githubIssueTrackerEntity.pull_request = pullRequestData
        githubIssueTrackerEntity.body = "dummy_issue_body"
        val reactionsData = ReactionsData()
        reactionsData.reactions_url = "https://www.github.com/dummy_reactions_url"
        reactionsData.total_count = "12"
        reactionsData.eyes = 0
        reactionsData.plus_one = 0
        reactionsData.minus_one = 0
        reactionsData.laugh = 0
        reactionsData.hooray = 0
        reactionsData.confused = 0
        reactionsData.heart = 0
        reactionsData.rocket = 0
        githubIssueTrackerEntity.reactions = reactionsData
        githubIssueTrackerEntity.timeline_url = "https://www.github.com/dummy_timeline_url"
        githubIssueTrackerEntity.performed_via_github_app = null
        githubIssueTrackerEntity.state_reason = null
        return githubIssueTrackerEntity
    }
}