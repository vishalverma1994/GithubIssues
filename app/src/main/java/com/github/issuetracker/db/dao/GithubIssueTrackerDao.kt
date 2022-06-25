package com.github.issuetracker.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity

@Dao
interface GithubIssueTrackerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubIssueTracker(githubIssueTrackerEntity: List<GithubIssueTrackerEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubIssueTrackerEntity(githubIssueTrackerEntity: GithubIssueTrackerEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateGithubIssueTracker(githubIssueTrackerEntity: GithubIssueTrackerEntity): Int

    @Query("Select * FROM github_issue_tracker_table")
    fun getAllGithubIssueTrackerList(): LiveData<List<GithubIssueTrackerEntity>>

    @Query("Select * FROM github_issue_tracker_table")
    fun getAllTestGithubIssueTrackerList(): List<GithubIssueTrackerEntity>

    @Query("SELECT * FROM github_issue_tracker_table where id = :id")
    fun getGithubIssueTrackerDetail(id: Long): LiveData<GithubIssueTrackerEntity>

    @Delete
    suspend fun deleteGithubIssueTracker(githubIssueTrackerEntity: GithubIssueTrackerEntity)

    @Query("DELETE FROM github_issue_tracker_table WHERE id = :id")
    fun deleteGithubIssueTrackerById(id: Int): Int
}