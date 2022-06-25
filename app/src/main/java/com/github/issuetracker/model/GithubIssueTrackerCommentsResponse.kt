package com.github.issuetracker.model

data class GithubIssueTrackerCommentsResponse(
    var url: String = "",
    var html_url: String = "",
    var issue_url: String = "",
    var id: Long = -1,
    var node_id: String = "",
    var user: User? = null,
    var created_at: String = "",
    var updated_at: String = "",
    var author_association: String = "",
    var body: String? = "",
    var reactions: ReactionsData? = null,
    var performed_via_github_app: Any? = null
)
