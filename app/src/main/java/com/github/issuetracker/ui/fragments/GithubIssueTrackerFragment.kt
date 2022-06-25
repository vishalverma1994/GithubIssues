package com.github.issuetracker.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.issuetracker.R
import com.github.issuetracker.databinding.FragmentGithubIssueTrackerBinding
import com.github.issuetracker.db.entity.GithubIssueTrackerEntity
import com.github.issuetracker.ui.adapter.GithubIssueTrackerAdapter
import com.github.issuetracker.utils.Constants
import com.github.issuetracker.utils.Status
import com.github.issuetracker.viewmodel.GithubIssueTrackerViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GithubIssueTrackerFragment : Fragment() {

    private val githubIssueTrackerViewModel: GithubIssueTrackerViewModel by activityViewModels()
    private lateinit var binding: FragmentGithubIssueTrackerBinding
    private var githubIssuesTrackerList = emptyList<GithubIssueTrackerEntity>()
    private lateinit var githubIssueTrackerAdapter: GithubIssueTrackerAdapter

    companion object {
        private val TAG = GithubIssueTrackerFragment::class.simpleName
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentGithubIssueTrackerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerGithubIssuesFromDB()
        observeGithubIssueTrackerLiveData()
        setGithubIssueAdapter()
        fetchGithubIssueFromNetwork()
    }

    private fun observerGithubIssuesFromDB() {
        githubIssueTrackerViewModel.getGithubIssuesFromDB.observe(viewLifecycleOwner) { githubIssuesList ->
            Log.e(TAG, Gson().toJson(githubIssuesList))
            githubIssuesList?.let {
                if (it.isNotEmpty()) {
                    binding.rvGithubIssues.visibility = View.VISIBLE
                    binding.tvEmptyStatement.visibility = View.GONE
                    githubIssuesTrackerList = it.reversed()
                    githubIssueTrackerAdapter.submitList(githubIssuesTrackerList)
                } else{
                    binding.rvGithubIssues.visibility = View.GONE
                    binding.tvEmptyStatement.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun observeGithubIssueTrackerLiveData() {
        githubIssueTrackerViewModel.githubIssueTrackerLiveData.observe(viewLifecycleOwner) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    private fun setGithubIssueAdapter() {
        binding.rvGithubIssues.apply {
            layoutManager = LinearLayoutManager(requireContext())
            githubIssueTrackerAdapter = GithubIssueTrackerAdapter(::onItemClick)
            adapter = githubIssueTrackerAdapter
            addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
        }
    }

    private fun onItemClick(position: Int) {
        if (githubIssuesTrackerList.isNotEmpty()) {
            val bundle = bundleOf(Constants.ARG_1 to githubIssuesTrackerList[position].id)
            findNavController().navigate(R.id.action_myGithubIssuesFragment_to_myGithubIssueDetailFragment, bundle)
        }
    }

    private fun fetchGithubIssueFromNetwork() {
        githubIssueTrackerViewModel.fetchGithubIssueFromNetwork()
    }
}