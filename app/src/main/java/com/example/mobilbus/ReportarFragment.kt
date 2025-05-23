package com.example.mobilbus

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.mobilbus.com.example.mobilbus.Tarea
import org.json.JSONException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class ReportarFragment : Fragment(), TareasAdapter.OnItemClickListener {

    private lateinit var adapter: TareasAdapter
    private var tareaSeleccionada: Tarea? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_reportar, container, false)

        // set Campos de fecha
        val inputFechareentrega: EditText = rootView.findViewById(R.id.inputFechaEntrega)

        // Obtener la fecha actual del sistema
        val currentDate = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(Date())
        inputFechareentrega.setText(currentDate)

        // Fecha entrega - DatePicker
        inputFechareentrega.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                    inputFechareentrega.setText(selectedDate)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Configura RecyclerView y adaptador con lista vacía inicialmente
        val recyclerView: RecyclerView = rootView.findViewById(R.id.recyclerViewTareas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TareasAdapter(mutableListOf(), this)
        recyclerView.adapter = adapter

        val btnListarTareas: ImageButton = rootView.findViewById(R.id.imageBuscartarea)
        btnListarTareas.setOnClickListener {
            val txtCriterio = rootView.findViewById<EditText>(R.id.inputBuscartareapordni)
            val criterio = txtCriterio.text.toString()
            Log.i("CRITERIO", "Valor criterio: '$criterio'")

            val url = "https://h9ul1f7bmb.execute-api.us-east-1.amazonaws.com/v1/tarea?criterio=$criterio"
            Log.i("URL", url)

            val stringRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {
                        val jsonArray = response.getJSONArray("data")
                        Log.i("JSON Enviado", jsonArray.toString())
                        val items = mutableListOf<Tarea>()
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            items.add(
                                Tarea(
                                    id = obj.getString("id"),
                                    detalle = obj.getString("detalletarea"),
                                    estado = obj.getString("estado"),
                                    fechaLimite = obj.getString("fechalimite"),
                                    dniconductor = obj.getString("dniconductor")
                                )
                            )
                        }
                        adapter.actualizarTareas(items)
                    } catch (e: JSONException) {
                        Log.i("=====>", e.message.toString())
                    }
                },
                { error ->
                    Log.i("=====>", error.toString())
                }
            )
            val requestQueue = Volley.newRequestQueue(requireContext())
            requestQueue.add(stringRequest)
        }

        val inputDetalle = rootView.findViewById<EditText>(R.id.inputDetalle)
        val inputFechaEntrega = rootView.findViewById<EditText>(R.id.inputFechaEntrega)
        val txtEstadoRepor = rootView.findViewById<EditText>(R.id.txtEstadoRepor)
        val btnActualizar = rootView.findViewById<Button>(R.id.btnActualizarTarea)

        btnActualizar.setOnClickListener {
            if (tareaSeleccionada == null) {
                Toast.makeText(context, "Seleccione una tarea primero", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Recoger datos editados
            val nuevoDetalle = inputDetalle.text.toString()
            val nuevaFechaEntrega = inputFechaEntrega.text.toString()
            val nuevoEstado = txtEstadoRepor.text.toString()

            // TODO: Implementa aquí la lógica para enviar los datos actualizados al backend
            Toast.makeText(context, "Tarea actualizada (simulado)", Toast.LENGTH_SHORT).show()
        }

        return rootView
    }

    override fun onItemClick(tarea: Tarea) {
        tareaSeleccionada = tarea
        //view?.findViewById<EditText>(R.id.inputDetalle)?.setText(tarea.detalle)
        //view?.findViewById<EditText>(R.id.inputFechaEntrega)?.setText(tarea.fechaLimite)
        //view?.findViewById<EditText>(R.id.txtEstadoRepor)?.setText(tarea.estado)

        Toast.makeText(context, "Seleccionaste la tarea: ${tarea.detalle}", Toast.LENGTH_SHORT).show()
    }
}
