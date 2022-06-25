package com.github.issuetracker.model

import com.google.gson.annotations.SerializedName

data class ReactionsData(
    @SerializedName("url")
    var reactions_url: String = "",
    var total_count: String = "",
    @SerializedName("+1")
    var plus_one: Int = -1,
    @SerializedName("-1")
    var minus_one: Int = -1,
    var laugh: Int = -1,
    var hooray: Int = -1,
    var confused: Int = -1,
    var heart: Int = -1,
    var rocket: Int = -1,
    var eyes: Int = -1
)
