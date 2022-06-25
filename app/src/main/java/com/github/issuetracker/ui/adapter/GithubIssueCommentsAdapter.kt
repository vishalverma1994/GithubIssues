package com.github.issuetracker.ui.adapter

import android.content.Context
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.issuetracker.databinding.AdapterGithubIssueCommentsBinding
import com.github.issuetracker.extension.getTimeInUtcFormat
import com.github.issuetracker.model.GithubIssueTrackerCommentsResponse

class GithubIssueCommentsAdapter :
    ListAdapter<GithubIssueTrackerCommentsResponse, GithubIssueCommentsAdapter.ViewHolder>(CommentsDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterGithubIssueCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    inner class ViewHolder(private val binding: AdapterGithubIssueCommentsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindViews(commentsResponse: GithubIssueTrackerCommentsResponse) {
                Glide.with(context).load(commentsResponse.user?.avatar_url.orEmpty())
                    .apply(RequestOptions.circleCropTransform()).into(binding.ivUserProfile)
                binding.tvUserName.text = commentsResponse.user?.login.orEmpty()
                binding.tvComment.apply {
                    text = commentsResponse.body
                    movementMethod = LinkMovementMethod.getInstance()
                }
                binding.tvCommentUpdatedAt.text = commentsResponse.updated_at.getTimeInUtcFormat()
            }
    }
}

class CommentsDiffUtil : DiffUtil.ItemCallback<GithubIssueTrackerCommentsResponse>() {
    override fun areItemsTheSame(
        oldItem: GithubIssueTrackerCommentsResponse,
        newItem: GithubIssueTrackerCommentsResponse
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: GithubIssueTrackerCommentsResponse,
        newItem: GithubIssueTrackerCommentsResponse
    ): Boolean {
        return oldItem == newItem
    }

}