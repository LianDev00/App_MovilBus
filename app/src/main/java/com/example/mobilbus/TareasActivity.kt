package com.example.mobilbus

import android.os.Bundle
import android.view.View // Importa View para usar View.VISIBLE y View.GONE
import android.widget.Button // Importa Button para el botón "Lista de Tareas"
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView // Importa CardView (aunque no lo ocultaremos completamente)
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout

class TareasActivity : AppCompatActivity() {
    private lateinit var tabLayout: TabLayout
    private lateinit var btnListaTareas: Button // <--- Nueva variable para el botón "Lista de Tareas"
    private lateinit var btnRegistrar: MaterialButton // Referencia al botón "Registrar"
    private lateinit var inputTipoTarea: EditText // Referencia al EditText "Tipo o Tarea"
    // No necesitamos el CardView como variable de clase si solo controlamos los botones internos.
    // Si necesitas controlar otros elementos del formulario, podrías hacerlo.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tareas)

        enableEdgeToEdge() // Puedes mantener esto o quitarlo
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        tabLayout = findViewById(R.id.tabLayout)
        btnListaTareas = findViewById(R.id.btnListaTareas) // <--- Obtener referencia al botón "Lista de Tareas"
        btnRegistrar = findViewById(R.id.btnRegistrar)
        inputTipoTarea = findViewById(R.id.inputTipoTarea)

        // Asegúrate de que las pestañas estén definidas. Si ya las tienes en XML, esto podría ser redundante.
        if (tabLayout.tabCount == 0) {
            tabLayout.addTab(tabLayout.newTab().setText("Registrar"))
            tabLayout.addTab(tabLayout.newTab().setText("Reportar"))
        }

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> { // Pestaña "Registrar" seleccionada
                        Toast.makeText(this@TareasActivity, "Pestaña Registrar seleccionada", Toast.LENGTH_SHORT).show()
                        inputTipoTarea.hint = "Registrar"

                        // Mostrar el botón "Registrar" y ocultar "Lista de Tareas"
                        btnRegistrar.visibility = View.VISIBLE
                        btnListaTareas.visibility = View.GONE

                        // Opcional: ajustar visibilidad de otros campos si "Reportar"
                        // requiere menos campos o campos diferentes
                        findViewById<EditText>(R.id.inputPlacaVehiculo).visibility = View.VISIBLE
                        findViewById<EditText>(R.id.inputConductor).visibility = View.VISIBLE
                        findViewById<EditText>(R.id.inputFechaLimite).visibility = View.VISIBLE
                        // Puedes ajustar los hints o textos de los TextViews si es necesario.

                    }
                    1 -> { // Pestaña "Reportar" seleccionada
                        Toast.makeText(this@TareasActivity, "Pestaña Reportar seleccionada", Toast.LENGTH_SHORT).show()
                        inputTipoTarea.hint = "Reportar" // El EditText sigue siendo visible, solo cambia el hint

                        // Ocultar el botón "Registrar" y mostrar "Lista de Tareas"
                        btnRegistrar.visibility = View.GONE
                        btnListaTareas.visibility = View.VISIBLE

                        // Opcional: Si los campos de "Placa", "Conductor", "Fecha Límite" no aplican para "Reportar"
                        // puedes ocultarlos. Si sí aplican, déjalos visibles.
                        // Según la imagen original de "Tareas", parece que "Reportar" podría mostrar una lista,
                        // por lo que estos campos podrían no ser relevantes. Decídelo tú.
                        findViewById<EditText>(R.id.inputPlacaVehiculo).visibility = View.VISIBLE
                        findViewById<EditText>(R.id.inputConductor).visibility = View.VISIBLE
                        findViewById<EditText>(R.id.inputFechaLimite).visibility = View.VISIBLE
                        // Si ocultas los EditTexts, también deberías ocultar los TextViews que los etiquetan.
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // No necesitas hacer nada aquí para este caso.
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // No necesitas hacer nada aquí para este caso.
            }
        })

        // Configuración inicial al cargar la actividad
        tabLayout.getTabAt(0)?.select() // Esto disparará onTabSelected para la posición 0

        // Asegúrate de que los elementos estén en el estado correcto al inicio
        // (Aunque el select() de arriba ya lo debería hacer)
        btnRegistrar.visibility = View.VISIBLE
        btnListaTareas.visibility = View.GONE
        // Los campos de formulario serán visibles por defecto si no los ocultas en la pestaña 0
        findViewById<EditText>(R.id.inputPlacaVehiculo).visibility = View.VISIBLE
        findViewById<EditText>(R.id.inputConductor).visibility = View.VISIBLE
        findViewById<EditText>(R.id.inputFechaLimite).visibility = View.VISIBLE
        inputTipoTarea.hint = "Registrar"


        btnRegistrar.setOnClickListener {
            val selectedTabPosition = tabLayout.selectedTabPosition
            val modo = if (selectedTabPosition == 0) "Registrar" else "Reportar"
            val tipoTarea = inputTipoTarea.text.toString()

            Toast.makeText(this, "Acción en modo $modo: Tipo de Tarea: $tipoTarea", Toast.LENGTH_LONG).show()
            // Lógica para registrar.
        }

        btnListaTareas.setOnClickListener {
            Toast.makeText(this, "Navegando a la Lista de Tareas", Toast.LENGTH_SHORT).show()
            // Aquí la acción para ir a la lista de tareas.
            // val intent = Intent(this, ListaDeTareasActivity::class.java)
            // startActivity(intent)
        }
    }
}