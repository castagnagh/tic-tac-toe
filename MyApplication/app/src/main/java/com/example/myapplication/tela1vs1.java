package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class tela1vs1 extends AppCompatActivity {

    TextView textoJogador;
    Button button11, button12, button13, button21, button22, button23, button31, button32, button33, reiniciar, voltar;

    int[][] matriz = new int[3][3];
    int jogadorAtual = 1;
    int limiteJogadas = 0;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        jogadorAtual = savedInstanceState.getInt("jogadorAtual");
        limiteJogadas = savedInstanceState.getInt("limiteJogadas");
        int[] flattenedMatrix = savedInstanceState.getIntArray("matriz");
        matriz = unflattenMatrix(flattenedMatrix);

        // Restaura o texto dos botões
        button11.setText(savedInstanceState.getString("button11_text"));
        button12.setText(savedInstanceState.getString("button12_text"));
        button13.setText(savedInstanceState.getString("button13_text"));
        button21.setText(savedInstanceState.getString("button21_text"));
        button22.setText(savedInstanceState.getString("button22_text"));
        button23.setText(savedInstanceState.getString("button23_text"));
        button31.setText(savedInstanceState.getString("button31_text"));
        button32.setText(savedInstanceState.getString("button32_text"));
        button33.setText(savedInstanceState.getString("button33_text"));

        // Atualiza o texto do jogador
        textoJogador.setText("Sua vez jogador " + jogadorAtual);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela1vs1);

        reiniciar = findViewById(R.id.reiniciar);
        voltar = findViewById(R.id.voltar);
        textoJogador = findViewById(R.id.textoJogador);


        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        textoJogador.setText("Sua vez jogador " + jogadorAtual);
        button11 = findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(0,0, button11);
            }
        });

        button12 = findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(0,1, button12);
            }
        });

        button13 = findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(0,2, button13);
            }
        });

        button21 = findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(1,0, button21);
            }
        });

        button22 = findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(1,1, button22);
            }
        });

        button23 = findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(1,2, button23);
            }
        });

        button31 = findViewById(R.id.button31);
        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(2,0, button31);
            }
        });

        button32 = findViewById(R.id.button32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(2,1, button32);
            }
        });

        button33 = findViewById(R.id.button33);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realizarJogada(2,2, button33);
            }
        });

        reiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reiniciarJogo();
            }
        });

        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(tela1vs1.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void realizarJogada(int i, int j, Button button){
            if (matriz[i][j] == 0) {
                limiteJogadas++;
                if (jogadorAtual == 1) {
                    matriz[i][j] = 1;
                    button.setText("X");
                } else {
                    matriz[i][j] = 2;
                    button.setText("O");
                }
                if (verificarGanhador(jogadorAtual)) {
                    exibirMensagemVitoria(jogadorAtual);
                    desativarBotoes();
                } else if (limiteJogadas == 9) {
                    exibirMensagemEmpate();
                    desativarBotoes();
                } else {
                    jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
                    textoJogador.setText("Sua vez jogador " + jogadorAtual);
                }
            }
        }
        public boolean verificarGanhador(int jogador) {
            //verifica as linhas
            for (int i = 0; i < 3; i++) {
                if (matriz[i][0] == jogador && matriz[i][1] == jogador && matriz[i][2] == jogador) {
                    return true;
                }
            }
            // Verifica as colunas
            for (int j = 0; j < 3; j++) {
                if (matriz[0][j] == jogador && matriz[1][j] == jogador && matriz[2][j] == jogador) {
                    return true;
                }
            }
            // Verifica as diagonais
            if (matriz[0][0] == jogador && matriz[1][1] == jogador && matriz[2][2] == jogador) {
                return true;
            }
            if (matriz[0][2] == jogador && matriz[1][1] == jogador && matriz[2][0] == jogador) {
                return true;
            }
            return false;
        }

        public void exibirMensagemVitoria(int jogador) {
            textoJogador.setText("Jogador " + jogador + " venceu!!!");
        }

        public void exibirMensagemEmpate() {
            textoJogador.setText("Velha!");
        }

        public void desativarBotoes() {
            button11.setEnabled(false);
            button12.setEnabled(false);
            button13.setEnabled(false);
            button21.setEnabled(false);
            button22.setEnabled(false);
            button23.setEnabled(false);
            button31.setEnabled(false);
            button32.setEnabled(false);
            button33.setEnabled(false);
        }

        public void reiniciarJogo() {
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    matriz[i][j] = 0;
                }
            }
            button11.setText("");
            button12.setText("");
            button13.setText("");
            button21.setText("");
            button22.setText("");
            button23.setText("");
            button31.setText("");
            button32.setText("");
            button33.setText("");
            button11.setEnabled(true);
            button12.setEnabled(true);
            button13.setEnabled(true);
            button21.setEnabled(true);
            button22.setEnabled(true);
            button23.setEnabled(true);
            button31.setEnabled(true);
            button32.setEnabled(true);
            button33.setEnabled(true);
            jogadorAtual = 1;
            limiteJogadas = 0;
            textoJogador.setText("Sua vez jogador " + jogadorAtual);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Salva o estado do jogo no Bundle
        outState.putInt("jogadorAtual", jogadorAtual);
        outState.putInt("limiteJogadas", limiteJogadas);
        int[] flattenedMatrix = flattenMatrix(matriz);
        outState.putIntArray("matriz", flattenedMatrix);

        // Salva o texto dos botões no Bundle
        outState.putString("button11_text", button11.getText().toString());
        outState.putString("button12_text", button12.getText().toString());
        outState.putString("button13_text", button13.getText().toString());
        outState.putString("button21_text", button21.getText().toString());
        outState.putString("button22_text", button22.getText().toString());
        outState.putString("button23_text", button23.getText().toString());
        outState.putString("button31_text", button31.getText().toString());
        outState.putString("button32_text", button32.getText().toString());
        outState.putString("button33_text", button33.getText().toString());
    }

    private int[] flattenMatrix(int[][] matrix) {
        int[] flattened = new int[9];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                flattened[index++] = matrix[i][j];
            }
        }
        return flattened;
    }

    private int[][] unflattenMatrix(int[] flattened) {
        int[][] matrix = new int[3][3];
        int index = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = flattened[index++];
            }
        }
        return matrix;
    }
}