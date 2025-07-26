package com.example.slidesproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;
import com.heinrichreimersoftware.materialintro.slide.SimpleSlide;

public class MainActivity extends IntroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_1)
                .build()
        );

        addSlide(new FragmentSlide.Builder()
                .background(android.R.color.white)
                .fragment(R.layout.intro_2)
                .build()
        );



//        addSlide(new SimpleSlide.Builder()
//                .title("Titulo")
//                .description("Descrição")
//                .image(R.drawable.um)
//                .background(android.R.color.holo_orange_light)
//                .build()
//        );
//
//        addSlide(new SimpleSlide.Builder()
//                .title("Titulo2")
//                .description("Descrição2")
//                .background(android.R.color.holo_orange_light)
//                .image(R.drawable.dois)
//                .build()
//        );
//
//        addSlide(new SimpleSlide.Builder()
//                .title("Titulo3")
//                .description("Descrição3")
//                .background(android.R.color.holo_orange_light)
//                .image(R.drawable.tres)
//                .build()
//        );
//
//        addSlide(new SimpleSlide.Builder()
//                .title("Titulo4")
//                .description("Descrição4")
//                .background(android.R.color.holo_orange_light)
//                .image(R.drawable.quatro)
//                .build()
//        );

    }
}