package com.example.organizze.activity;

import android.os.Bundle;
import android.view.View;
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
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal = 0.0;

    private Double despesaGerada = 0.0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_despesas);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);
        campoValor = findViewById(R.id.editValor);


        campoData.setText(DateUtil.dataAtual());
        recuperarDespesaTotal();


    }

    public void salvarDespesa(View view){
        if (validarCampos()) {

            if (despesaTotal == null) {
                Toast.makeText(this, "Aguarde, carregando dados...", Toast.LENGTH_SHORT).show();
                return;
            }

            String data = campoData.getText().toString();
            despesaGerada = Double.parseDouble(campoValor.getText().toString());

            Movimentacao movimentacao = new Movimentacao();
            movimentacao.setValor(despesaGerada);
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");

            recuperarDespesaTotal();

            Double despesaAtualizada = despesaTotal + despesaGerada;

            atualizarDespesa(despesaAtualizada);
            movimentacao.salvar(data);
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


    public void recuperarDespesaTotal(){

        String email = autenticacao.getCurrentUser().getEmail();


        String idUsuario = Base64Custom.codificarBase64(email);
        DatabaseReference usuarioRef = firebase.child("usuarios").child(idUsuario);


        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue(Usuario.class);
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void atualizarDespesa(Double despesaAtualizada){

        String email = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64(email);
        DatabaseReference usuarioRef = firebase.child("usuarios").child(idUsuario);

        usuarioRef.child("despesaTotal").setValue(despesaAtualizada);
    }

}