package com.example.studiiaitrail2.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.studiiaitrail2.R


class StudyPlannerAdapter(
    private var sessions: List<StudySession>
) : RecyclerView.Adapter<StudyPlannerAdapter.SessionViewHolder>() {

    fun updateSessions(newSessions: List<StudySession>) {
        sessions = newSessions
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_study_session, parent, false)
        return SessionViewHolder(view)
    }

    override fun getItemCount() = sessions.size

    override fun onBindViewHolder(holder: SessionViewHolder, position: Int) {
        holder.bind(sessions[position])
    }

    inner class SessionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val dayText: TextView = itemView.findViewById(R.id.textDay)
        private val topicsText: TextView = itemView.findViewById(R.id.textTopics)
        private val timeText: TextView = itemView.findViewById(R.id.textStudyTime)

        @SuppressLint("SetTextI18n")
        fun bind(session: StudySession) {
            dayText.text = "Day ${session.dayNumber}"
            topicsText.text = session.topics.joinToString(", ")
            timeText.text = "${session.totalStudyMinutes} minutes"
        }
    }
}
