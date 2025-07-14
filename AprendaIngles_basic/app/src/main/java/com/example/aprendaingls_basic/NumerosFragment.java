package com.example.aprendaingls_basic;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumerosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumerosFragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MediaPlayer mediaPlayer;


    private ImageButton imageButtonUm, imageButtonDois, imageButtonTres, imageButtonQuatro, imageButtonCinco;

    public NumerosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumerosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NumerosFragment newInstance(String param1, String param2) {
        NumerosFragment fragment = new NumerosFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_numeros, container, false);
        imageButtonUm = view.findViewById(R.id.imageButtonUm);
        imageButtonDois = view.findViewById(R.id.imageButtonDois);
        imageButtonTres = view.findViewById(R.id.imageButtonTres);
        imageButtonQuatro = view.findViewById(R.id.imageButtonQuatro);
        imageButtonCinco = view.findViewById(R.id.imageButtonCinco);

        imageButtonUm.setOnClickListener(this);
        imageButtonDois.setOnClickListener(this);
        imageButtonTres.setOnClickListener(this);
        imageButtonQuatro.setOnClickListener(this);
        imageButtonCinco.setOnClickListener(this);


        return view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {

            int id = v.getId();

            if (id == R.id.imageButtonUm) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.one);
                tocarSom();
            } else if (id == R.id.imageButtonDois) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.two);
                tocarSom();
            } else if (id == R.id.imageButtonTres) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.three);
                tocarSom();
            } else if (id == R.id.imageButtonQuatro) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.four);
                tocarSom();
            } else if (id == R.id.imageButtonCinco) {
                mediaPlayer = MediaPlayer.create(getContext(), R.raw.five);
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
    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null){ mediaPlayer.release();
        }
    }

}