package com.example.mobilbus

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val contactLink = findViewById<TextView>(R.id.contactLink)

        val correctUsername = "admin"
        val correctPassword = "admin123"

        loginButton.setOnClickListener {
            val enteredUser = usernameInput.text.toString()
            val enteredPass = passwordInput.text.toString()

            if (enteredUser == correctUsername && enteredPass == correctPassword) {
                Toast.makeText(this, "¡Bienvenido, $enteredUser!", Toast.LENGTH_SHORT).show()

                val intent = Intent(this, MenuPrincipal::class.java)
                startActivity(intent)

                finish()

            } else {
                Toast.makeText(this, "Usuario o contraseña incorrectos.", Toast.LENGTH_SHORT).show()
                passwordInput.text.clear()
            }
        }

        contactLink.setOnClickListener {
            Toast.makeText(this, "Contactando al administrador...", Toast.LENGTH_SHORT).show()
        }
    }
}