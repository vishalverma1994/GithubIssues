package com.github.issuetracker.model

import androidx.room.Embedded
import com.google.gson.annotations.SerializedName

data class PullRequestData(
    @SerializedName("url")
    var pull_request_url: String = "",
    @SerializedName("html_url")
    var pull_request_html_url: String = "",
    var diff_url: String = "",
    var patch_url: String = "",
    @Embedded
    var merged_at: Any? = null
)
