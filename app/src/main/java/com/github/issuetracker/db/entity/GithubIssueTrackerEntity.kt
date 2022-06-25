package com.github.issuetracker.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.github.issuetracker.model.LabelsData
import com.github.issuetracker.model.PullRequestData
import com.github.issuetracker.model.ReactionsData
import com.github.issuetracker.model.User

@Entity(tableName = "github_issue_tracker_table")
data class GithubIssueTrackerEntity(
    var url: String = "",
    var repository_url: String = "",
    var labels_url: String = "",
    var comments_url: String = "",
    var events_url: String = "",
    var html_url: String = "",
    @PrimaryKey
    var id: Long = -1,
    var node_id: String = "",
    var number: Long = -1,
    var title: String? = "",
    @Embedded
    var user: User? = null,
    var labels: List<LabelsData>? = null,
    var state: String = "",
    var locked: Boolean = false,
    @Embedded
    var assignee: Any? = null,
    var assignees:List<Any>? = null,
    @Embedded
    var milestone: Any? = null,
    var comments: Int = -1,
    var created_at: String = "",
    var updated_at: String? = "",
    var closed_at: String? = "",
    var author_association: String = "",
    @Embedded
    var active_lock_reason: Any? = null,
    var draft: Boolean = false,
    @Embedded
    var pull_request: PullRequestData? = null,
    var body: String? = "",
    @Embedded
    var reactions: ReactionsData? = null,
    var timeline_url: String = "",
    @Embedded
    var performed_via_github_app: Any? = null,
    @Embedded
    var state_reason: Any? = null
)
