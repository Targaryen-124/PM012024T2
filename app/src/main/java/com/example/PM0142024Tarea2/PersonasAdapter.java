package com.example.PM0142024Tarea2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PersonasAdapter extends RecyclerView.Adapter<PersonasAdapter.PersonaViewHolder> {

    private List<Personas> listaPersonas;
    private Context context;
    private PersonasDBHelper dbHelper;

    public PersonasAdapter(Context context, List<Personas> listaPersonas) {
        this.context = context;
        this.listaPersonas = listaPersonas;
        dbHelper = new PersonasDBHelper(context);
    }

    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.persona_item, parent, false);
        return new PersonaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = listaPersonas.get(position);
        holder.nombres.setText(persona.getNombres());
        holder.apellidos.setText(persona.getApellidos());
        holder.edad.setText(String.valueOf(persona.getEdad()));
        holder.correo.setText(persona.getCorreo());
        holder.direccion.setText(persona.getDireccion());

        holder.btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdatePersonaActivity.class);
                intent.putExtra("personaId", persona.getId());
                context.startActivity(intent);
            }
        });

        holder.btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.eliminarPersona(persona.getId());
                listaPersonas.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listaPersonas.size());
                Toast.makeText(context, "Persona eliminada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas.size();
    }

    class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView nombres, apellidos, edad, correo, direccion;
        Button btnActualizar, btnEliminar;

        PersonaViewHolder(@NonNull View itemView) {
            super(itemView);
            nombres = itemView.findViewById(R.id.tv_nombres);
            apellidos = itemView.findViewById(R.id.tv_apellidos);
            edad = itemView.findViewById(R.id.tv_edad);
            correo = itemView.findViewById(R.id.tv_correo);
            direccion = itemView.findViewById(R.id.tv_direccion);
            btnActualizar = itemView.findViewById(R.id.btn_actualizar);
            btnEliminar = itemView.findViewById(R.id.btn_eliminar);
        }
    }
}
