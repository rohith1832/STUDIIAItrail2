package com.example.studiiaitrail2.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studiiaitrail2.R

class StudyPlannerFragment : Fragment() {

    // Views
    private lateinit var inputTopics: EditText
    private lateinit var inputDailyTime: EditText
    private lateinit var btnGenerate: Button
    private lateinit var recyclerViewTimetable: RecyclerView

    private lateinit var adapter: StudyPlannerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment (use your already created fragment_study_planner.xml)
        return inflater.inflate(R.layout.fragment_study_planner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize views
        inputTopics = view.findViewById(R.id.inputTopics)
        inputDailyTime = view.findViewById(R.id.inputDailyTime)
        btnGenerate = view.findViewById(R.id.btnGenerate)
        recyclerViewTimetable = view.findViewById(R.id.recyclerViewTimetable)

        // Set up RecyclerView
        adapter = StudyPlannerAdapter(emptyList())
        recyclerViewTimetable.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewTimetable.adapter = adapter

        // Button click to generate timetable
        btnGenerate.setOnClickListener {
            val topicsText = inputTopics.text.toString().trim()
            val dailyTimeText = inputDailyTime.text.toString().trim()

            if (topicsText.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter some topics.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (dailyTimeText.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter daily study time in minutes.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val topics = topicsText.split(",").map { it.trim() }.filter { it.isNotEmpty() }
            if (topics.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter valid topics.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val dailyMinutes = dailyTimeText.toIntOrNull()
            if (dailyMinutes == null || dailyMinutes <= 0) {
                Toast.makeText(requireContext(), "Please enter a valid positive number for daily study time.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val timetable = generateTimetable(topics, dailyMinutes)
            adapter.updateSessions(timetable)
        }
    }

    /**
     * Simple timetable generator that evenly distributes topics over days,
     * assigning the full daily study time to each day.
     * You can customize this logic as needed.
     */
    private fun generateTimetable(topics: List<String>, dailyMinutes: Int): List<StudySession> {
        val sessions = mutableListOf<StudySession>()

        // For simplicity, assign one topic per day, total days = number of topics
        topics.forEachIndexed { index, topic ->
            sessions.add(StudySession(dayNumber = index + 1, topics = listOf(topic), totalStudyMinutes = dailyMinutes))
        }

        return sessions
    }
}
