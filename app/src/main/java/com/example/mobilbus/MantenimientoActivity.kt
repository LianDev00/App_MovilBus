package com.example.mobilbus

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mobilbus.databinding.ActivityMantenimientoBinding
import com.example.mobilbus.databinding.ActivityMenuPrincipalBinding

class MantenimientoActivity : AppCompatActivity() {

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mantenimiento)
        val imageBtnConductor: ImageView = findViewById(R.id.imageBtnConductor)
        val imageBtnSupervisor: ImageView = findViewById(R.id.imageBtnSupervisor)
        val imageBtnAdministrador: ImageView = findViewById(R.id.imageBtnAdministrador)


        imageBtnConductor.setOnClickListener {
            val intent = Intent(this, ConductoresActivity::class.java)
            startActivity(intent)
        }

        imageBtnSupervisor.setOnClickListener {
            val intent = Intent(this, SupervisoresActivity::class.java)
            startActivity(intent)
        }

        imageBtnAdministrador.setOnClickListener {
            val intent = Intent(this, AdministradoresActivity::class.java)
            startActivity(intent)
        }
    }
}