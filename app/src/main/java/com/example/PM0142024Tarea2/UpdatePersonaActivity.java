package com.example.PM0142024Tarea2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdatePersonaActivity extends AppCompatActivity {

    private PersonasDBHelper dbHelper;
    private EditText etNombres, etApellidos, etEdad, etCorreo, etDireccion;
    private Button btnActualizar;
    private int personaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_persona);

        dbHelper = new PersonasDBHelper(this);

        etNombres = findViewById(R.id.et_nombres);
        etApellidos = findViewById(R.id.et_apellidos);
        etEdad = findViewById(R.id.et_edad);
        etCorreo = findViewById(R.id.et_correo);
        etDireccion = findViewById(R.id.et_direccion);
        btnActualizar = findViewById(R.id.btn_actualizar);

        personaId = getIntent().getIntExtra("personaId", -1);

        Personas persona = dbHelper.getPersona(personaId);
        if (persona != null) {
            etNombres.setText(persona.getNombres());
            etApellidos.setText(persona.getApellidos());
            etEdad.setText(String.valueOf(persona.getEdad()));
            etCorreo.setText(persona.getCorreo());
            etDireccion.setText(persona.getDireccion());
        }

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = etNombres.getText().toString();
                String apellidos = etApellidos.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                String correo = etCorreo.getText().toString();
                String direccion = etDireccion.getText().toString();

                Personas personaActualizada = new Personas(personaId, nombres, apellidos, edad, correo, direccion);
                dbHelper.actualizarPersona(personaActualizada);

                Toast.makeText(UpdatePersonaActivity.this, "Persona actualizada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
