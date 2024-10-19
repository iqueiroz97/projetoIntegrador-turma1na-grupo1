import java.util.*;

public class Utils {
    Scanner entrada = new Scanner(System.in);

    //    VARIÁVEIS
    private final String[] opcoes = {"a) ", "b) ", "c) ", "d) ", "e) "};
    private final boolean[] statusPerguntas = new boolean[5];
    //    private String enunciadoPergunta, alternativa1, alternativa2, alternativa3, alternativa4;
    private String alternativaCorreta;
    private int posicaoAlternativaCorreta;
    private int contadorRespostaCorreta;
    private int contadorRespostaIncorreta;
    private int encerraGame;

    public int getEncerraGame() {
        return encerraGame;
    }

    //    MENU
    //    O código morse abaixo do banner diz "Terror no Espaço"
    public void banner() {
        System.out.print("""
                
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
                                  PRESSIONE <ENTER> PARA INICIAR""");

        entrada.nextLine();
    }
    //    https://manytools.org/hacker-tools/ascii-banner/
    //    Fonte: Alligator
    /*
        Alligator by Simon Bradley <syb3@aber.ac.uk>
        17th June, 1994
    */

    public String selecionaOpcao() {
        System.out.print("\nSELECIONE UMA OPÇÃO: ");
        return entrada.next().toLowerCase();
    }

