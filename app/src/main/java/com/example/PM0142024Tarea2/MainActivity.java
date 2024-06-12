package com.example.PM0142024Tarea2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private PersonasDBHelper dbHelper;
    private EditText etNombres, etApellidos, etEdad, etCorreo, etDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new PersonasDBHelper(this);

        etNombres = findViewById(R.id.et_nombres);
        etApellidos = findViewById(R.id.et_apellidos);
        etEdad = findViewById(R.id.et_edad);
        etCorreo = findViewById(R.id.et_correo);
        etDireccion = findViewById(R.id.et_direccion);
        Button btnSalvar = findViewById(R.id.btn_salvar);
        Button btnVer = findViewById(R.id.btn_ver);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = etNombres.getText().toString();
                String apellidos = etApellidos.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());
                String correo = etCorreo.getText().toString();
                String direccion = etDireccion.getText().toString();

                Personas persona = new Personas(nombres, apellidos, edad, correo, direccion);
                dbHelper.insertarPersona(persona);
            }
        });

        btnVer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewPersonasActivity.class);
                startActivity(intent);
            }
        });
    }
}
