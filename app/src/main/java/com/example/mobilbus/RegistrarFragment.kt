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

class RegistrarFragment : Fragment(R.layout.fragment_registrar) {
    // This fragment is simply blank, so no logic needed here

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflar el layout del fragmento
        val rootView = inflater.inflate(R.layout.fragment_registrar, container, false)

        // Spinner (Origen o Destino)
        val spinner: Spinner = rootView.findViewById(R.id.spinnerTipoTarea)

        // Campos de fecha
        val edtFecha: EditText = rootView.findViewById(R.id.edtFechaLimite)

        // Obtener la fecha actual del sistema
        val currentDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
        edtFecha.setText(currentDate)

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

        // Botón Registrar
        val btnRegistrar: Button = rootView.findViewById(R.id.btnRegistrarTarea)
        btnRegistrar.setOnClickListener {
            // accion Registrar
        }

        return rootView
    }
}
