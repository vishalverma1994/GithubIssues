package com.github.issuetracker.ui.fragments

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.issuetracker.databinding.FragmentGithubIssueTrackerDetailBinding
import com.github.issuetracker.ui.adapter.GithubIssueCommentsAdapter
import com.github.issuetracker.utils.Constants
import com.github.issuetracker.utils.Status
import com.github.issuetracker.viewmodel.GithubIssueTrackerViewModel

class GithubIssueTrackerDetailFragment : Fragment() {

    private val githubIssueTrackerViewModel: GithubIssueTrackerViewModel by activityViewModels()
    private lateinit var binding: FragmentGithubIssueTrackerDetailBinding
    private lateinit var githubIssueCommentsAdapter: GithubIssueCommentsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGithubIssueTrackerDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        extractArguments()
        observeGithubIssueCommentsLiveData()
        setGithubIssueCommentsAdapter()
    }

    private fun extractArguments() {
        fetchGithubIssueTrackerDetailFromDB(requireArguments().getLong(Constants.ARG_1))
    }

    private fun fetchGithubIssueTrackerDetailFromDB(id: Long) {
        githubIssueTrackerViewModel.getGithubIssueDetailFromDB(id)
            .observe(viewLifecycleOwner) { res ->
                res?.let { githubIssueTrackerDetail ->
                    binding.tvTitle.text = githubIssueTrackerDetail.title.orEmpty()
                    binding.tvDesc.apply {
                        text = githubIssueTrackerDetail.body.orEmpty()
                        movementMethod = LinkMovementMethod.getInstance()
                    }
                    fetchCommentsFromNetwork(githubIssueTrackerDetail.number)
                }
            }
    }

    private fun fetchCommentsFromNetwork(issueNumber: Long) {
        githubIssueTrackerViewModel.fetchCommentsFromNetwork(issueNumber)
    }

    private fun observeGithubIssueCommentsLiveData() {
        githubIssueTrackerViewModel.githubIssueTrackerCommentsLiveData.observe(viewLifecycleOwner) { it ->
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    githubIssueCommentsAdapter.submitList(it.data)
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setGithubIssueCommentsAdapter() {
        binding.rvGithubIssuesComments.apply {
            layoutManager = LinearLayoutManager(requireContext())
            githubIssueCommentsAdapter = GithubIssueCommentsAdapter()
            adapter = githubIssueCommentsAdapter
            isNestedScrollingEnabled = false
        }
    }
}