package com.example.mobilbus

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RegistrarFragment : Fragment(R.layout.fragment_registrar) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_registrar, container, false)

        // Spinner tipo tarea
        val spinner: Spinner = rootView.findViewById(R.id.spinnerTipoTarea)
        val tipotarea = listOf("Seleccionar","Mantenimiento", "Documentario", "Cubrir ruta Alterna")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, tipotarea)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        // Campos de fecha
        val inputFechareregistro: EditText = rootView.findViewById(R.id.inputFecharegistro)
        val inputFecharelimite: EditText = rootView.findViewById(R.id.inputFecharelimite)

        // Obtener la fecha actual del sistema
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        inputFecharelimite.setText(currentDate)
        inputFechareregistro.setText(currentDate)

        // Fecha registro - DatePicker
        inputFechareregistro.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(), { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    inputFechareregistro.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Fecha limite - DatePicker
        inputFecharelimite.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(), { view, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    inputFecharelimite.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Botón Registrar
        val btnRegistrar: Button = rootView.findViewById(R.id.btnRegistrarTarea)
        btnRegistrar.setOnClickListener {
            // accion Registrar
            val inptipotarea = rootView.findViewById<Spinner>(R.id.spinnerTipoTarea)
            val inpdetalletarea = rootView.findViewById<EditText>(R.id.inputDetalle)
            val inpplaca = rootView.findViewById<EditText>(R.id.inputPlacavehiculo)
            val inpdniconductor = rootView.findViewById<EditText>(R.id.inputdniconductor)
            val inpfecharegistro = rootView.findViewById<EditText>(R.id.inputFecharegistro)
            val inpfechalimite = rootView.findViewById<EditText>(R.id.inputFecharelimite)


            // Obtener los valores reales

            val tipotarea = inptipotarea.selectedItem.toString()
            val detalletarea = inpdetalletarea.text.toString()
            val placa = inpplaca.text.toString()
            val dniconductor = inpdniconductor.text.toString()
            val estado = "Pendiente"

            // Convertir fecha a formato yyyy-MM-dd
            val formatoEntrada = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
            val formatoSalida = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

            val fechaDateregistro = formatoEntrada.parse(inpfecharegistro.text.toString())
            val fecharegistroformat = fechaDateregistro?.let { formatoSalida.format(it) } ?: inpfecharegistro.text.toString()

            val fechaDatelimite = formatoEntrada.parse(inpfechalimite.text.toString())
            val fecharelimiteformat = fechaDatelimite?.let { formatoSalida.format(it) } ?: inpfechalimite.text.toString()

            var url = "https://h9ul1f7bmb.execute-api.us-east-1.amazonaws.com/v1/tarea"

            val jsonObject = JSONObject()
            try {
                jsonObject.put("tipotarea", tipotarea)
                jsonObject.put("detalletarea", detalletarea)
                jsonObject.put("placa", placa)
                jsonObject.put("dniconductor", dniconductor)
                jsonObject.put("fecharegistro", fecharegistroformat)
                jsonObject.put("fechalimite", fecharelimiteformat)
                jsonObject.put("estado", estado)

                val jsonObjReq = object : JsonObjectRequest(
                    Request.Method.POST, url, jsonObject,
                    Response.Listener {
                            response ->  Log.i("=====>", "Registro Guardado Correctamente")
                        Toast.makeText(requireContext(), "Registro Guardado Correctamente", Toast.LENGTH_SHORT).show()
                        Log.i("JSON Enviado", jsonObject.toString())

                        //limpiar campos luego de insertar
                        inptipotarea.setSelection(0)
                        inpdetalletarea.text.clear()
                        inpplaca.text.clear()
                        inpdniconductor.text.clear()
                        inpfecharegistro.text.clear()
                        inpfechalimite.text.clear()
                    },
                    Response.ErrorListener {
                            error : VolleyError -> Log.i("=====>", error.message ?: "Error en Api")
                    }
                ){}
                val requestQueue = Volley.newRequestQueue(requireContext())
                requestQueue.add(jsonObjReq)

            } catch (e: JSONException){
                Log.i("=====>", e.message ?: "Error Json")
            }



        }

        return rootView
    }

}
