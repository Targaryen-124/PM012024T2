package com.example.PM0142024Tarea2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ViewPersonasActivity extends AppCompatActivity {

    private PersonasDBHelper dbHelper;
    private RecyclerView recyclerView;
    private PersonasAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_personas);

        dbHelper = new PersonasDBHelper(this);
        recyclerView = findViewById(R.id.recyclerViewPersonas);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Obtener la lista de personas de la base de datos
        List<Personas> listaPersonas = dbHelper.getAllPersonas();

        // Configurar el adaptador del RecyclerView
        adapter = new PersonasAdapter(this, listaPersonas);
        recyclerView.setAdapter(adapter);
    }
}