    public void interacao(String acao) {
        entrada.nextLine();

        if (acao.equalsIgnoreCase("retornar")) {
            System.out.print("\nPRESSIONE <ENTER> PARA RETORNAR");
        } else if (acao.equalsIgnoreCase("prosseguir")) {
            System.out.print("\nPRESSIONE <ENTER> PARA PROSSEGUIR");
        } else {
            System.out.print("\nAÇÃO INVÁLIDA!");
        }

        entrada.nextLine();
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

    public void mostraMenu() {
        System.out.println("""
                
                ::::    ::::  :::::::::: ::::    ::: :::    :::\s
                +:+:+: :+:+:+ :+:        :+:+:   :+: :+:    :+:\s
                +:+ +:+:+ +:+ +:+        :+:+:+  +:+ +:+    +:+\s
                +#+  +:+  +#+ +#++:++#   +#+ +:+ +#+ +#+    +:+\s
                +#+       +#+ +#+        +#+  +#+#+# +#+    +#+\s
                #+#       #+# #+#        #+#   #+#+# #+#    #+#\s
                ###       ### ########## ###    ####  ######## \s
                """);

        System.out.println("""
                1 - INSTRUÇÕES\r
                2 - JOGAR\r
                3 - CRÉDITOS\r
                4 - ESTATÍSTICAS\r
                5 - SAIR""");

        opcoesMenu(selecionaOpcao());
    }

    public void opcoesMenu(String opcaoSelecionada) {
        switch (opcaoSelecionada) {
            case "1" -> instrucoes();
            case "2" -> jogar();
            case "3" -> creditos();
            case "4" -> estatisticas();
            case "5" -> sair();
            default -> System.out.println("\nOPÇÃO INVÁLIDA!");
        }
    }

    //    TODO: Pensar nas instruções do jogo
    public void instrucoes() {
        System.out.print("""
                
                ::::::::::: ::::    :::  :::::::: ::::::::::: :::::::::  :::    :::  ::::::::   ::::::::  :::::::::: :::::::: \s
                    :+:     :+:+:   :+: :+:    :+:    :+:     :+:    :+: :+:    :+: :+:    :+: :+:    :+: :+:       :+:    :+:\s
                    +:+     :+:+:+  +:+ +:+           +:+     +:+    +:+ +:+    +:+ +:+        +:+    +:+ +:+       +:+       \s
                    +#+     +#+ +:+ +#+ +#++:++#++    +#+     +#++:++#:  +#+    +:+ +#+        +#+    +:+ +#++:++#  +#++:++#++\s
                    +#+     +#+  +#+#+#        +#+    +#+     +#+    +#+ +#+    +#+ +#+        +#+    +#+ +#+              +#+\s
                    #+#     #+#   #+#+# #+#    #+#    #+#     #+#    #+# #+#    #+# #+#    #+# #+#    #+# #+#       #+#    #+#\s
                ########### ###    ####  ########     ###     ###    ###  ########   ########   ########  ########## ######## \s
                """);

        System.out.println("\nPara jogar este jogo você deve...");
        interacao("retornar");
    }

    //    TODO: Elaborar inicialização do jogo
    public void jogar() {
        System.out.println("\nINICIA A PARTIDA");

        //    Teste de perguntas
        boolean encerraPartida = false;

        while (!encerraPartida) {
            //  Pergunta 1
            do {
                statusPerguntas[0] = checaResposta(mostraPergunta(listaPerguntas().get(0)), selecionaOpcao());
            } while (!statusPerguntas[0]);

//                  Pergunta individual
//            do {
//                statusPergunta1 = checaResposta(mostraPergunta(listaPerguntas.get(0)), selecionaOpcao());
//            } while (!statusPergunta1);

            interacao("prosseguir");

            System.out.println("\nENCERRANDO PARTIDA...");
            encerraPartida = true;
        }
    }

    //    TODO: Pensar melhor nos créditos
    public void creditos() {
        System.out.print("""
                
                    :::     :::    ::: ::::::::::: ::::::::  :::::::::  :::::::::: :::::::: \s
                  :+: :+:   :+:    :+:     :+:    :+:    :+: :+:    :+: :+:       :+:    :+:\s
                 +:+   +:+  +:+    +:+     +:+    +:+    +:+ +:+    +:+ +:+       +:+       \s
                +#++:++#++: +#+    +:+     +#+    +#+    +:+ +#++:++#:  +#++:++#  +#++:++#++\s
                +#+     +#+ +#+    +#+     +#+    +#+    +#+ +#+    +#+ +#+              +#+\s
                #+#     #+# #+#    #+#     #+#    #+#    #+# #+#    #+# #+#       #+#    #+#\s
                ###     ###  ########      ###     ########  ###    ### ########## ######## \s
                """);

        System.out.println("""
                
                Breno Rios\r
                Igor Queiroz\r
                Lucas Serafim\r
                Rafael Batista""");

        interacao("retornar");
    }

    public void estatisticas() {
        System.out.print("""
                
                :::::::::: :::::::: ::::::::::: ::: ::::::::::: ::::::::::: :::::::: ::::::::::: ::::::::::: ::::::::      :::      :::::::: \s
                :+:       :+:    :+:    :+:   :+: :+:   :+:         :+:    :+:    :+:    :+:         :+:    :+:    :+:   :+: :+:   :+:    :+:\s
                +:+       +:+           +:+  +:+   +:+  +:+         +:+    +:+           +:+         +:+    +:+         +:+   +:+  +:+       \s
                +#++:++#  +#++:++#++    +#+ +#++:++#++: +#+         +#+    +#++:++#++    +#+         +#+    +#+        +#++:++#++: +#++:++#++\s
                +#+              +#+    +#+ +#+     +#+ +#+         +#+           +#+    +#+         +#+    +#+        +#+     +#+        +#+\s
                #+#       #+#    #+#    #+# #+#     #+# #+#         #+#    #+#    #+#    #+#         #+#    #+#    #+# #+#     #+# #+#    #+#\s
                ########## ########     ### ###     ### ###     ########### ########     ###     ########### ########  ###     ###  ######## \s
                """);

        System.out.println("\nRESPOSTAS CORRETAS: " + contadorRespostaCorreta);
        System.out.println("RESPOSTAS INCORRETAS: " + contadorRespostaIncorreta);

        interacao("retornar");
    }

    public void sair() {
        int confirmaEncerramento;

        do {
            confirmaEncerramento = confirmarAcao();

            if (confirmaEncerramento == 1) {
                encerraGame = 5;

                System.out.print("""
                        
                        :::::::::  :::   ::: ::::::::::\s
                        :+:    :+: :+:   :+: :+:       \s
                        +:+    +:+  +:+ +:+  +:+       \s
                        +#++:++#+    +#++:   +#++:++#  \s
                        +#+    +#+    +#+    +#+       \s
                        #+#    #+#    #+#    #+#       \s
                        #########     ###    ##########\s
                        """);
            } else if (confirmaEncerramento == 0) {
                mostraMenu();
            } else {
                System.out.println("\nOPÇÃO INVÁLIDA! TENTE NOVAMENTE.");
            }
        } while (confirmaEncerramento == -1);
    }

    //    ALEATORIEDADE
    public void embaralhaLista(Object lista) {
        Collections.shuffle((List<?>) lista);
    }

    //    VALIDAÇÃO
    public boolean checaResposta(ArrayList<String> pergunta, String respostaJogador) {
        int posicaoAlternativaCorreta = pergunta.indexOf(this.alternativaCorreta);

        int posicaoRespostaJogador = switch (respostaJogador) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            default -> -1;
        };

        if (posicaoAlternativaCorreta == posicaoRespostaJogador) {
            contadorRespostaCorreta += 1;
            System.out.println("\nRESPOSTA CORRETA!");
            return true;
        } else if (posicaoRespostaJogador == -1) {
            System.out.println("\nRESPOSTA INVÁLIDA!");
            return false;
        } else {
            contadorRespostaIncorreta += 1;
            System.out.println("\nRESPOSTA INCORRETA!");
            return false;
        }
    }

