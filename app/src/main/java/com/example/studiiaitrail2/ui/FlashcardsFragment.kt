package com.example.studiiaitrail2.ui


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.studiiaitrail2.R

class FlashcardsFragment : Fragment() {

    private lateinit var descriptionTextView: TextView

    // Example DBMS introduction paragraph
    private val dbmsIntro = """
        Database Management System (DBMS) is software that allows users to define, create, maintain, and control access to databases. 
        It provides a systematic and efficient way to manage data and supports multiple users simultaneously. DBMS ensures data integrity, security, and consistency while enabling easy data retrieval and management.
    """.trimIndent()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_flashcards, container, false)
        descriptionTextView = view.findViewById(R.id.textDescription)
        descriptionTextView.text = dbmsIntro  // Set DBMS introduction text here
        return view
    }
}
