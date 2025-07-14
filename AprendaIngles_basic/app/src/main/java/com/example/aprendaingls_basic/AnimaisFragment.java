package com.example.aprendaingls_basic;

import static com.example.aprendaingls_basic.R.id.imageButtonCao;
import static com.example.aprendaingls_basic.R.id.imageButtonGato;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnimaisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnimaisFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MediaPlayer mediaPlayer;
    private ImageButton imageButtonCao, imageButtonGato, imageButtonLeao, imageButtonMacaco, imageButtonOvelha, imageButtonVaca;

    public AnimaisFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnimaisFragment.
     */
    // TODO: Rename and change types and number of parameters

    public static AnimaisFragment newInstance(String param1, String param2) {
        AnimaisFragment fragment = new AnimaisFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_animais, container, false);
        imageButtonCao = view.findViewById(R.id.imageButtonCao);
        imageButtonGato = view.findViewById(R.id.imageButtonGato);
        imageButtonLeao = view.findViewById(R.id.imageButtonLeao);
        imageButtonMacaco = view.findViewById(R.id.imageButtonMacaco);
        imageButtonOvelha = view.findViewById(R.id.imageButtonOvelha);
        imageButtonVaca = view.findViewById(R.id.imageButtonVaca);

        imageButtonCao.setOnClickListener(this);
        imageButtonGato.setOnClickListener(this);
        imageButtonLeao.setOnClickListener(this);
        imageButtonMacaco.setOnClickListener(this);
        imageButtonOvelha.setOnClickListener(this);
        imageButtonVaca.setOnClickListener(this);
        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {

        int id = view.getId();

        if (id == R.id.imageButtonCao) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.dog);
            tocarSom();
        } else if (id == R.id.imageButtonGato) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.cat);
            tocarSom();
        } else if (id == R.id.imageButtonLeao) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.lion);
            tocarSom();
        } else if (id == R.id.imageButtonMacaco) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.monkey);
            tocarSom();
        } else if (id == R.id.imageButtonOvelha) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.sheep);
            tocarSom();
        } else if (id == R.id.imageButtonVaca) {
            mediaPlayer = MediaPlayer.create(getContext(), R.raw.cow);
            tocarSom();
        }
    }
    public void tocarSom(){
        if (mediaPlayer != null){
            mediaPlayer.start();
        }

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });

    }


    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null){
            mediaPlayer.release();
        }
    }

}