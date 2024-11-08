import java.util.*;

public class Utils {
    // VARIÁVEIS E OBJETOS
    Scanner entrada = new Scanner(System.in);

    // RELACIONADOS A PERGUNTAS
    private final String[] opcoes = {"a) ", "b) ", "c) ", "d) ", "e) "};
    ArrayList<ArrayList<String>> perguntas = new ArrayList<>();
    private String enunciado;
    ArrayList<String> alternativasCorretas = new ArrayList<>();
    private String alternativaCorretaPergunta1, alternativaCorretaPergunta2, alternativaCorretaPergunta3,
            alternativaCorretaPergunta4, alternativaCorretaPergunta5;
    private int posicaoAlternativaCorreta;

    // RELACIONADOS A ESTATÍSTICAS
    private int contadorRespostaCorreta;
    private int contadorRespostaIncorreta;

    // CONTROLADORES
    private boolean primeiraExecucaoJogo = true;
    private boolean primeiraExecucaoPartida = true;
    private boolean encerraGame;

    // GETTERS
    public boolean getEncerraGame() {
        return encerraGame;
    }

    // BANNER
    // https://manytools.org/hacker-tools/ascii-banner/
    // Fonte: Alligator
    /*
     * Alligator by Simon Bradley <syb3@aber.ac.uk>
     * 17th June, 1994
     */
    // O código morse abaixo do banner diz "Terror no Espaço"
    public void banner() {
        limparTela();

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
                                  PRESSIONE <ENTER> PARA INICIAR""", true, 2);

        entrada.nextLine();
    }

    // UTILITÁRIOS
    public void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
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

    public void printComDelay(String texto, boolean pulaLinha, int tempo) {
        // Ajustar o tempo para imprimir mais rápido ou mais devagar
        for (int i = 0; i < texto.length(); i++) {
            System.out.print(texto.charAt(i));
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
        // Tempo restante para a questão acabar
        return tempoLimiteQuestao - (horaAtual - horaInicioQuestao);
    }

    // INICIALIZAÇÃO
    public void iniciaJogo() {
        if (primeiraExecucaoJogo) {
            banner();
            primeiraExecucaoJogo = false;
        }

        mostraMenu();
    }

    // INTERAÇÕES
    public String selecionaOpcao() {
        String opcaoSelecionada;

        do {
            System.out.print("\nSELECIONE UMA OPÇÃO: ");
            opcaoSelecionada = entrada.nextLine().toLowerCase();
        } while (opcaoSelecionada.isEmpty());

        limparTela();

        return opcaoSelecionada;
    }

    public void interacao(String acao) {
        if (acao.equalsIgnoreCase("retornar")) {
            System.out.print("\nPRESSIONE <ENTER> PARA RETORNAR");
        } else if (acao.equalsIgnoreCase("prosseguir")) {
            System.out.print("\nPRESSIONE <ENTER> PARA PROSSEGUIR");
        } else {
            System.out.print("\nAÇÃO INVÁLIDA!");
        }

        entrada.nextLine();
        limparTela();
    }

    public int confirmarAcao() {
        System.out.print("""
                
                DESEJA CONFIRMAR A AÇÃO?\s
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
        limparTela();

        printComDelay("""
                
                ::::    ::::  :::::::::: ::::    ::: :::    :::\s
                +:+:+: :+:+:+ :+:        :+:+:   :+: :+:    :+:\s
                +:+ +:+:+ +:+ +:+        :+:+:+  +:+ +:+    +:+\s
                +#+  +:+  +#+ +#++:++#   +#+ +:+ +#+ +#+    +:+\s
                +#+       +#+ +#+        +#+  +#+#+# +#+    +#+\s
                #+#       #+# #+#        #+#   #+#+# #+#    #+#\s
                ###       ### ########## ###    ####  ######## \s
                """, true, 1);

        System.out.println("""
                1 - INSTRUÇÕES\r
                2 - JOGAR\r
                3 - CRÉDITOS\r
                4 - STATUS\r
                5 - SAIR""");

        switch (selecionaOpcao()) {
            case "1" -> instrucoes();
            case "2" -> jogar();
            case "3" -> creditos();
            case "4" -> status();
            case "5" -> sair();
            default -> System.out.println("\nOPÇÃO INVÁLIDA!");
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
                """, true, 1);

        System.out.println("""
                
                Responda as perguntas com a alternativa correta antes do tempo acabar,
                para completar os desafios propostos durante a história do jogo.
                
                Interaja com a IA, AURA, para para resolver os desafios.
                
                Para interagir, basta seguir as instruções em tela selecionando a opção
                de acordo com o que for apresentado.""");

        interacao("retornar");
    }

    public void jogar() {
        printComDelay("#### CAPÍTULO I ####\n", true, 30);

        pausa(1000);

        printComDelay("EMERGÊNCIA NA NAVE AETHER", false, 30);
        printComDelay(".....", true, 500);

        printComDelay("""
                
                ======= O ano é 3129.
                Arkana Moovit e John Reeves estão a caminho de Nahum na nave AETHER com a missão de coletar
                uma amostra da planta SANSEVIERIA. Durante a viagem, porém, um súbito problema atinge os sistemas da nave,
                desencadeando uma série de falhas. A voz da IA da nave, AURA, ecoa pela cabine:
                """, true, 30);

        pausa(500);

        printComDelay("""
                -Falha detectada em sistemas principais. Requer-se diagnóstico e reparo imediato.
                Por favor, consultem o painel de controle para mais detalhes.
                """, true, 30);

        pausa(500);

        printComDelay("*****Arkana e John trocam um olhar preocupado.*******", true, 30);

        pausa(500);

        printComDelay("""
                
                -Eu vou cuidar da parte externa, John! Faça os reparos necessários aqui dentro.
                ****Disse Arkana.
                """, true, 30);

        pausa(500);

        printComDelay("""
                -Pode deixar, eu dou conta!
                *****Disse John""", true, 30);

        pausa(500);

        printComDelay("""
                
                Assim, com cada um assumindo a responsabilidade por uma parte da nave, Arkana lida com o exterior,
                enquanto John trabalha nos sistemas internos.
                Eles escolhem suas respectivas funções e começam suas jornadas individuais de reparo.""", true, 30);

        pausa(500);

        switch (mostrarOpcoesPersonagens()) {
            case 1 -> iniciarArkanaMoovit();
            case 2 -> iniciarJohnReeves();
            case 3 -> interacao("retornar");
            default -> interacao("invalido");
        }
    }

    // TODO: Pensar melhor nos créditos
    public void creditos() {
        printComDelay("""
                
                 ::::::::  :::::::::  :::::::::: ::::::::: ::::::::::: ::::::::::: ::::::::   :::::::: \s
                :+:    :+: :+:    :+: :+:        :+:    :+:    :+:         :+:    :+:    :+: :+:    :+:\s
                +:+        +:+    +:+ +:+        +:+    +:+    +:+         +:+    +:+    +:+ +:+       \s
                +#+        +#++:++#:  +#++:++#   +#+    +:+    +#+         +#+    +#+    +:+ +#++:++#++\s
                +#+        +#+    +#+ +#+        +#+    +#+    +#+         +#+    +#+    +#+        +#+\s
                #+#    #+# #+#    #+# #+#        #+#    #+#    #+#         #+#    #+#    #+# #+#    #+#\s
                 ########  ###    ### ########## ######### ###########     ###     ########   ######## \s
                """, true, 1);

        System.out.println("""
                
                Breno Rios\r
                Igor Queiroz\r
                Lucas Serafim\r
                Rafael Batista""");

        interacao("retornar");
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
                """, true, 1);

        System.out.println("\nRESPOSTAS CORRETAS: " + contadorRespostaCorreta);
        System.out.println("RESPOSTAS INCORRETAS: " + contadorRespostaIncorreta);

        interacao("retornar");
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
                """, true, 1);

        int confirmaEncerramento;

        do {
            confirmaEncerramento = confirmarAcao();

            if (confirmaEncerramento == 1) {
                encerraGame = true;
            } else if (confirmaEncerramento == 0) {
                mostraMenu();
            } else {
                System.out.println("\nOPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
            }
        } while (confirmaEncerramento == -1);
    }

    // ALEATORIEDADE
    // Embaralha listas de perguntas e alternativas
    public void embaralha(Object lista) {
        Collections.shuffle((List<?>) lista);
    }

    // VALIDAÇÃO
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

        // TODO: Ajustar incremento da variável de resposta incorreta caso o tempo da pergunta tenha acabado
        // Compara a posição da alternativa correta com a posição da resposta do jogador
        if ((posicaoAlternativaCorreta == posicaoRespostaJogador) && tempoRestantePergunta > 0) {
            contadorRespostaCorreta += 1;
            printComDelay("\nRESPOSTA CORRETA!", true, 30);
            return true;
        } else if (posicaoRespostaJogador == -1) {
            printComDelay("\nRESPOSTA INVÁLIDA!", true, 30);
            return false;
        } else {
            contadorRespostaIncorreta += 1;
            printComDelay("\nRESPOSTA INCORRETA!", true, 30);
            return false;
        }
    }

    // QUESTÕES
    public void fazPergunta() {
        if (!listaPerguntas().isEmpty()) {
            boolean encerraQuestao = false;
            int tentativas = 3;
            int tempoLimiteQuestao = 60; // Tempo para o jogador responder uma questão (Em segundos)

            System.out.println("\nBANCO DE DADOS INSTÁVEL!\n");
            pausa(500);
            printComDelay("VOCÊ POSSUI " + tentativas + " TENTATIVAS, OU " + tempoLimiteQuestao + " SEGUNDOS PARA " +
                    "RESPONDER A QUESTÃO.\nCASO CONTRÁRIO, UMA PARTIÇÃO DO BANCO DE DADOS IRÁ CORROMPER", false, 30);
            printComDelay("...", true, 500);

            // Inicia o cronômetro
            long tempoRestante = tempoLimiteQuestao;
            long horaInicioQuestao = System.currentTimeMillis() / 1000L; // Horário de início da questão

            while ((tempoRestante > 0 && tentativas > 0) && !encerraQuestao) {
                tempoRestante = timer(tempoLimiteQuestao, horaInicioQuestao);

                boolean respostaCorreta;
                String respostaJogador;

                System.out.println("\nTEMPO RESTANTE: " + tempoRestante);
                System.out.println("TENTATIVAS RESTANTES: " + tentativas + "\n");

                ArrayList<String> perguntaAtual = listaPerguntas().get(0);
                mostraPergunta(perguntaAtual);

                if (tempoRestante < 1) {
                    limparTela();
                    printComDelay("\nTEMPO ESGOTADO!", true, 30);
                } else {
                    respostaJogador = selecionaOpcao();
                    respostaCorreta = validaResposta(perguntaAtual, respostaJogador, tempoRestante);

                    if (respostaCorreta || tentativas == 1) {
                        if (respostaCorreta) {
                            printComDelay("\nBANCO DE DADOS ESTABILIZADO", false, 30);
                            printComDelay("...", true, 500);
                        }

                        listaPerguntas().remove(0); // Remove a pergunta atual da lista de perguntas
                        encerraQuestao = true;
                    }
                }
                tentativas--;
            }
        } else {
            printComDelay("\nBANCO DE DADOS ESTÁVEL", false, 30);
        }
        interacao("prosseguir");
    }

    // Mostra a pergunta com as alternativas embaralhadas
    public void mostraPergunta(ArrayList<String> perguntaAtual) {
        // Mostra o enunciado da pergunta e depois remove ele do Array "pergunta"
        if (perguntaAtual.size() > 5) {
            enunciado = perguntaAtual.get(0);
            perguntaAtual.remove(0);
        }

        printComDelay("AURA: " + enunciado, true, 20);

        embaralha(perguntaAtual);

        // Mostra a lista de alternativas, onde as opções de "a" até "e" ficam fixas
        for (int i = 0; i < perguntaAtual.size(); i++) {
            System.out.println(opcoes[i] + perguntaAtual.get(i));
            pausa(500);
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
                Qual é o tipo de relacionamento onde uma entidade pode estar
                associada a várias outras, mas essas estão associadas a apenas
                uma entidade?
                """;

        // O enunciado inicialmente entra na posição zero do array
        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Relacionamento Um-para-Um (1:1)";
        String alternativa2 = "Relacionamento Hierárquico";
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
                se implementa a ligação entre as tabelas no banco de dados relacional?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Através de uma chave estrangeira simples em cada tabela";
        String alternativa2 = "Por meio de uma função SQL";
        String alternativa3 = "Com triggers no banco de dados";
        String alternativa4 = "Diretamente na cláusula WHERE das consultas";
        alternativaCorretaPergunta2 = "Usando uma tabela de junção (ou associativa) que contém chaves estrangeiras de" +
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

        String alternativa1 = "Uma chave que identifica de forma única um registro na tabela";
        String alternativa2 = "Um índice que acelera as consultas";
        String alternativa3 = "Um valor nulo em uma coluna específica";
        String alternativa4 = "Uma forma de normalização de dados";
        alternativaCorretaPergunta3 = "Uma coluna ou grupo de colunas que faz referência a uma chave primária em " +
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
                Qual conceito de normalização elimina a duplicação de dados ao
                garantir que cada atributo de uma tabela dependa unicamente da
                chave primária?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Primeira Forma Normal (1FN)";
        String alternativa2 = "Segunda Forma Normal (2FN)";
        String alternativa3 = "Forma Não Normal (FNN)";
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
                Qual das opções abaixo é um exemplo de relacionamento Um-para-Um (1:1)?
                """;

        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Um autor e seus livros";
        String alternativa2 = "Um país e suas cidades";
        String alternativa3 = "Um aluno e seus cursos";
        String alternativa4 = "Um pedido e seus produtos";
        alternativaCorretaPergunta5 = "Um número de identificação de cidadão e o próprio cidadão";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorretaPergunta5);

        return alternativas;
    }

    //  SELEÇÃO DE PESONAGENS

    public int mostrarOpcoesPersonagens() {
        System.out.println("\n===== ESCOLHA SEU PERSONAGEM =====");
        System.out.println();
        System.out.println("1 – Arkana Moovit");
        System.out.println("2 – John Reeve");
        System.out.println("3 – Para voltar");

        String personagemSelecionado = selecionaOpcao();

        return switch (personagemSelecionado) {
            case "1" -> 1;
            case "2" -> 2;
            case "3" -> 3;
            default -> -1;
        };
    }

    public void iniciarArkanaMoovit() {
        String funcao = "Especialista em Ecossistemas";
        int estamina = 10;
        int inteligencia = 9;
        int habilidades = 6;
        int forca = 8;

        System.out.println("Você escolheu Arkana Moovit\n");
        pausa(1000);
        printComDelay("Iniciando com Arkana Moovit", false, 30);
        printComDelay(".....\n", true, 500);

        mostraAtributosPersonagem(funcao, estamina, inteligencia, habilidades, forca);

        interacao("prosseguir");

        historiaArkanaMoovit();
    }

    public void historiaArkanaMoovit() {
        // TODO: Implementar
    }

    public void iniciarJohnReeves() {
        String funcao = "Astronauta formado em ciência da computação através da aeronáutica.\nEspecialista em análise" +
                " de dados e decifração de padrões extraterrestres";
        int estamina = 10;
        int inteligencia = 10;
        int habilidades = 7;
        int forca = 6;

        System.out.println("Você escolheu John Reeves\n");
        pausa(1000);
        printComDelay("Iniciando com John Reeves", false, 30);
        printComDelay(".....\n", true, 500);

        mostraAtributosPersonagem(funcao, estamina, inteligencia, habilidades, forca);

        interacao("prosseguir");

        historiaJohnReeves();
    }

    public void historiaJohnReeves() {
        // TODO: Implementar
        if (!listaPerguntas().isEmpty()) {
            for (int i = 1; i <= 5; i++) {
                fazPergunta();
            }
        } else {
            printComDelay("PROBLEMAS SOLUCIONADOS!", true, 30);
        }
    }

    public void mostraAtributosPersonagem(String funcao, int estamina, int inteligencia, int habilidades, int forca) {
        System.out.println("Função: " + funcao);
        pausa(500);
        System.out.println("Nível de estamina: " + estamina);
        pausa(500);
        System.out.println("Nível de inteligência: " + inteligencia);
        pausa(500);
        System.out.println("Nível de habilidades: " + habilidades);
        pausa(500);
        System.out.println("Nível de força: " + forca);
        pausa(500);
    }
}