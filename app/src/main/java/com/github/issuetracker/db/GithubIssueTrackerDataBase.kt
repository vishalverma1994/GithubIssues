package com.github.issuetracker.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.github.issuetracker.db.converter.ListTypeConverter
import com.github.issuetracker.db.dao.GithubIssueTrackerDao
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity

@Database(
    entities = [GithubIssueTrackerEntity::class],
    version = 1
)
@TypeConverters(ListTypeConverter::class)
abstract class GithubIssueTrackerDataBase: RoomDatabase() {

    abstract fun getGithubIssueTrackerDao(): GithubIssueTrackerDao
}