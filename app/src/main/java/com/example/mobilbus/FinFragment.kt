package com.example.mobilbus

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FinFragment : Fragment(R.layout.fragment_fin) {
    // This fragment is also blank

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_fin, container, false)

        // Spinner (Origen o Destino)
        val spinner: Spinner = rootView.findViewById(R.id.spinnerOrigenDestino)

        // Campos de fecha y hora
        val edtFecha: EditText = rootView.findViewById(R.id.edtFecha)
        val edtHora: EditText = rootView.findViewById(R.id.edtHora)

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
                requireContext(), { view, year, month, dayOfMonth ->
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
                requireContext(), { view, hourOfDay, minute ->
                    val selectedTime = String.format("%02d:%02d", hourOfDay, minute)
                    edtHora.setText(selectedTime)
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
            )
            timePicker.show()
        }

        // Botón Registrar
        val btnRegistrar: Button = rootView.findViewById(R.id.btnRegistrarFinRuta)
        btnRegistrar.setOnClickListener {
            // Aquí puedes añadir el código para manejar el registro de datos
        }

        return rootView
    }
}
