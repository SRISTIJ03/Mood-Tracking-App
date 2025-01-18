package com.sristi.moodtracking

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var progressBar: ProgressBar

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        progressBar = findViewById(R.id.progressBar)
        val emailInput = findViewById<EditText>(R.id.editText2) // Assuming email input field exists
        val passwordInput = findViewById<EditText>(R.id.editText3) // Assuming password input field exists
        val signInButton = findViewById<Button>(R.id.sign_in_button)

        signInButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val email = emailInput.text.toString()
            val password = passwordInput.text.toString()

            // Validate credentials
            authenticate(email, password)
        }
    }

    private fun authenticate(email: String, password: String) {
        progressBar.visibility = View.GONE

        // Regex for email validation
        val emailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$".toRegex()

        // Check if email and password are valid
        if (!email.matches(emailRegex)) {
            showError("Invalid email format. Please enter a valid @gmail.com email.")
            return
        }

        if (password.length <= 5) {
            showError("Password must be at least 6 characters long.")
            return
        }

        // Save session data and proceed if valid
        saveSessionData("Welcome!", "dummy-token")
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun showError(message: String) {
        AlertDialog.Builder(this).setTitle("Error")
            .setMessage(message)
            .setPositiveButton(android.R.string.ok) { _, _ -> }
            .setIcon(android.R.drawable.ic_dialog_alert).show()
    }

    private fun saveSessionData(name: String?, token: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = preferences.edit()
        editor.putString("Name", name)
        editor.putString("Token", token)
        editor.apply()
    }
}
