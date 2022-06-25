package com.github.issuetracker.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.github.issuetracker.databinding.AdapterGithubIssueTrackerBinding
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.extension.getTimeInUtcFormat

class GithubIssueTrackerAdapter(private val onItemClick: (Int) -> Unit) :
    ListAdapter<GithubIssueTrackerEntity, GithubIssueTrackerAdapter.ViewHolder>(MyDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            AdapterGithubIssueTrackerBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), parent.context
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindViews(getItem(position))
    }

    inner class ViewHolder(private val binding: AdapterGithubIssueTrackerBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onItemClick(absoluteAdapterPosition)
            }
        }

        fun bindViews(githubIssueTrackerEntity: GithubIssueTrackerEntity) {
            Glide.with(context).load(githubIssueTrackerEntity.user?.avatar_url.orEmpty())
                .apply(RequestOptions.circleCropTransform()).into(binding.ivUserProfile)

            binding.tvUserName.text = githubIssueTrackerEntity.user?.login.orEmpty()
            binding.tvTitle.text = githubIssueTrackerEntity.title.orEmpty()
            binding.tvDesc.text = githubIssueTrackerEntity.body.orEmpty()
            binding.tvUpdatedAt.text = githubIssueTrackerEntity.updated_at?.getTimeInUtcFormat().orEmpty()
        }
    }
}

class MyDiffUtil : DiffUtil.ItemCallback<GithubIssueTrackerEntity>() {
    override fun areItemsTheSame(oldItem: GithubIssueTrackerEntity, newItem: GithubIssueTrackerEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: GithubIssueTrackerEntity, newItem: GithubIssueTrackerEntity): Boolean {
        return oldItem == newItem
    }

}