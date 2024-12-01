import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;
import javax.sound.sampled.*;

public class Utils {
    // VARIÁVEIS E OBJETOS
    Scanner entrada = new Scanner(System.in);
    int personagemEscolhido;

    // RELACIONADOS A PERGUNTAS
    private final String[] OPCOES = {"a) ", "b) ", "c) ", "d) ", "e) "};
    ArrayList<ArrayList<String>> perguntas = new ArrayList<>();
    private String enunciado;
    ArrayList<String> alternativasCorretas = new ArrayList<>();
    private String alternativaCorretaPergunta1, alternativaCorretaPergunta2, alternativaCorretaPergunta3,
            alternativaCorretaPergunta4, alternativaCorretaPergunta5;
    private int posicaoAlternativaCorreta;

    // RELACIONADOS A ESTATÍSTICAS
    private int contadorRespostaCorreta;
    private int contadorRespostaIncorreta;
    private long respostaMaisRapida;

    // CONTROLADORES
    private boolean primeiraExecucaoJogo = true;
    private boolean primeiraExecucaoPartida = true;
    private boolean tempoLimitePrimeiraExecucao = true;
    private final int TEMPO_LIMITE_QUESTAO = 60; // Tempo para o jogador responder uma questão (Em segundos)
    private final int DELAY_TEXTO_PADRAO = 40;
    private final int DELAY_TEXTO_LENTO = 500;
    private final int PAUSA_PADRAO = 500;
    private final int PAUSA_LENTA = 2000;
    private boolean partidaAtiva;
    private boolean encerraGame;

    // RELACIONADOS AO SOM
    // nostro5.wav by levelclearer -- https://freesound.org/s/259324/ -- License: Creative Commons 0
    String audioIntro = "main/resources/sounds/intro.wav";
    // Enter Key Press Mechanical Keyboard by alpinemesh -- https://freesound.org/s/627647/ -- License: Creative Commons 0
    String audioTecla = "main/resources/sounds/enter-key-press.wav";
    // Bllrr-text-loop by lulyc -- https://freesound.org/s/346118/ -- License: Creative Commons 0
    String audioTexto = "main/resources/sounds/text-loop.wav";
    // Tabletop clock ticking, original speed by ycbcr -- https://freesound.org/s/388903/ -- License: Attribution 4.0
    String audioRelogio = "main/resources/sounds/clock-ticking.wav";
    // Correct and Incorrect Chime by LaurenPonder -- https://freesound.org/s/639432/ -- License: Creative Commons 0
    String audioRespostaCorreta = "main/resources/sounds/correct-chime.wav";
    String audioRespostaIncorreta = "main/resources/sounds/incorrect-chime.wav";

    private Clip clipIntro;
    private Clip clipTecla;
    private Clip clipTexto;
    private Clip clipRelogio;
    private Clip clipRespostaCorreta;
    private Clip clipRespostaIncorreta;

    // GETTERS
    public boolean getEncerraGame() {
        return encerraGame;
    }

    // INICIALIZACÃO
    public void iniciaJogo() {
        if (!partidaAtiva) {
            if (clipIntro == null || !clipIntro.isRunning()) {
                tocarSom(audioIntro, TipoAudio.INTRO);
            }
        }

        if (primeiraExecucaoJogo) {
            banner();
            primeiraExecucaoJogo = false;
        }

        mostraMenu();
    }

    // BANNER
    // O código morse abaixo do banner diz "Terror no Espaço"
    /*
     * https://manytools.org/hacker-tools/ascii-banner/
     * Fonte: Alligator
     * Alligator by Simon Bradley <syb3@aber.ac.uk>
     * 17th June, 1994
     */
    public void banner() {
        limpaTerminal();

        printComDelay("""
                
                          :::     :::::::::: ::::::::::: :::    ::: :::::::::: :::::::::\s
                       :+: :+:   :+:            :+:     :+:    :+: :+:        :+:    :+:\s
                     +:+   +:+  +:+            +:+     +:+    +:+ +:+        +:+    +:+ \s
                   +#++:++#++: +#++:++#       +#+     +#++:++#++ +#++:++#   +#++:++#:   \s
                  +#+     +#+ +#+            +#+     +#+    +#+ +#+        +#+    +#+   \s
                 #+#     #+# #+#            #+#     #+#    #+# #+#        #+#    #+#    \s
                ###     ### ##########     ###     ###    ### ########## ###    ###     \s
                                                                                        \s
                        - . .-. .-. --- .-.    -. ---    . ... .--. .- -.-. ---         \s
                                                                                        \s
                                  PRESSIONE <ENTER> PARA INICIAR""", true, 2, true);

        entrada.nextLine();
        tocarSom(audioTecla, TipoAudio.TECLA);
    }

