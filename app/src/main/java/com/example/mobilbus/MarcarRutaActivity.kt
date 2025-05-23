package com.example.mobilbus

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import org.json.JSONException
import org.json.JSONObject
import java.util.Calendar
import java.util.Date
import java.util.Locale

class MarcarRutaActivity : androidx.appcompat.app.AppCompatActivity() {

    private lateinit var tabLayout: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marcar_ruta)

        tabLayout = findViewById(R.id.tabLayout)

        val btnRegistrarOrigen = findViewById<MaterialButton>(R.id.btnRegistrarOrigen)
        val btnRegistrarDestino = findViewById<MaterialButton>(R.id.btnRegistrarDestino)
        val spinnerOrigen = findViewById<Spinner>(R.id.spinnerOrigen)
        val spinnerDestino = findViewById<Spinner>(R.id.spinnerDestino)

        // ocultar botones
        //btnRegistrarOrigen.visibility = View.GONE
        btnRegistrarDestino.visibility = View.GONE

        // Cargar datos en Spinner
        val ciudades = listOf("Seleccionar","Lima", "Huánuco", "Huancayo", "Cusco", "Arequipa", "Trujillo", "Piura")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, ciudades)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerOrigen.adapter = adapter
        spinnerDestino.adapter = adapter

        // Campos de fecha y hora
        val edtFecha: EditText = findViewById(R.id.inputFecha)
        val edtHora: EditText = findViewById(R.id.inputHora)

        // Obtener la fecha actual del sistema
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        edtFecha.setText(currentDate)

        // Obtener la hora actual del sistema
        val currentTime = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
        edtHora.setText(currentTime)

        // Fecha - DatePicker
        edtFecha.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                this, { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    edtFecha.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Hora - TimePicker
        edtHora.setOnClickListener {
            val calendar = Calendar.getInstance()
            val timePicker = TimePickerDialog(
                this, { view, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    edtHora.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }

        tabLayout.addTab(tabLayout.newTab().setText("Inicio"))
        tabLayout.addTab(tabLayout.newTab().setText("Fin"))

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        //Toast.makeText(this@MarcarRutaActivity, "Pestaña Inicio seleccionada", Toast.LENGTH_SHORT).show()
                        btnRegistrarOrigen.visibility = View.VISIBLE
                        btnRegistrarDestino.visibility = View.GONE

                    }
                    1 -> {

                        //Toast.makeText(this@MarcarRutaActivity, "Pestaña Fin seleccionada", Toast.LENGTH_SHORT).show()
                        btnRegistrarDestino.visibility = View.VISIBLE
                        btnRegistrarOrigen.visibility = View.GONE

                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

    }

    // registrar origen en marcar ruta
    fun RegistrarInicioRuta(view: View){

        val spinnerOrigen = findViewById<Spinner>(R.id.spinnerOrigen)
        val spinnerDestino = findViewById<Spinner>(R.id.spinnerDestino)
        val inputFecha = findViewById<EditText>(R.id.inputFecha)
        val inputHora = findViewById<EditText>(R.id.inputHora)
        val inputPlaca = findViewById<EditText>(R.id.inputPlacaVehiculo)
        val inputConductor = findViewById<EditText>(R.id.inputConductor)



        // Obtener los valores reales
        val origen = spinnerOrigen.selectedItem.toString()
        val destino = spinnerDestino.selectedItem.toString()

        // Convertir fecha a formato yyyy-MM-dd
        val formatoEntrada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formatoSalida = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaDate = formatoEntrada.parse(inputFecha.text.toString())
        val fechaFormateada = fechaDate?.let { formatoSalida.format(it) } ?: inputFecha.text.toString()

        val hora = inputHora.text.toString()
        val placa = inputPlaca.text.toString()
        val dniconductor = inputConductor.text.toString()

        var url = "https://h9ul1f7bmb.execute-api.us-east-1.amazonaws.com/v1/inicioruta"

        val jsonObject = JSONObject()
        try {
            jsonObject.put("origen", origen)
            jsonObject.put("destino", destino)
            jsonObject.put("fecha", fechaFormateada)
            jsonObject.put("hora", hora)
            jsonObject.put("placa", placa)
            jsonObject.put("dniconductor", dniconductor)

            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener {
                    response ->  Log.i("=====>", "Registro Guardado Correctamente")
                    Toast.makeText(this@MarcarRutaActivity, "Registro Guardado Correctamente", Toast.LENGTH_SHORT).show()
                    Log.i("JSON Enviado", jsonObject.toString())

                    //limpiar campos luego de insertar
                    spinnerOrigen.setSelection(0)
                    spinnerDestino.setSelection(0)
                    inputFecha.text.clear()
                    inputHora.text.clear()
                    inputPlaca.text.clear()
                    inputConductor.text.clear()
                },
                Response.ErrorListener {
                        error : VolleyError -> Log.i("=====>", error.message ?: "Error en Api")
                }
            ){}
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(jsonObjReq)

        } catch (e: JSONException){
            Log.i("=====>", e.message ?: "Error Json")
        }

    }

    // registrar destino en marcar ruta

    fun RegistrarFinRuta(view: View){

        val spinnerOrigen = findViewById<Spinner>(R.id.spinnerOrigen)
        val spinnerDestino = findViewById<Spinner>(R.id.spinnerDestino)
        val inputFecha = findViewById<EditText>(R.id.inputFecha)
        val inputHora = findViewById<EditText>(R.id.inputHora)
        val inputPlaca = findViewById<EditText>(R.id.inputPlacaVehiculo)
        val inputConductor = findViewById<EditText>(R.id.inputConductor)



        // Obtener los valores reales
        val origen = spinnerOrigen.selectedItem.toString()
        val destino = spinnerDestino.selectedItem.toString()

        // Convertir fecha a formato yyyy-MM-dd
        val formatoEntrada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formatoSalida = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val fechaDate = formatoEntrada.parse(inputFecha.text.toString())
        val fechaFormateada = fechaDate?.let { formatoSalida.format(it) } ?: inputFecha.text.toString()

        val hora = inputHora.text.toString()
        val placa = inputPlaca.text.toString()
        val dniconductor = inputConductor.text.toString()

        var url = "https://h9ul1f7bmb.execute-api.us-east-1.amazonaws.com/v1/finruta"

        val jsonObject = JSONObject()
        try {
            jsonObject.put("origen", origen)
            jsonObject.put("destino", destino)
            jsonObject.put("fecha", fechaFormateada)
            jsonObject.put("hora", hora)
            jsonObject.put("placa", placa)
            jsonObject.put("dniconductor", dniconductor)

            val jsonObjReq = object : JsonObjectRequest(
                Request.Method.POST, url, jsonObject,
                Response.Listener {
                        response ->  Log.i("=====>", "Registro Guardado Correctamente")
                    Toast.makeText(this@MarcarRutaActivity, "Registro Guardado Correctamente", Toast.LENGTH_SHORT).show()
                    Log.i("JSON Enviado", jsonObject.toString())

                    //limpiar campos luego de insertar
                    spinnerOrigen.setSelection(0)
                    spinnerDestino.setSelection(0)
                    inputFecha.text.clear()
                    inputHora.text.clear()
                    inputPlaca.text.clear()
                    inputConductor.text.clear()
                },
                Response.ErrorListener {
                        error : VolleyError -> Log.i("=====>", error.message ?: "Error en Api")
                }
            ){}
            val requestQueue = Volley.newRequestQueue(this)
            requestQueue.add(jsonObjReq)

        } catch (e: JSONException){
            Log.i("=====>", e.message ?: "Error Json")
        }

    }

}