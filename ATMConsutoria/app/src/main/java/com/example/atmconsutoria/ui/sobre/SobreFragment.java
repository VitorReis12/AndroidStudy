package com.example.atmconsutoria.ui.sobre;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atmconsutoria.R;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SobreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SobreFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SobreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SobreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SobreFragment newInstance(String param1, String param2) {
        SobreFragment fragment = new SobreFragment();
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
        String description = "ATM Consultoria é uma empresa especializada em soluções empresariais, oferecendo serviços de consultoria voltados para o crescimento sustentável de negócios. Atuamos com foco em qualidade, inovação e atendimento personalizado, ajudando nossos clientes a alcançarem seus objetivos com eficiência e profissionalismo.";
        Element versao = new Element();
        Element space = new Element();
        versao.setTitle("1.7.12");
        versao.setIconDrawable(R.drawable.perfil);
        View view = new AboutPage(getActivity())
                .setImage(R.drawable.logo)
                .setDescription(description)
                .addGroup("Entre em Contato")
                .addEmail("atendimento@atmconsultoria.com","Envie um Email")
                .addWebsite("letcontrol-vercel.com","Acesse nosso Site")

                .addGroup("Redes Sociais")
                .addFacebook("","Facebook")
                .addInstagram("","Instagram")
                .addYoutube("","Youtube")
                .addGitHub("VitorReis12", "GitHub")
                .addPlayStore("","Dowload App")
                .addItem(versao)
                .addItem(space)
                .create();

        return view;


    }
}