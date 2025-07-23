package com.example.firebaseapp;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.firebaseapp.model.Produto;
import com.example.firebaseapp.model.Usuario;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {


    /*private ImageView image;
    private Button buttonUpload;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private FirebaseAuth auth = FirebaseAuth.getInstance();*/

    @SuppressLint("MissingInflatedId")
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


        /*image = findViewById(R.id.imageView);
        buttonUpload = findViewById(R.id.button);


        buttonUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                image.isDrawingCacheEnabled();
                image.buildDrawingCache();

                Bitmap bitmap = image.getDrawingCache();

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 70, baos);
                byte[] data = baos.toByteArray();

                StorageReference storageReference = FirebaseStorage.getInstance().getReference();
                StorageReference imagens = storageReference.child("imagens").child("foto-perfil");
                StorageReference imageRef = imagens.child("celular.jpeg");

                Glide.with(MainActivity.this)
                        .load(imageRef)
                        .into(image);

                imageRef.delete().addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        Toast.makeText(MainActivity.this, "Erro ao deletar", Toast.LENGTH_SHORT).show();

                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        Toast.makeText(MainActivity.this, "Sucesso ao Deletar o Arquivo", Toast.LENGTH_SHORT).show();

                    }
                });


                String nomeArquivo = UUID.randomUUID().toString();
                StorageReference imageRef = imagens.child(nomeArquivo+".jpeg");

                UploadTask uploadTask = imageRef.putBytes(data);

                uploadTask.addOnFailureListener(MainActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Upload da imagem falhou: " + e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                }).addOnSuccessListener(MainActivity.this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        imageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                Uri uri = task.getResult();
                                Toast.makeText(MainActivity.this, "Sucesso ao fazer o Upload" + uri.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });


            }
        });

        DatabaseReference usuarios = reference.child("usuarios");

//        DatabaseReference usuarioPesquisa = usuarios.child("-OVoEE9rdctvvE1NkQ1Y");
//        Query usuarioPesquisa = usuarios.orderByChild("idade").startAt(18).endAt(24);
        Query usuarioPesquisa = usuarios.orderByChild("nome").startAt("Ma").endAt("Ma"+"\uf8ff");

//        Query usuarioPesquisa = usuarios.orderByChild("nome").equalTo("Alexandre");
//        Query usuarioPesquisa = usuarios.orderByKey().limitToFirst(2);
//        Query usuarioPesquisa = usuarios.orderByKey().limitToLast(2);


         usuarioPesquisa.addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
//                 Usuario dadosUsuario = snapshot.getValue(Usuario.class);
//                 Log.i(" Dados usuario: ", "nome: " + dadosUsuario.getNome()+"  sobrenome: " + dadosUsuario.getSobrenome()+ "  idade: "+ dadosUsuario.getIdade());

                 Log.i(" Dados usuario: ", snapshot.getValue().toString());
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {

             }
         });


        Usuario usuario = new Usuario("Alexandre","Macedo",40);
        Usuario usuario2 = new Usuario("Marcos","Millian",24);
        Usuario usuario3 = new Usuario("Vitor","Ferreira",24);
        Usuario usuario4 = new Usuario("Marcelo","Farias",27);
        Usuario usuario5 = new Usuario("ana","Ribeiro",70);

        usuarios.push().setValue(usuario);


        auth.signOut();

        auth.signInWithEmailAndPassword("victor@gmail.com","vic12345").addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Log.i("singIn"," Sucesso ao Logar Usuario ");
                }else {
                    Log.i("singIn"," Erro ao Logar Usuario ");
                }
            }
        });

        if (auth.getCurrentUser() != null){
            Log.i("CurrentUser"," Usuario Logado ");
        }else {
            Log.i("CurrentUser"," Usuario não está logado ");
        }

        auth.createUserWithEmailAndPassword(
                "victor@gmail.com","vic12345")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.i("CreateUser","Sucesso ao Cadastrar Usuario");
                        }else {
                            Log.i("CreateUser","Erro ao Cadastrar Usuario");
                        }
                    }
                });


        DatabaseReference usuarios = reference.child("usuarios").child("001");
        DatabaseReference produtos = reference.child("produtos");

        usuarios.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Log.i("FIREBASE-TESTES", snapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Usuario usuario = new Usuario("Vitor","Ferreira",24);
        Usuario usuario2 = new Usuario("Maria","Palma",70);
        usuarios.child("002").setValue(usuario2);

        Produto produto = new Produto("Notebook","Dell",4.224);
        Produto produto2 = new Produto("Iphone 12","Apple",2.100);
        produtos.child("001").setValue(produto);
        produtos.child("002").setValue(produto2);*/


    }
}

