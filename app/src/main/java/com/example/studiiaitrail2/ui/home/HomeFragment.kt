package com.example.studiiaitrail2.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studiiaitrail2.R
import com.example.studiiaitrail2.SearchItem
import com.example.studiiaitrail2.ui.RetrofitClient
import com.example.studiiaitrail2.ui.SearchAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.view.KeyEvent
import androidx.navigation.fragment.findNavController
import com.example.studiiaitrail2.ui.FlashcardsFragment

class HomeFragment : Fragment() {

    private lateinit var searchEditText: EditText
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SearchAdapter
    private lateinit var searchResultsContainer: View


    companion object {
        const val API_KEY = "AIzaSyBk5Oz6MCHpc2K_NQVfXu3WtA_aRWxZwb0"
        const val CX = "36cefe802456e433e"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        searchResultsContainer = root.findViewById(R.id.searchResultsContainer)

        searchEditText = root.findViewById(R.id.searchEditText)
        recyclerView = root.findViewById(R.id.searchResultsRecyclerView)

        adapter = SearchAdapter(emptyList())
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        searchResultsContainer.isVisible = false

        searchEditText.setOnEditorActionListener { _, actionId, event ->
            val isEnterKey = (event?.keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_DOWN)
            val isSearchAction = (actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE)

            if (isEnterKey || isSearchAction) {
                val query = searchEditText.text.toString().trim()
                if (query.isNotEmpty()) {
                    performSearch(query)
                }
                true
            } else {
                false
            }
        }
        root.findViewById<View>(R.id.card_quiz).setOnClickListener {
            findNavController().navigate(R.id.quizFragment)

        }

        root.findViewById<View>(R.id.card_study_planner).setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_studyPlannerFragment)
        }

        root.findViewById<View>(R.id.card_flashcards).setOnClickListener {

            findNavController().navigate(R.id.flashcardsFragment)
        }
        root.findViewById<View>(R.id.card_progress_tracker).setOnClickListener {
            findNavController().navigate(R.id.progressTrackerFragment)
        }

        root.findViewById<View>(R.id.card_exam_date).setOnClickListener {
            Toast.makeText(requireContext(), "Exam Date clicked!", Toast.LENGTH_SHORT).show()
            // Navigate or show exam date UI
        }

        return root
    }

    private fun performSearch(query: String) {
        lifecycleScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    RetrofitClient.apiService.search(
                        apiKey = API_KEY,
                        cx = CX,
                        query = query
                    )
                }
                val results = response.items ?: emptyList()
                searchResultsContainer.isVisible = results.isNotEmpty()  // Show container only if results
                adapter.updateData(results)
            } catch (e: Exception) {
                searchResultsContainer.isVisible = false  // Hide container on error
                Toast.makeText(requireContext(), "Search failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

}
