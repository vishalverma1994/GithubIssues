package com.github.issuetracker.utils

object Constants {

    //database name
    const val GITHUB_ISSUE_TRACKER_DATABASE_NAME = "github_issue_tracker_db"

    //api services constants
    const val BASE_URL = "https://api.github.com/"

    const val GITHUB_ISSUES_LIST_API = "repos/square/okhttp/issues"

    const val GITHUB_ISSUE_COMMENT_LIST_API = "$GITHUB_ISSUES_LIST_API/{issueNumber}/comments"

    //global constants
    const val INTERNET_CONNECTION_ERROR_MESSAGE = "Please check your internet connection."

    //constant arguments
    const val ARG_1 = "arg_1"
}