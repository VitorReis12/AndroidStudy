package com.example.letcontrol;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Fragment_Home extends Fragment {

    TextView tvTotalGeral, tvTotalMensal, tvTotalAnual, tvGastoMes;

    private Handler handler = new Handler();
    private Runnable atualizarDadosRunnable;

    Button buttonVerMetas, buttonRitmoAtual, buttonAbrirDispositivo;
    private final int INTERVALO_ATUALIZACAO = 2000; // 10 segundos

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment__home, container, false);

        tvTotalGeral = view.findViewById(R.id.tvTotalGeral);
        tvTotalMensal = view.findViewById(R.id.tvTotalMes);
        tvTotalAnual = view.findViewById(R.id.tvTotalAno);
        tvGastoMes = view.findViewById(R.id.tvGastoMes);
        buttonVerMetas = view.findViewById(R.id.buttonVerMetas);
        buttonRitmoAtual = view.findViewById(R.id.button_ritmo_atual);
        buttonAbrirDispositivo = view.findViewById(R.id.buttonAbrirDispositivo);

        buttonAbrirDispositivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), DispositivoActivity.class);
                startActivity(intent);
            }
        });

        buttonVerMetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                androidx.fragment.app.FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_conteudo, new InformationFragment());
                transaction.commit();
            }
        });

        buttonRitmoAtual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), FluxoAtualActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences prefs = requireActivity().getSharedPreferences("login", getContext().MODE_PRIVATE);
        String email = prefs.getString("email", null);

//        if (email != null) {
//            iniciarAtualizacaoAutomatica(email);
//        } else {
//            Toast.makeText(getContext(), "Email não encontrado!", Toast.LENGTH_SHORT).show();
//        }

        return view;
    }

//    private void iniciarAtualizacaoAutomatica(String email) {
//        atualizarDadosRunnable = new Runnable() {
//            @Override
//            public void run() {
//                buscarTotaisPorEmail(email);
//                handler.postDelayed(this, INTERVALO_ATUALIZACAO);
//            }
//        };
//        handler.post(atualizarDadosRunnable); // inicia imediatamente
//    }
//
//    private void buscarTotaisPorEmail(String email) {
//        new Thread(() -> {
//            try {
//                String urlStr = "https://b47a-177-23-2-190.ngrok-free.app/letcontrolphp/consumo_usuario.php?email=" + email;
//                URL url = new URL(urlStr.replace(" ", "%20"));
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setRequestMethod("GET");
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                StringBuilder responseStr = new StringBuilder();
//                String line;
//
//                while ((line = reader.readLine()) != null) {
//                    responseStr.append(line);
//                }
//                reader.close();
//
//                JSONObject json = new JSONObject(responseStr.toString());
//
//                if (json.getBoolean("success")) {
//                    double totalGeral = json.getDouble("total_litros");
//                    double totalMes = json.getDouble("total_litros_mes");
//                    double totalAno = json.getDouble("total_litros_ano");
//
//                    requireActivity().runOnUiThread(() -> {
//                        tvTotalGeral.setText(String.format("%.2f Litros", totalGeral));
//                        tvTotalMensal.setText(String.format("%.2f Litros", totalMes));
//                        tvTotalAnual.setText(String.format("%.2f Litros", totalAno));
//                        float gasto = (float) totalMes;
//                        float gasto2 = gasto * 0.004f;
//                        String gastoS = String.format("%.2f", gasto2);
//                        tvGastoMes.setText("R$" + gastoS);
//
//
//                    });
//                } else {
//                    String erro = json.optString("error", "Erro desconhecido.");
//                    requireActivity().runOnUiThread(() ->
//                            Toast.makeText(getContext(), "Erro: " + erro, Toast.LENGTH_LONG).show());
//                }
//
//            } catch (Exception e) {
//                e.printStackTrace();
//                requireActivity().runOnUiThread(() ->
//                        Toast.makeText(getContext(), "Erro de conexão", Toast.LENGTH_LONG).show());
//            }
//        }).start();
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        handler.removeCallbacks(atualizarDadosRunnable); // Evita vazamento de memória
//    }
}
