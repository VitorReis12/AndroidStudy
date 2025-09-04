package com.example.whatsapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.whatsapp.R;
import com.example.whatsapp.config.ConfiguracaoFirebase;
import com.example.whatsapp.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {


    FirebaseAuth auth;
    Button buttonLogar;
    TextInputEditText inputEmail, inputSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        inputEmail = findViewById(R.id.inputEmailLogin);
        inputSenha = findViewById(R.id.inputSenhaLogin);

        buttonLogar = findViewById(R.id.buttonLogar);
        buttonLogar.setOnClickListener(V -> valiarCampos());



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null){
            abrirTelaPrincipal();
        }
    }

    public void abrirTelaCadastro(View view){

        Intent intent = new Intent(getApplicationContext(), CadastroActivity.class);
        startActivity(intent);

    }

    public void logarUsuario(Usuario usuario){


        auth = ConfiguracaoFirebase.getAuth();
        auth.signInWithEmailAndPassword(usuario.getEmail(),usuario.getSenha()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                    abrirTelaPrincipal();
                }else {
                    String exception;
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        exception = "Por favor Digite um email válido";
                    }catch (FirebaseAuthUserCollisionException e){
                        exception = "Esta conta já foi cadastrada!";
                    }catch (Exception e){
                        exception = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(getApplicationContext(), exception, Toast.LENGTH_LONG).show();
                }

            }
        });


    }


    public void valiarCampos(){

        String email = inputEmail.getText().toString();
        String senha = inputSenha.getText().toString();


        if (!email.isEmpty()){
            if (!senha.isEmpty()){


                    Usuario usuario = new Usuario();
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    logarUsuario(usuario);



            }else {
                    Toast.makeText(getApplicationContext(), "Preencha o campo Senha!", Toast.LENGTH_SHORT).show();
            }
        }else {
                Toast.makeText(getApplicationContext(), "Preencha o campo Email!", Toast.LENGTH_SHORT).show();
        }
    }



    public void abrirTelaPrincipal(){


        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);

    }



}



