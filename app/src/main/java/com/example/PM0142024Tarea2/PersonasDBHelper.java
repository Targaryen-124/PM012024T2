package com.example.PM0142024Tarea2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PersonasDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "personas.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_PERSONAS = "personas";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NOMBRES = "nombres";
    public static final String COLUMN_APELLIDOS = "apellidos";
    public static final String COLUMN_EDAD = "edad";
    public static final String COLUMN_CORREO = "correo";
    public static final String COLUMN_DIRECCION = "direccion";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_PERSONAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NOMBRES + " TEXT, " +
                    COLUMN_APELLIDOS + " TEXT, " +
                    COLUMN_EDAD + " INTEGER, " +
                    COLUMN_CORREO + " TEXT, " +
                    COLUMN_DIRECCION + " TEXT);";

    public PersonasDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSONAS);
        onCreate(db);
    }

    public List<Personas> getAllPersonas() {
        List<Personas> personasList = new ArrayList<>();
        String selectQuery = "SELECT  * FROM " + TABLE_PERSONAS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Personas persona = new Personas();
                persona.setId(cursor.getInt(0));
                persona.setNombres(cursor.getString(1));
                persona.setApellidos(cursor.getString(2));
                persona.setEdad(cursor.getInt(3));
                persona.setCorreo(cursor.getString(4));
                persona.setDireccion(cursor.getString(5));

                personasList.add(persona);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return personasList;
    }

    public void insertarPersona(Personas persona) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRES, persona.getNombres());
        values.put(COLUMN_APELLIDOS, persona.getApellidos());
        values.put(COLUMN_EDAD, persona.getEdad());
        values.put(COLUMN_CORREO, persona.getCorreo());
        values.put(COLUMN_DIRECCION, persona.getDireccion());

        // Insertar fila
        db.insert(TABLE_PERSONAS, null, values);
        db.close(); // Cerrar conexión a la base de datos
    }

    public void actualizarPersona(Personas persona) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOMBRES, persona.getNombres());
        values.put(COLUMN_APELLIDOS, persona.getApellidos());
        values.put(COLUMN_EDAD, persona.getEdad());
        values.put(COLUMN_CORREO, persona.getCorreo());
        values.put(COLUMN_DIRECCION, persona.getDireccion());

        db.update(TABLE_PERSONAS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(persona.getId())});
        db.close();
    }

    public void eliminarPersona(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PERSONAS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }

    public Personas getPersona(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PERSONAS, new String[]{COLUMN_ID, COLUMN_NOMBRES, COLUMN_APELLIDOS, COLUMN_EDAD, COLUMN_CORREO, COLUMN_DIRECCION},
                COLUMN_ID + "=?", new String[]{String.valueOf(id)}, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Personas persona = new Personas(
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NOMBRES)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_APELLIDOS)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_EDAD)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CORREO)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DIRECCION))
            );
            cursor.close();
            return persona;
        } else {
            return null;
        }
    }
}
