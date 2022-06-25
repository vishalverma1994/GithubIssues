package com.github.issuetracker.model

data class LabelsData(
    var id: Long = -1,
    var node_id: String = "",
    var url: String = "",
    var name: String = "",
    var color: String = "",
    var default: Boolean = false,
    var description: String = ""
)
