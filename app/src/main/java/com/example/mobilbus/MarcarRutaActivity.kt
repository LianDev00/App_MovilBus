package com.example.mobilbus

import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout // Importa TabLayout

class MarcarRutaActivity : androidx.appcompat.app.AppCompatActivity() {

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcar_ruta)

        tabLayout = findViewById(R.id.tabLayout)

        val inputOrigenDestino = findViewById<EditText>(R.id.inputOrigenDestino)
        val btnRegistrar = findViewById<MaterialButton>(R.id.btnRegistrar)

        tabLayout.addTab(tabLayout.newTab().setText("Inicio"))
        tabLayout.addTab(tabLayout.newTab().setText("Fin"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        Toast.makeText(this@MarcarRutaActivity, "Pestaña Inicio seleccionada", Toast.LENGTH_SHORT).show()
                        inputOrigenDestino.hint = "Origen"
                    }
                    1 -> {

                        Toast.makeText(this@MarcarRutaActivity, "Pestaña Fin seleccionada", Toast.LENGTH_SHORT).show()
                        inputOrigenDestino.hint = "Destino"
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })


        tabLayout.getTabAt(0)?.select()

        btnRegistrar.setOnClickListener {
            val selectedTabPosition = tabLayout.selectedTabPosition
            val modo = if (selectedTabPosition == 0) "Inicio" else "Fin"

            val origenDestino = inputOrigenDestino.text.toString()

            Toast.makeText(this, "Registrando en modo $modo: Origen/Destino: $origenDestino", Toast.LENGTH_LONG).show()
        }
    }
}