    //    QUESTÕES
    public ArrayList<ArrayList<String>> listaPerguntas() {
        ArrayList<ArrayList<String>> perguntas = new ArrayList<>();

        perguntas.add(pergunta1());
        perguntas.add(pergunta2());
        perguntas.add(pergunta3());
        perguntas.add(pergunta4());
        perguntas.add(pergunta5());

//        embaralhaLista(perguntas);

        return perguntas;
    }

    public ArrayList<String> mostraPergunta(ArrayList<String> pergunta) {
        //  Mostra o enunciado da pergunta e depois remove ele do Array "pergunta"
        if (pergunta.size() > 5) {
            System.out.println(pergunta.get(0));
            pergunta.remove(0);
        }

        embaralhaLista(pergunta);

        //  Mostra a lista de alternativas, onde as opções de "a" até "e" ficam fixas
        for (int i = 0; i < pergunta.size(); i++) {
            System.out.println(opcoes[i] + pergunta.get(i));
        }

        return pergunta;
    }

    //    Questionário Banco de Dados (Relacionamento)
    public ArrayList<String> pergunta1() {
        ArrayList<String> alternativas = new ArrayList<>();

        String enunciadoPergunta = """
                
                Qual é o tipo de relacionamento onde uma entidade pode estar
                associada a várias outras, mas essas estão associadas a apenas
                uma entidade?
                """;

        //  O enunciado sempre entra na posição zero do Array
        alternativas.add(enunciadoPergunta);

        String alternativa1 = "Relacionamento Um-para-Um (1:1)";
        String alternativa2 = "Relacionamento Hierárquico";
        String alternativa3 = "Relacionamento Muitos-para-Muitos (N:N)";
        String alternativa4 = "Relacionamento Circular";
        alternativaCorreta = "Relacionamento Um-para-Muitos (1:N)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorreta);

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
        alternativaCorreta = "Usando uma tabela de junção (ou associativa) que contém chaves estrangeiras de ambas as tabelas";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorreta);

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
        alternativaCorreta = "Uma coluna ou grupo de colunas que faz referência a uma chave primária em outra tabela";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorreta);

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
        alternativaCorreta = "Terceira Forma Normal (3FN)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorreta);

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
        alternativaCorreta = "Um número de identificação de cidadão e o próprio cidadão";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(this.alternativaCorreta);

        return alternativas;
    }
}
