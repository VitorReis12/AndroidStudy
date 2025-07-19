package com.example.bancodedadossqlite_1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {

            SQLiteDatabase database = openOrCreateDatabase("appBD", MODE_PRIVATE, null);


            database.execSQL("CREATE TABLE IF NOT EXISTS Usuario ( id INTEGER PRIMARY KEY AUTOINCREMENT ,nome VARCHAR(20), idade INT(3) ) ");


//            database.execSQL("INSERT INTO Usuario(nome,idade) VALUES ('Usuario_1',12) ");
//            database.execSQL("INSERT INTO Usuario(nome,idade) VALUES ('Usuario_2',18) ");
//            database.execSQL("INSERT INTO Usuario(nome,idade) VALUES ('Usuario_3',24) ");
//            database.execSQL("INSERT INTO Usuario(nome,idade) VALUES ('Usuario_4',40) ");


            String consulta = "SELECT * FROM Usuario ";
//            String consulta = "SELECT id, nome, idade FROM Usuario ";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE nome = 'Usuario_1' AND idade = 24";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade >= 30 OR idade = 24";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade IN(18,35)";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade IN('Usuario_1','Usuario_2')";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade BETWEEN 30 AND 50";
//
//            String filtro = "";
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE nome LIKE '%"+filtro+"%' ";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade IN(18,35) ORDER BY idade ASC";
//
//            String consulta = "SELECT id, nome, idade FROM Usuario " +
//                    "WHERE idade IN(18,35) ORDER BY nome DESC LIMIT 3";

            database.execSQL("UPDATE Usuario set idade = 21 WHERE nome = 'Usuario_2' ");
            database.execSQL("DELETE FROM Usuario WHERE id = 4");
//            database.execSQL("DELETE FROM Usuario");

            Cursor cursor = database.rawQuery(consulta, null);

            int indiceNome = cursor.getColumnIndex("nome");
            int indiceId = cursor.getColumnIndex("id");
            int indiceIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null) {
                String id = cursor.getString(indiceId);
                String nome = cursor.getString(indiceNome);
                String idade = cursor.getString(indiceIdade);

                Log.i("RESULTADO -  ", "ID: " + id + "  NOME: " + nome + "  IDADE: " + idade);

                cursor.moveToNext();
            }

//            database.execSQL("DROP TABLE Usuario");

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}