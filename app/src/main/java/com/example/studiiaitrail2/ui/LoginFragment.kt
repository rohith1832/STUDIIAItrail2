package com.example.studiiaitrail2.ui

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.studiiaitrail2.R

class LoginFragment : Fragment() {

    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var btnAction: Button
    private lateinit var textToggle: TextView
    private lateinit var textModeTitle: TextView

    private var isLoginMode = true

    private val PREFS_NAME = "user_prefs"
    private val KEY_EMAIL = "email"
    private val KEY_PASSWORD = "password"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)
        editEmail = view.findViewById(R.id.editEmail)
        editPassword = view.findViewById(R.id.editPassword)
        btnAction = view.findViewById(R.id.btnAction)
        textToggle = view.findViewById(R.id.textToggle)
        textModeTitle = view.findViewById(R.id.textModeTitle)

        btnAction.setOnClickListener { onActionClicked() }
        textToggle.setOnClickListener { toggleMode() }

        updateUIForMode()

        return view
    }

    private fun onActionClicked() {
        val email = editEmail.text.toString().trim()
        val password = editPassword.text.toString()

        // Basic validation
        if (!isValidEmail(email)) {
            Toast.makeText(requireContext(), "Please enter a valid email", Toast.LENGTH_SHORT).show()
            return
        }
        if (password.length < 6) {
            Toast.makeText(requireContext(), "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
            return
        }

        if (isLoginMode) {
            login(email, password)
        } else {
            signup(email, password)
        }
    }

    private fun login(email: String, password: String) {
        val prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val storedEmail = prefs.getString(KEY_EMAIL, null)
        val storedPassword = prefs.getString(KEY_PASSWORD, null)

        if (storedEmail == null || storedPassword == null) {
            Toast.makeText(requireContext(), "No user found, please sign up first", Toast.LENGTH_SHORT).show()
            return
        }

        if (email == storedEmail && password == storedPassword) {
            findNavController().navigate(R.id.action_loginFragment_to_nav_home)

            // TODO: Proceed to next fragment or activity
        } else {
            Toast.makeText(requireContext(), "Invalid credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun signup(email: String, password: String) {
        val prefs = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor = prefs.edit()

        // You could check if user already exists and handle accordingly
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_PASSWORD, password)
        editor.apply()

        Toast.makeText(requireContext(), "Sign up successful! Please log in.", Toast.LENGTH_SHORT).show()
        // Switch to login mode after signup
        isLoginMode = true
        updateUIForMode()
        clearInputs()
    }

    private fun toggleMode() {
        isLoginMode = !isLoginMode
        updateUIForMode()
        clearInputs()
    }

    private fun updateUIForMode() {
        if (isLoginMode) {
            textModeTitle.text = "Login"
            btnAction.text = "Login"
            textToggle.text = "Don't have an account? Sign up"
        } else {
            textModeTitle.text = "Sign Up"
            btnAction.text = "Sign Up"
            textToggle.text = "Already have an account? Log in"
        }
    }

    private fun clearInputs() {
        editEmail.text.clear()
        editPassword.text.clear()
    }

    private fun isValidEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}
