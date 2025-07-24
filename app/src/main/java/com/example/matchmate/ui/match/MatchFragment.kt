package com.example.matchmate.ui.match

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.matchmate.R
import com.example.matchmate.data.db.MatchDatabase
import com.example.matchmate.data.model.Match
import com.example.matchmate.data.model.MatchStatus
import com.example.matchmate.data.repository.MatchRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.RecyclerView

class MatchFragment : Fragment() {

    private lateinit var viewModel: MatchViewModel
    private lateinit var adapter: MatchAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerMatches)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val dao = MatchDatabase.getDatabase(requireContext()).matchDao()
        val repository = MatchRepository(dao)
        val factory = MatchViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[MatchViewModel::class.java]

        adapter = MatchAdapter(emptyList()) { match, status ->
            viewModel.updateMatchStatus(match, status)
        }
        recyclerView.adapter = adapter

        viewModel.matches.observe(viewLifecycleOwner) { list ->
            adapter.updateData(list)
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            error?.let {
                // Show toast or dialog
            }
        }

        // Fetch from API only once (not from DB)
        viewModel.fetchMatches()
    }
}
