package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class tela1vscomp extends AppCompatActivity {

    TextView textoJogador, JogadorX, Jogador0;
    Button button11, button12, button13, button21, button22, button23, button31, button32, button33, reiniciar, voltar;
    int[][] matriz = new int[3][3];
    int jogadorAtual = 1;
    int limiteJogadas = 0;
    int escolha=0;

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
        setContentView(R.layout.activity_tela1vscomp);

        reiniciar = findViewById(R.id.reiniciar);
        voltar = findViewById(R.id.voltar);
        textoJogador = findViewById(R.id.textoJogador);
        JogadorX = findViewById(R.id.JogadorX);
        Jogador0 = findViewById(R.id.Jogador0);


        //CRIAÇÃO DA MATRIZ
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 0;
            }
        }

        //RECEBENDO PARAMETRO DA TELAESCOLHAVSCOMP
        Intent recebeEscolha = getIntent();
        if (recebeEscolha != null) {
            String escolhaX = recebeEscolha.getStringExtra("xis");
            String escolha0 = recebeEscolha.getStringExtra("bolinha");

            //SE A ESCOLHA DO PLAYER FOR 'X' ENTÃO FAÇA:
            if ("X".equals(escolhaX)) {
                JogadorX.setText("Você - X");
                Jogador0.setText("Computador - 0");
                textoJogador.setText("Sua vez");
                button11 = findViewById(R.id.button11);
                button11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(0, 0, button11);

                    }
                });

                button12 = findViewById(R.id.button12);
                button12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(0, 1, button12);
                    }
                });

                button13 = findViewById(R.id.button13);
                button13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(0, 2, button13);
                    }
                });

                button21 = findViewById(R.id.button21);
                button21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(1, 0, button21);
                    }
                });

                button22 = findViewById(R.id.button22);
                button22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(1, 1, button22);
                    }
                });

                button23 = findViewById(R.id.button23);
                button23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(1, 2, button23);
                    }
                });

                button31 = findViewById(R.id.button31);
                button31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(2, 0, button31);
                    }
                });

                button32 = findViewById(R.id.button32);
                button32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(2, 1, button32);
                    }
                });

                button33 = findViewById(R.id.button33);
                button33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        realizarJogadaX(2, 2, button33);
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
                        Intent intent = new Intent(tela1vscomp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
            //SE A ESCOLHA DO PLAYER FOR '0' ENTÃO FAÇA:
            if ("0".equals(escolha0)) {
                escolha=2;
                JogadorX.setText("Computador - X");
                Jogador0.setText("Você - 0");
                textoJogador.setText("Computador jogando");
                primeiraJogadaPC();
                button11 = findViewById(R.id.button11);
                button11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(0,0,button11);
                    }
                });

                button12 = findViewById(R.id.button12);
                button12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(0,1,button12);
                    }
                });

                button13 = findViewById(R.id.button13);
                button13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(0,2,button13);
                    }
                });

                button21 = findViewById(R.id.button21);
                button21.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(1,0,button21);
                    }
                });

                button22 = findViewById(R.id.button22);
                button22.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(1,1,button22);
                    }
                });

                button23 = findViewById(R.id.button23);
                button23.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(1,2,button23);
                    }
                });

                button31 = findViewById(R.id.button31);
                button31.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(2,0,button31);
                    }
                });

                button32 = findViewById(R.id.button32);
                button32.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(2,1,button32);
                    }
                });

                button33 = findViewById(R.id.button33);
                button33.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        jogadaComputadorX();
                        realizarJogada0(2,2,button33);
                    }
                });

                reiniciar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        reiniciarComputador0();
                    }
                });

                voltar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(tela1vscomp.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }
        }
    }

    //cÓDIGO PARA QUANDO O JOGADOR ESCOLHE 'X'
    public void realizarJogadaX(int i, int j, Button button){
        //VERIFICA A POSIÇÃO ANTES DE REALIZAR A JOGADA, SE ESTIVER VAZIA PROCEDE...
        if (matriz[i][j] == 0) {
            //SOMA A QUANTIDADE DE JOGADAS
            limiteJogadas++;
            if (jogadorAtual == 1) {
                matriz[i][j] = 1;
                button.setText("X");
            }
            if (verificarGanhador(jogadorAtual)) {
                exibirMensagemVitoria(jogadorAtual);
                desativarBotoes();
            } else if (limiteJogadas == 9) {
                exibirMensagemEmpate();
                desativarBotoes();
            } else {
                textoJogador.setText("Computador jogando");
                jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
                jogadaComputador0();
            }
        }
    }
    //CÓDIGO DA JOGADA DO COMPUTADOR PARA QUANDO O JOGADOR ESCOLHE 'X' E O COMPUTADOR É '0'
    private void jogadaComputador0() {
        // Desativa os botões durante a jogada do computador para evitar bug do player escolher na vez do computador
        desativarBotoes();
        //delay para o computador jogar
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //sortea posição da jogada do computador
                Random random = new Random();
                int i, j;
                do {
                    i = random.nextInt(3);
                    j = random.nextInt(3);
                } while (matriz[i][j] != 0);
                //2 = 0
                matriz[i][j] = 2;
                Button button = getButton(i, j);
                button.setText("0");
                limiteJogadas++;
                if (verificarGanhador(2)) {
                    exibirMensagemVitoria(2);
                    desativarBotoes();
                } else if (limiteJogadas == 9) {
                    exibirMensagemEmpate();
                    desativarBotoes();
                } else {
                    jogadorAtual = 1;
                    textoJogador.setText("Sua vez");
                    // Ativa os botões após a jogada do computador
                    ativarBotoes();
                }
            }
        }, 1000);
    }

    //CÓDIGO PARA JOGADA DO COMPUTADOR = X SE JOGADOR ESCOLHE '0'

    //esse código primeiraJogadaPC tem que ter para iniciar o jogo
    //código praticamente igual o jogadaComputadorX, porém unica diferença é a posição do dasativarBotões,
    //se eu usasse o jogadaComputadorX para iniciar ele dava um bug e fechava a tela por conta de ter muitas ações de desativar o botão
    private void primeiraJogadaPC() {
        if (jogadorAtual == 1) { // só executa se for a vez do computador jogar
            //delay para o computador jogar
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Desativa os botões durante a jogada do computador para evitar bug do player escolher na vez do computador
                    desativarBotoes();
                    //sortea posição da jogada do computador
                    Random random = new Random();
                    int i, j;
                    do {
                        i = random.nextInt(3);
                        j = random.nextInt(3);
                    } while (matriz[i][j] != 0);
                    // Verifica se a posição já foi preenchida
                    if (matriz[i][j] == 0) {
                        matriz[i][j] = 1;
                        Button button = getButton(i, j);
                        button.setText("X");
                        limiteJogadas++;
                    }
                    if (verificarGanhador(1)) {
                        textoJogador.setText("Computador Venceu!");
                        desativarBotoes();
                    } else if (limiteJogadas == 9) {
                        exibirMensagemEmpate();
                        desativarBotoes();
                    } else {
                        jogadorAtual = 2;
                        textoJogador.setText("Sua vez");
                        // Ativa os botões após a jogada do computador
                        ativarBotoes();
                    }
                }
            }, 1000);
        }
    }

    private void jogadaComputadorX() {
        if (jogadorAtual == 1) { // só executa se for a vez do computador jogar
            // Desativa os botões durante a jogada do computador para evitar bug do player escolher na vez do computador
            desativarBotoes();
            //delay para o computador jogar
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //sortea posição da jogada do computador
                    Random random = new Random();
                    int i, j;
                    do {
                        i = random.nextInt(3);
                        j = random.nextInt(3);
                    } while (matriz[i][j] != 0);
                    // Verifica se a posição já foi preenchida
                    if (matriz[i][j] == 0) {
                        matriz[i][j] = 1;
                        Button button = getButton(i, j);
                        button.setText("X");
                        limiteJogadas++;
                    }
                    if (verificarGanhador(1)) {
                        textoJogador.setText("Computador Venceu!");
                        desativarBotoes();
                    } else if (limiteJogadas == 9) {
                        exibirMensagemEmpate();
                        desativarBotoes();
                    } else {
                        jogadorAtual = 2;
                        textoJogador.setText("Sua vez");
                        // Ativa os botões após a jogada do computador
                        ativarBotoes();
                    }
                }
            }, 1000);
        }
    }
    public void realizarJogada0(int i, int j, Button button){
        if (matriz[i][j] == 0) {
            limiteJogadas++;
            if (jogadorAtual == 2) {
                matriz[i][j] = 2;
                button.setText("0");
                desativarBotoes();
            }
            if (verificarGanhador(jogadorAtual)) {
                textoJogador.setText("Você venceu!");
                desativarBotoes();
            } else if (limiteJogadas == 9) {
                exibirMensagemEmpate();
                desativarBotoes();
            } else {
                textoJogador.setText("Computador jogando");
                jogadorAtual = (jogadorAtual == 1) ? 2 : 1;
                jogadaComputadorX();
            }
        }
    }



    private Button getButton(int i, int j) {
        switch (i) {
            case 0:
                switch (j) {
                    case 0:
                        return button11;
                    case 1:
                        return button12;
                    case 2:
                        return button13;
                }
            case 1:
                switch (j) {
                    case 0:
                        return button21;
                    case 1:
                        return button22;
                    case 2:
                        return button23;
                }
            case 2:
                switch (j) {
                    case 0:
                        return button31;
                    case 1:
                        return button32;
                    case 2:
                        return button33;
                }
            default:
                return null;
        }
    }

    private boolean verificarGanhador(int jogador) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[i][0] == jogador && matriz[i][1] == jogador && matriz[i][2] == jogador) {
                return true;
            }
            if (matriz[0][i] == jogador && matriz[1][i] == jogador && matriz[2][i] == jogador) {
                return true;
            }
        }
        if (matriz[0][0] == jogador && matriz[1][1] == jogador && matriz[2][2] == jogador) {
            return true;
        }
        if (matriz[0][2] == jogador && matriz[1][1] == jogador && matriz[2][0] == jogador) {
            return true;
        }
        return false;
    }

    private void exibirMensagemVitoria(int jogador) {
        if (jogador == 1) {
            textoJogador.setText("Você venceu!");
        } else {
            textoJogador.setText("O computador venceu!");
        }
    }

    private void exibirMensagemEmpate() {
        textoJogador.setText("Velha!");
    }

    private void desativarBotoes() {
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

    private void ativarBotoes() {
        button11.setEnabled(true);
        button12.setEnabled(true);
        button13.setEnabled(true);
        button21.setEnabled(true);
        button22.setEnabled(true);
        button23.setEnabled(true);
        button31.setEnabled(true);
        button32.setEnabled(true);
        button33.setEnabled(true);
    }

    private void reiniciarJogo() {
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
        textoJogador.setText("Sua vez");
        jogadorAtual = 1;
        limiteJogadas = 0;
    }

    private void reiniciarComputador0() {
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
        textoJogador.setText("Computador jogando");
        jogadorAtual = 1;
        limiteJogadas = 0;
        jogadaComputadorX();
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