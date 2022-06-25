package com.github.issuetracker.model

import com.google.gson.annotations.SerializedName

data class User(
    var login: String = "",
    @SerializedName("id")
    var user_id: Long = -1,
    @SerializedName("node_id")
    var user_node_id: String = "",
    var avatar_url: String = "",
    var gravatar_id: String = "",
    @SerializedName("url")
    var user_url: String = "",
    @SerializedName("html_url")
    var user_html_url: String = "",
    var followers_url: String = "",
    var following_url: String = "",
    var gists_url: String = "",
    var starred_url: String = "",
    var subscriptions_url: String = "",
    var organizations_url: String = "",
    var repos_url: String = "",
    @SerializedName("events_url")
    var user_events_url: String = "",
    var received_events_url: String = "",
    var type: String = "",
    var site_admin: Boolean = false
)