    // UTILITÁRIOS
    public boolean identificaWindows() {
        return System.getProperty("os.name").contains("Windows");
    }

    public String ajustaDiretorio(String diretorio) {
        if (identificaWindows()) {
            return diretorio.replace("/", "\\");
        }
        return diretorio;
    }

    public void limpaTerminal() {
        try {
            if (identificaWindows()) {
                // Comando para Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Comando para Unix/Linux/Mac
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception ignored) {
        }
    }

    // Enum para os tipos de som
    public enum TipoAudio {
        INTRO, TECLA, TEXTO, RELOGIO, RESPOSTA_CORRETA, RESPOSTA_INCORRETA
    }

    public void tocarSom(String audio, TipoAudio tipo) {
        String audioDiretorioAjustado = ajustaDiretorio(audio);

        try (InputStream caminhoEntrada = getClass().getResourceAsStream(audioDiretorioAjustado)) {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(caminhoEntrada)));

            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);

            switch (tipo) {
                case INTRO:
                    clipIntro = clip;
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
                case TECLA:
                    clipTecla = clip;
                    clip.start();
                    break;
                case TEXTO:
                    clipTexto = clip;
                    clip.start();
                    break;
                case RELOGIO:
                    clipRelogio = clip;
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                    break;
                case RESPOSTA_CORRETA:
                    clipRespostaCorreta = clip;
                    clip.start();
                    break;
                case RESPOSTA_INCORRETA:
                    clipRespostaIncorreta = clip;
                    clip.start();
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao reproduzir áudio: " + audioDiretorioAjustado, e);
        }
    }

    public void pararSom(TipoAudio tipo) {
        try {
            switch (tipo) {
                case INTRO:
                    if (clipIntro != null && clipIntro.isRunning()) {
                        clipIntro.stop();
                        clipIntro.close();
                    }
                    break;
                case TECLA:
                    if (clipTecla != null && clipTecla.isRunning()) {
                        clipTecla.stop();
                        clipTecla.close();
                    }
                    break;
                case TEXTO:
                    if (clipTexto != null && clipTexto.isRunning()) {
                        clipTexto.stop();
                        clipTexto.close();
                    }
                    break;
                case RELOGIO:
                    if (clipRelogio != null && clipRelogio.isRunning()) {
                        clipRelogio.stop();
                        clipRelogio.close();
                    }
                    break;
                case RESPOSTA_CORRETA:
                    if (clipRespostaCorreta != null && clipRespostaCorreta.isRunning()) {
                        clipRespostaCorreta.stop();
                        clipRespostaCorreta.close();
                    }
                    break;
                case RESPOSTA_INCORRETA:
                    if (clipRespostaIncorreta != null && clipRespostaIncorreta.isRunning()) {
                        clipRespostaIncorreta.stop();
                        clipRespostaIncorreta.close();
                    }
                    break;
            }
        } catch (Exception e) {
            throw new RuntimeException("Erro ao parar o som: ", e);
        }
    }

    public void printComDelay(String texto, boolean pulaLinha, int tempo, boolean menu) {
        // Ajustar o tempo para imprimir mais rápido ou mais devagar
        for (int i = 0; i < texto.length(); i++) {
            if (menu) {
                System.out.print(texto.charAt(i));
            } else {
                tocarSom(audioTexto, TipoAudio.TEXTO);
                System.out.print(texto.charAt(i));
            }
            pausa(tempo);
        }

        if (pulaLinha) {
            System.out.println();
        }
    }

    public void pausa(int tempo) {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    // TIMER
    public long timer(int tempoLimiteQuestao, long horaInicioQuestao) {
        long horaAtual = System.currentTimeMillis() / 1000L; // Hora atual do sistema
        // Retorna o tempo restante para a questão acabar
        return tempoLimiteQuestao - (horaAtual - horaInicioQuestao);
    }

    public void respostaMaisRapida(long tempoRestante) {
        if (tempoLimitePrimeiraExecucao) {
            respostaMaisRapida = TEMPO_LIMITE_QUESTAO;
            tempoLimitePrimeiraExecucao = false;
        }

        if (tempoRestante < respostaMaisRapida) {
            respostaMaisRapida = tempoRestante;
        }
    }

    // INTERAÇÕES
    public String selecionaOpcao() {
        String opcaoSelecionada;

        do {
            System.out.print("\nSELECIONE UMA OPCAO: ");
            opcaoSelecionada = entrada.nextLine().toLowerCase();
        } while (opcaoSelecionada.isEmpty());

        tocarSom(audioTecla, TipoAudio.TECLA);
        limpaTerminal();

        return opcaoSelecionada;
    }

    // Enum para os tipos de interação
    public enum TipoInteracao {
        INVALIDA, PROSSEGUIR, RETORNAR
    }

    public void interacao(TipoInteracao acao) {
        if (acao == TipoInteracao.RETORNAR) {
            System.out.print("\nPRESSIONE <ENTER> PARA RETORNAR");
        } else if (acao == TipoInteracao.PROSSEGUIR) {
            System.out.print("\nPRESSIONE <ENTER> PARA PROSSEGUIR");
        } else {
            System.out.print("\nOPCAO INVALIDA!\n");
            pausa(PAUSA_PADRAO);
            System.out.print("\nPRESSIONE <ENTER> PARA TENTAR NOVAMENTE");
        }

        entrada.nextLine();
        tocarSom(audioTecla, TipoAudio.TECLA);

        limpaTerminal();
    }

    public int confirmarAcao() {
        System.out.print("""
                
                DESEJA CONFIRMAR A ACAO?\s
                       (S)  (N)         \s
                """);

        return switch (selecionaOpcao()) {
            case "s" -> 1;
            case "n" -> 0;
            default -> -1;
        };
    }

    // MENU
    public void mostraMenu() {
        limpaTerminal();

        printComDelay("""
                
                ::::    ::::  :::::::::: ::::    ::: :::    :::\s
                +:+:+: :+:+:+ :+:        :+:+:   :+: :+:    :+:\s
                +:+ +:+:+ +:+ +:+        :+:+:+  +:+ +:+    +:+\s
                +#+  +:+  +#+ +#++:++#   +#+ +:+ +#+ +#+    +:+\s
                +#+       +#+ +#+        +#+  +#+#+# +#+    +#+\s
                #+#       #+# #+#        #+#   #+#+# #+#    #+#\s
                ###       ### ########## ###    ####  ######## \s
                """, true, 1, true);

        System.out.println("""
                
                1 - INSTRUCOES\r
                2 - JOGAR\r
                3 - CREDITOS\r
                4 - STATUS\r
                5 - SAIR""");

        switch (selecionaOpcao()) {
            case "1" -> instrucoes();
            case "2" -> jogar();
            case "3" -> creditos();
            case "4" -> status();
            case "5" -> sair();
            default -> System.out.println("\nOPCAO INVALIDA!");
        }
    }

    public void instrucoes() {
        printComDelay("""
                
                ::::::::::: ::::    :::  :::::::: ::::::::::: :::::::::  :::    :::  ::::::::   ::::::::  :::::::::: :::::::: \s
                    :+:     :+:+:   :+: :+:    :+:    :+:     :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:       :+:    :+:\s
                    +:+     :+:+:+  +:+ +:+           +:+     +:+    +:+ +:+    +:+ +:+        +:+    +:+ +:+       +:+       \s
                    +#+     +#+ +:+ +#+ +#++:++#++    +#+     +#++:++#:  +#+    +:+ +#+        +#+    +:+ +#++:++#  +#++:++#++\s
                    +#+     +#+  +#+#+#        +#+    +#+     +#+    +#+ +#+    +#+ +#+        +#+    +#+ +#+              +#+\s
                    #+#     #+#   #+#+# #+#    #+#    #+#     #+#    #+# #+#    #+# #+#    #+# #+#    #+# #+#       #+#    #+#\s
                ########### ###    ####  ########     ###     ###    ###  ########   ########   ########  ########## ######## \s
                """, true, 1, true);
        pausa(PAUSA_PADRAO);

        System.out.println("""
                
                Responda as perguntas com a alternativa correta antes do tempo acabar,
                para completar os desafios propostos durante a historia do jogo.
                
                Interaja com a IA, AURA, para para resolver os desafios.
                
                Para interagir, basta seguir as instrucoes em tela selecionando a opcao
                de acordo com o que for apresentado.""");
        pausa(PAUSA_PADRAO);

        interacao(TipoInteracao.RETORNAR);
    }

    public void jogar() {
        pararSom(TipoAudio.INTRO);

        partidaAtiva = true;
        primeiraExecucaoPartida = true;

        listaPerguntas();

        historiaCapitulo1();
        interacao(TipoInteracao.PROSSEGUIR);

        status();

        partidaAtiva = false;
    }

    public void creditos() {
        printComDelay("""
                
                 ::::::::  :::::::::  :::::::::: ::::::::: ::::::::::: ::::::::::: ::::::::   :::::::: \s
                :+:    :+: :+:    :+: :+:        :+:    :+:    :+:         :+:    :+:    :+: :+:    :+:\s
                +:+        +:+    +:+ +:+        +:+    +:+    +:+         +:+    +:+    +:+ +:+       \s
                +#+        +#++:++#:  +#++:++#   +#+    +:+    +#+         +#+    +#+    +:+ +#++:++#++\s
                +#+        +#+    +#+ +#+        +#+    +#+    +#+         +#+    +#+    +#+        +#+\s
                #+#    #+# #+#    #+# #+#        #+#    #+#    #+#         #+#    #+#    #+# #+#    #+#\s
                 ########  ###    ### ########## ######### ###########     ###     ########   ######## \s
                """, true, 1, true);
        pausa(PAUSA_PADRAO);

        printComDelay("DESENVOLVIDO POR:\n", true, DELAY_TEXTO_PADRAO, true);
        pausa(PAUSA_PADRAO);

        System.out.println("Breno Rios");
        pausa(PAUSA_PADRAO);

        System.out.println("Igor Queiroz");
        pausa(PAUSA_PADRAO);

        System.out.println("Lucas Serafim");
        pausa(PAUSA_PADRAO);

        System.out.println("Rafael Batista");
        pausa(PAUSA_PADRAO);

        interacao(TipoInteracao.RETORNAR);
    }

    public void status() {
        printComDelay("""
                
                 :::::::: ::::::::::: ::: ::::::::::: :::    :::  :::::::: \s
                :+:    :+:    :+:   :+: :+:   :+:     :+:    :+: :+:    :+:\s
                +:+           +:+  +:+   +:+  +:+     +:+    +:+ +:+       \s
                +#++:++#++    +#+ +#++:++#++: +#+     +#+    +:+ +#++:++#++\s
                       +#+    +#+ +#+     +#+ +#+     +#+    +#+        +#+\s
                #+#    #+#    #+# #+#     #+# #+#     #+#    #+# #+#    #+#\s
                 ########     ### ###     ### ###      ########   ######## \s
                """, true, 1, true);
        pausa(PAUSA_PADRAO);

        System.out.println("\nRESPOSTAS CORRETAS: " + contadorRespostaCorreta);
        System.out.println("RESPOSTAS INCORRETAS: " + contadorRespostaIncorreta);
        System.out.println("RESPOSTA MAIS RAPIDA: " + respostaMaisRapida + "s");
        pausa(PAUSA_PADRAO);

        interacao(TipoInteracao.RETORNAR);
    }

    public void sair() {
        printComDelay("""
                
                 ::::::::      :::     ::::::::::: ::::::::: \s
                :+:    :+:   :+: :+:       :+:     :+:    :+:\s
                +:+         +:+   +:+      +:+     +:+    +:+\s
                +#++:++#++ +#++:++#++:     +#+     +#++:++#: \s
                       +#+ +#+     +#+     +#+     +#+    +#+\s
                #+#    #+# #+#     #+#     #+#     #+#    #+#\s
                 ########  ###     ### ########### ###    ###\s
                """, true, 1, true);

        int confirmaEncerramento;

        do {
            confirmaEncerramento = confirmarAcao();

            if (confirmaEncerramento == 1) {
                encerraGame = true;
            } else if (confirmaEncerramento == 0) {
                encerraGame = false;
            } else {
                System.out.println("\nOPCAO INVALIDA! TENTE NOVAMENTE.");
            }
        } while (confirmaEncerramento == -1);
    }

    // ALEATORIEDADE
    // Embaralha listas de perguntas e alternativas
    public void embaralha(Object lista) {
        Collections.shuffle((List<?>) lista);
    }

    // VALIDACÃO
    public boolean validaResposta(ArrayList<String> pergunta, String respostaJogador, long tempoRestantePergunta) {
        // Checa a posição da alternativa correta na pergunta atual
        for (String alternativaCorreta : listaAlternativasCorretas()) {
            for (String alternativa : pergunta) {
                if (alternativa.equals(alternativaCorreta)) {
                    posicaoAlternativaCorreta = pergunta.indexOf(alternativa);
                }
            }
        }

        // Checa a posição da resposta do jogador
        int posicaoRespostaJogador = switch (respostaJogador) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            default -> -1;
        };

        if (tempoRestantePergunta < 1) {
            posicaoRespostaJogador = -1;
        }

        // Compara a posição da alternativa correta com a posição da resposta do jogador
        if (posicaoRespostaJogador == -1) {
            tocarSom(audioRespostaIncorreta, TipoAudio.RESPOSTA_INCORRETA);

            if (tempoRestantePergunta < 1) {
                pararSom(TipoAudio.RELOGIO);

                limpaTerminal();

                System.out.println("VOCE NAO RESPONDEU A TEMPO\n");
            } else {
                printComDelay("RESPOSTA INVALIDA!\n", true, DELAY_TEXTO_PADRAO, false);
                pausa(PAUSA_PADRAO);
            }

            return false;
        } else if (posicaoAlternativaCorreta == posicaoRespostaJogador) {
            pararSom(TipoAudio.RELOGIO);
            tocarSom(audioRespostaCorreta, TipoAudio.RESPOSTA_CORRETA);

            contadorRespostaCorreta += 1;
            respostaMaisRapida(tempoRestantePergunta);

            listaPerguntas().remove(0); // Remove a pergunta atual da lista de perguntas

            printComDelay("RESPOSTA CORRETA!\n", true, DELAY_TEXTO_PADRAO, false);
            pausa(PAUSA_PADRAO);

            return true;
        } else {
            tocarSom(audioRespostaIncorreta, TipoAudio.RESPOSTA_INCORRETA);

            contadorRespostaIncorreta += 1;
            printComDelay("RESPOSTA INCORRETA!\n", true, DELAY_TEXTO_PADRAO, false);
            pausa(PAUSA_PADRAO);

            return false;
        }
    }

    // QUESTÕES
    public void fazPergunta() {
        if (!listaPerguntas().isEmpty()) {
            boolean encerraQuestao = false;
            int tentativa = 3;

            System.out.println("BANCO DE DADOS INSTAVEL!\n");
            pausa(PAUSA_PADRAO);
            printComDelay("VOCE POSSUI " + tentativa + " TENTATIVAS, OU " + TEMPO_LIMITE_QUESTAO + " SEGUNDOS PARA " +
                            "RESPONDER A QUESTAO.\nCASO CONTRARIO, UMA PARTICAO DO BANCO DE DADOS IRA CORROMPER",
                    false, DELAY_TEXTO_PADRAO, false);
            printComDelay("...", true, DELAY_TEXTO_LENTO, false);

            // Inicia o cronômetro
            long horaInicioQuestao = System.currentTimeMillis() / 1000L; // Horário de início da questão
            long tempoRestante = timer(TEMPO_LIMITE_QUESTAO, horaInicioQuestao);
            tocarSom(audioRelogio, TipoAudio.RELOGIO);

            while ((tempoRestante >= 1 || tentativa >= 1) && !encerraQuestao) {
                limpaTerminal();

                ArrayList<String> perguntaAtual = listaPerguntas().get(0);

                System.out.println("TEMPO ATUAL: " + tempoRestante + "s");
                System.out.println("TENTATIVA ATUAL: " + tentativa);

                mostraPergunta(perguntaAtual);
                String respostaJogador = selecionaOpcao();
                boolean respostaCorreta = validaResposta(perguntaAtual, respostaJogador, tempoRestante);

                tempoRestante = timer(TEMPO_LIMITE_QUESTAO, horaInicioQuestao);

                if (tempoRestante < 1) {
                    pararSom(TipoAudio.RELOGIO);
                    System.out.println("VOCE NAO RESPONDEU A TEMPO");

                    tentativa = 0;
                    encerraQuestao = true;
                } else {
                    if (respostaCorreta || tentativa == 1) {
                        pararSom(TipoAudio.RELOGIO);

                        if (respostaCorreta) {
                            printComDelay("BANCO DE DADOS ESTABILIZADO", false, DELAY_TEXTO_PADRAO, false);
                            printComDelay("...", true, DELAY_TEXTO_LENTO, false);
                        } else {
                            printComDelay("BANCO DE DADOS INSTAVEL", true, DELAY_TEXTO_PADRAO, false);
                        }

                        tentativa = 0;
                        encerraQuestao = true;
                    }
                }

                tentativa--;
            }
        } else {
            printComDelay("\nBANCO DE DADOS ESTAVEL. SEM NECESSIDADE DE MANUTENCAO", false, DELAY_TEXTO_PADRAO, false);
        }

        interacao(TipoInteracao.PROSSEGUIR);
    }

    // Mostra a pergunta com as alternativas embaralhadas
    public void mostraPergunta(ArrayList<String> perguntaAtual) {
        // Mostra o enunciado da pergunta e depois remove ele do Array "pergunta"
        if (perguntaAtual.size() > 5) {
            enunciado = perguntaAtual.get(0);
            perguntaAtual.remove(0);
        }

        System.out.println("\nAURA: " + enunciado);

        embaralha(perguntaAtual);

        // Mostra a lista de alternativas, onde as opções de "a" até "e" ficam fixas
        for (int i = 0; i < perguntaAtual.size(); i++) {
            System.out.println(OPCOES[i] + perguntaAtual.get(i));
            pausa(PAUSA_PADRAO);
        }
    }

    // Entrega a lista de perguntas de forma embaralhada
    public ArrayList<ArrayList<String>> listaPerguntas() {
        if (primeiraExecucaoPartida) {
            perguntas.add(pergunta1());
            perguntas.add(pergunta2());
            perguntas.add(pergunta3());
            perguntas.add(pergunta4());
            perguntas.add(pergunta5());

            embaralha(perguntas);

            primeiraExecucaoPartida = false;
        }

        return perguntas;
    }

    public ArrayList<String> listaAlternativasCorretas() {
        alternativasCorretas.add(alternativaCorretaPergunta1);
        alternativasCorretas.add(alternativaCorretaPergunta2);
        alternativasCorretas.add(alternativaCorretaPergunta3);
        alternativasCorretas.add(alternativaCorretaPergunta4);
        alternativasCorretas.add(alternativaCorretaPergunta5);

        return alternativasCorretas;
    }

    // Questionário Banco de Dados (Relacionamento)
    public ArrayList<String> pergunta1() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                Qual e o tipo de relacionamento onde uma entidade pode estar
                associada a varias outras, mas essas estao associadas a apenas
                uma entidade?
                """;

        // O enunciado inicialmente entra na posição zero do array
        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Relacionamento Um-para-Um (1:1)";
        String alternativa2 = "Relacionamento Hierarquico";
        String alternativa3 = "Relacionamento Muitos-para-Muitos (N:N)";
        String alternativa4 = "Relacionamento Circular";

        // A alternativa correta inicialmente entra na posição cinco do array
        alternativaCorretaPergunta1 = "Relacionamento Um-para-Muitos (1:N)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta1);

        return alternativas;
    }

    public ArrayList<String> pergunta2() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                Em um relacionamento Muitos-para-Muitos (N:N), como geralmente
                se implementa a ligacao entre as tabelas no banco de dados relacional?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Atraves de uma chave estrangeira simples em cada tabela";
        String alternativa2 = "Por meio de uma funcao SQL";
        String alternativa3 = "Com triggers no banco de dados";
        String alternativa4 = "Diretamente na clausula WHERE das consultas";
        alternativaCorretaPergunta2 = "Usando uma tabela de juncao (ou associativa) que contem chaves estrangeiras de" +
                " ambas as tabelas";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta2);

        return alternativas;
    }

    public ArrayList<String> pergunta3() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                Em um banco de dados relacional, o que uma chave estrangeira representa?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Uma chave que identifica de forma unica um registro na tabela";
        String alternativa2 = "Um indice que acelera as consultas";
        String alternativa3 = "Um valor nulo em uma coluna especifica";
        String alternativa4 = "Uma forma de normalizacao de dados";
        alternativaCorretaPergunta3 = "Uma coluna ou grupo de colunas que faz referencia a uma chave primaria em " +
                "outra tabela";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta3);

        return alternativas;
    }

    public ArrayList<String> pergunta4() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                Qual conceito de normalizacao elimina a duplicacao de dados ao
                garantir que cada atributo de uma tabela dependa unicamente da
                chave primaria?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Primeira Forma Normal (1FN)";
        String alternativa2 = "Segunda Forma Normal (2FN)";
        String alternativa3 = "Forma Nao Normal (FNN)";
        String alternativa4 = "Forma Normal de Boyce-Codd (FNBC)";
        alternativaCorretaPergunta4 = "Terceira Forma Normal (3FN)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta4);

        return alternativas;
    }

    public ArrayList<String> pergunta5() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                Qual das opcoes abaixo e um exemplo de relacionamento Um-para-Um (1:1)?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Um autor e seus livros";
        String alternativa2 = "Um pais e suas cidades";
        String alternativa3 = "Um aluno e seus cursos";
        String alternativa4 = "Um pedido e seus produtos";
        alternativaCorretaPergunta5 = "Um numero de identificacao de cidadao e o proprio cidadao";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta5);

        return alternativas;
    }

    //  SELECÃO DE PESONAGENS
    public int escolherPersonagem() {
        int personagemSelecionado = 0;

        do {
            System.out.print("""
                    
                    ======= ESCOLHA SEU PERSONAGEM =======\s
                    
                     (1) John Reeves ou (2) Arkana Moovit\s
                    """);

            switch (selecionaOpcao()) {
                case "1":
                    personagemSelecionado = 1;
                    break;
                case "2":
                    personagemSelecionado = 2;
                    break;
                default:
                    interacao(TipoInteracao.INVALIDA);
            }
        } while (personagemSelecionado == 0);

        return personagemSelecionado;
    }

    public void iniciarArkanaMoovit() {
        String funcao = "Especialista em Ecossistemas\n";
        int estamina = 10;
        int inteligencia = 9;
        int habilidades = 6;
        int forca = 8;

        System.out.println("Voce escolheu Arkana Moovit\n");
        pausa(1000);
        printComDelay("Iniciando com Arkana Moovit", false, DELAY_TEXTO_PADRAO, false);
        printComDelay(".....\n", true, DELAY_TEXTO_LENTO, false);

        mostraAtributosPersonagem(funcao, estamina, inteligencia, habilidades, forca);

        interacao(TipoInteracao.PROSSEGUIR);

        historiaArkanaMoovit();
    }

    public void historiaArkanaMoovit() {
        // TODO: Implementar
    }

    public void iniciarJohnReeves() {
        String funcao = """
                Astronauta formado em ciencia da computacao atraves da aeronautica.
                Especialista em analise de dados e decifragem de padroes extraterrestres
                """;
        int estamina = 10;
        int inteligencia = 10;
        int habilidades = 7;
        int forca = 6;

        System.out.println("Voce escolheu John Reeves\n");
        pausa(1000);

        printComDelay("Iniciando com John Reeves", false, DELAY_TEXTO_PADRAO, false);
        printComDelay(".....\n", true, DELAY_TEXTO_LENTO, false);

        mostraAtributosPersonagem(funcao, estamina, inteligencia, habilidades, forca);

        interacao(TipoInteracao.PROSSEGUIR);

        historiaJohnReeves();
    }

    public void historiaJohnReeves() {
        printComDelay("#### RECUPERANDO OS SISTEMAS INTERNOS ####", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        printComDelay("\nAnalise do banco de dados e ligacao entre sistemas", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        printComDelay("""
                        
                        Dentro da nave, John acessa o painel de controle e descobre um problema com os bancos de dados de
                        navegacao, onde as tabelas precisam ser interligadas para retomar a funcionalidade completa.""", true,
                DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("AURA apresenta uma pergunta para ajudar John a verificar as conexoes:", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        interacao(TipoInteracao.PROSSEGUIR);
        fazPergunta();

        printComDelay("""
                        Gracas a resposta de John, AURA restaura a ligacao dos dados de navegacao.
                        Ele segue as instrucoes para reparar o sistema e continua com os ajustes internos.""", true, DELAY_TEXTO_PADRAO,
                false);
        pausa(PAUSA_LENTA);

        interacao(TipoInteracao.PROSSEGUIR);

        printComDelay("#### VERIFICACAO DAS CONEXOES DE ENERGIA ####", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        printComDelay("""
                
                Com o banco de dados de navegacao parcialmente restaurado, John agora precisa verificar o sistema de suporte de vida.
                
                O painel de controle exibe novas pergunta sobre chaves e relacionamentos, que ele deve responder
                corretamente para prosseguir com o reparo:""", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        interacao(TipoInteracao.PROSSEGUIR);
        fazPergunta();
        fazPergunta();
        fazPergunta();

        printComDelay("** ENCONTRO E JORNADA FINAL: MISSAO EM NAHUM **", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        printComDelay("""
                
                Com os reparos feitos, Arkana e John se reunem e conseguem seguir para Nahum. Contudo,
                uma ultima verificacao do sistema mostra que a nave ainda possui redundancias e duplicacoes de dados
                nos registros, que precisam ser eliminadas antes de pousarem no planeta.*""", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                
                **AURA:** “John, para melhorar a eficiencia do sistema e evitar sobrecarga, recomenda-se
                realizar uma ultima normalizacao nos dados.*""", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("\nJohn consulta o painel e é apresentado a uma última pergunta:", true, DELAY_TEXTO_PADRAO,
                false);
        pausa(PAUSA_LENTA);

        interacao(TipoInteracao.PROSSEGUIR);
        fazPergunta();

        printComDelay("** OBJETIVO ALCANCADO **", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                        
                        Com o pouso em Nahum, Arkana e John finalmente podem se concentrar na coleta da planta Sansevieria.
                        Ao avançarem pela superficie do planeta, percebem que seus esforços e reparos na AETHER foram essenciais
                        para o sucesso da missao, prontos para enfrentar o próximo desafio juntos.""", true, DELAY_TEXTO_PADRAO,
                false);
        pausa(PAUSA_PADRAO);
    }

    public void historiaCapitulo1() {
        printComDelay("#### CAPITULO I ####\n", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        printComDelay("EMERGENCIA NA NAVE AETHER", false, DELAY_TEXTO_PADRAO, false);
        printComDelay(".....", true, DELAY_TEXTO_LENTO, false);

        printComDelay("\n======= O ano e 3129", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                
                Arkana Moovit e John Reeves estao a caminho de Nahum na nave AETHER com a missao de coletar
                uma amostra da planta SANSEVIERIA. Durante a viagem, porem, um subito problema atinge os sistemas da nave,
                desencadeando uma serie de falhas. A voz da IA da nave, AURA, ecoa pela cabine:
                """, true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        interacao(TipoInteracao.PROSSEGUIR);

        printComDelay("""
                - Falha detectada em sistemas principais. Requer-se diagnostico e reparo imediato.
                Por favor, consultem o painel de controle para mais detalhes.
                """, true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        interacao(TipoInteracao.PROSSEGUIR);

        printComDelay("***** Arkana e John trocam um olhar preocupado *****", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                
                - Eu vou cuidar da parte externa, John! Faca os reparos necessarios aqui dentro.
                ****Disse Arkana""", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                
                - Pode deixar, eu dou conta!
                *****Disse John""", true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        printComDelay("""
                        
                        Assim, com cada um assumindo a responsabilidade por uma parte da nave, Arkana lida com o exterior,
                        enquanto John trabalha nos sistemas internos.
                        Eles escolhem suas respectivas funcoes e comecam suas jornadas individuais de reparo.""",
                true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_LENTA);

        personagemEscolhido = escolherPersonagem();

        if (personagemEscolhido == 1) {
            iniciarJohnReeves();
        } else {
            iniciarArkanaMoovit();
        }
    }

    public void mostraAtributosPersonagem(String funcao, int estamina, int inteligencia, int habilidades, int forca) {
        pausa(PAUSA_PADRAO);

        System.out.print("Funcao: ");
        printComDelay(funcao, true, DELAY_TEXTO_PADRAO, false);
        pausa(PAUSA_PADRAO);

        System.out.println("Nivel de estamina: " + estamina);
        pausa(PAUSA_PADRAO);

        System.out.println("Nivel de inteligencia: " + inteligencia);
        pausa(PAUSA_PADRAO);

        System.out.println("Nivel de habilidades: " + habilidades);
        pausa(PAUSA_PADRAO);

        System.out.println("Nivel de forca: " + forca);
        pausa(PAUSA_PADRAO);
    }
}