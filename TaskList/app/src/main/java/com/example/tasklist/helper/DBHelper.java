package com.example.tasklist.helper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;


public class DBHelper extends SQLiteOpenHelper {

    public static int VERSION = 1;
    public static String NOME_BD = "BD_TAREFAS";
    public static String TABELA_TAREFAS = "tarefas";
    public DBHelper(@Nullable Context context) {
        super(context, NOME_BD, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS "+ TABELA_TAREFAS + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT NOT NULL);";

        try {

            db.execSQL(sql);
            Log.i("INFO DB", "Banco Criado Com Sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            Log.i("INFO DB", "ERRO ao Criar o Banco");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Será Executado se atualizar o VERSION
    }
}
