package com.example.organizze.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.organizze.R;
import com.example.organizze.config.ConfiguracaoFirebase;
import com.example.organizze.helper.Base64Custom;
import com.example.organizze.helper.DateUtil;
import com.example.organizze.model.Movimentacao;
import com.example.organizze.model.Usuario;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class ReceitasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double receitaTotal = 0.0;

    private Double receitaGerada = 0.0;

    private FloatingActionButton buttonSalvarReceita;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_receitas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoData = findViewById(R.id.editReceitaData);
        campoCategoria = findViewById(R.id.editReceitaCategoria);
        campoDescricao = findViewById(R.id.editReceitaDescricao);
        campoValor = findViewById(R.id.editReceitaValor);

        campoData.setText(DateUtil.dataAtual());

        buttonSalvarReceita = findViewById(R.id.fabSalvarReceita);

        buttonSalvarReceita.setOnClickListener(V-> salvarReceita());

        recuperarReceitaTotal();


    }

    public void salvarReceita(){

        Log.i("usuario", "salvarReceita: "+ autenticacao.getCurrentUser().getEmail());


        if (validarCampos()){

            if (receitaTotal == null) {
                Toast.makeText(this, "Aguarde, carregando dados...", Toast.LENGTH_SHORT).show();
                return;
            }

            String data = campoData.getText().toString();
            receitaGerada = Double.parseDouble(campoValor.getText().toString());

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setValor(receitaGerada);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("r");

            recuperarReceitaTotal();

            Double receitaAtualizada = receitaTotal + receitaGerada;

            atualizarReceita(receitaAtualizada);
            movimentacao.salvar(data);
            Log.i("teste", "salvarReceita: " + receitaAtualizada + " |RT:  " + receitaTotal);
            finish();

        }



    }

    public Boolean validarCampos(){


        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if (!textoValor.isEmpty()){

            if (!textoData.isEmpty()){

                if (!textoCategoria.isEmpty()){

                    if (!textoDescricao.isEmpty()){

                        return true;

                    }else {
                        Toast.makeText(this, "Preencha a Descrição", Toast.LENGTH_SHORT).show();
                        return false;
                    }

                }else {
                    Toast.makeText(this, "Preencha a Categoria", Toast.LENGTH_SHORT).show();
                    return false;
                }

            }else {
                Toast.makeText(this, "Preencha a Data", Toast.LENGTH_SHORT).show();
                return false;
            }

        }else {
            Toast.makeText(this, "Preencha o Valor", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


    public void recuperarReceitaTotal(){

        String email = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(email);

        DatabaseReference usuarioRef = firebase.child("usuarios").child(idUsuario);

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                receitaTotal = usuario.getReceitaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void atualizarReceita(Double receitaAtualizada){

        String email = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(email);

        DatabaseReference usuarioRef = firebase.child("usuarios").child(idUsuario);
        usuarioRef.child("receitaTotal").setValue(receitaAtualizada);


    }


}