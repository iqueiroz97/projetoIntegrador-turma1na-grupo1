import java.util.*;

public class Utils {
    Scanner interacao = new Scanner(System.in);

    //    VARIÁVEIS
    private final String[] opcoes = {"a) ", "b) ", "c) ", "d) ", "e) "};
    private final ArrayList<ArrayList<String>> listaPerguntas = new ArrayList<>();
    private String enunciadoPergunta, alternativa1, alternativa2, alternativa3, alternativa4, alternativaCorreta;
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

        interacao.nextLine();
    }
    //    https://manytools.org/hacker-tools/ascii-banner/
    //    Fonte: Alligator
    /*
        Alligator by Simon Bradley <syb3@aber.ac.uk>
        17th June, 1994
    */

    public String selecionaOpcao() {
        System.out.print("\nSELECIONE UMA OPÇÃO: ");
        return interacao.next().toLowerCase();
    }

    public void prosseguir() {
        System.out.print("\nPRESSIONE <ENTER> PARA PROSSEGUIR");
        interacao.nextLine();
        interacao.nextLine();
    }

    public void retornar() {
        System.out.print("\nPRESSIONE <ENTER> PARA RETORNAR");
        interacao.nextLine();
        interacao.nextLine();
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
        retornar();
    }

    //    TODO: Elaborar inicialização do jogo
    public void jogar() {
        System.out.println("\nINICIA A PARTIDA");

        //    Teste de listaPerguntas
        boolean statusPergunta1;
        boolean statusPergunta2;
        boolean statusPergunta3;
        boolean statusPergunta4;
        boolean statusPergunta5;
        boolean encerraPartida = false;

        while (!encerraPartida) {
            listaPerguntas.add(pergunta1());
            listaPerguntas.add(pergunta2());
            listaPerguntas.add(pergunta3());
            listaPerguntas.add(pergunta4());
            listaPerguntas.add(pergunta5());

            embaralhaLista(listaPerguntas);

//            checaResposta(mostraPergunta(listaPerguntas.get(0)), selecionaOpcao());

            for (int i = 0; i < listaPerguntas.size(); i++) {
                checaResposta(mostraPergunta(listaPerguntas.get(i)), selecionaOpcao());
            }

//            do {
//                //  Pergunta 1
//                statusPergunta1 = checaResposta(mostraPergunta(pergunta1()), selecionaOpcao());
//            } while (!statusPergunta1);
//
//            prosseguir();
//            do {
//                //  Pergunta 2
//                statusPergunta2 = checaResposta(mostraPergunta(pergunta2()), selecionaOpcao());
//            } while (!statusPergunta2);
//
//            prosseguir();
//            do {
//                //  Pergunta 3
//                statusPergunta3 = checaResposta(mostraPergunta(pergunta3()), selecionaOpcao());
//            } while (!statusPergunta3);
//
//            prosseguir();
//            do {
//                //  Pergunta 4
//                statusPergunta4 = checaResposta(mostraPergunta(pergunta4()), selecionaOpcao());
//            } while (!statusPergunta4);
//
//            prosseguir();
//            do {
//                //  Pergunta 5
//                statusPergunta5 = checaResposta(mostraPergunta(pergunta5()), selecionaOpcao());
//            } while (!statusPergunta5);

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
        retornar();
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
        retornar();
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
    public void embaralhaLista(Object item) {
        Collections.shuffle((List<?>) item);
    }

    //    VALIDAÇÃO
    public boolean checaResposta(int posicaoAlternativaCorreta, String respostaJogador) {
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
    public int mostraPergunta(ArrayList<String> pergunta) {
        //  O enunciado é mostrado e depois é removido do Array
        System.out.println(pergunta.get(0));
        pergunta.remove(0);

        embaralhaLista(pergunta);

        for (int i = 0; i < pergunta.size(); i++) {
            System.out.println(opcoes[i] + pergunta.get(i));
        }

        return pergunta.indexOf(this.alternativaCorreta);
    }

    //    Questionário Banco de Dados (Relacionamento)
    public ArrayList<String> pergunta1() {
        ArrayList<String> alternativas = new ArrayList<>();

        enunciadoPergunta = """
                
                Qual é o tipo de relacionamento onde uma entidade pode estar
                associada a várias outras, mas essas estão associadas a apenas
                uma entidade?
                """;

        //  O enunciado sempre entra na posição zero do Array
        alternativas.add(enunciadoPergunta);

        alternativa1 = "Relacionamento Um-para-Um (1:1)";
        alternativa2 = "Relacionamento Hierárquico";
        alternativa3 = "Relacionamento Muitos-para-Muitos (N:N)";
        alternativa4 = "Relacionamento Circular";
        alternativaCorreta = "Relacionamento Um-para-Muitos (1:N)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativaCorreta);

        return alternativas;
    }

    public ArrayList<String> pergunta2() {
        ArrayList<String> alternativas = new ArrayList<>();

        enunciadoPergunta = """
                
                Em um relacionamento Muitos-para-Muitos (N:N), como geralmente
                se implementa a ligação entre as tabelas no banco de dados relacional?
                """;

        alternativas.add(enunciadoPergunta);

        alternativa1 = "Através de uma chave estrangeira simples em cada tabela";
        alternativa2 = "Por meio de uma função SQL";
        alternativa3 = "Com triggers no banco de dados";
        alternativa4 = "Diretamente na cláusula WHERE das consultas";
        alternativaCorreta = "Usando uma tabela de junção (ou associativa) que contém chaves estrangeiras de ambas as tabelas";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativaCorreta);

        return alternativas;
    }

    public ArrayList<String> pergunta3() {
        ArrayList<String> alternativas = new ArrayList<>();

        enunciadoPergunta = """
                
                Em um banco de dados relacional, o que uma chave estrangeira representa?
                """;

        alternativas.add(enunciadoPergunta);

        alternativa1 = "Uma chave que identifica de forma única um registro na tabela";
        alternativa2 = "Um índice que acelera as consultas";
        alternativa3 = "Um valor nulo em uma coluna específica";
        alternativa4 = "Uma forma de normalização de dados";
        alternativaCorreta = "Uma coluna ou grupo de colunas que faz referência a uma chave primária em outra tabela";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativaCorreta);

        return alternativas;
    }

    public ArrayList<String> pergunta4() {
        ArrayList<String> alternativas = new ArrayList<>();

        enunciadoPergunta = """
                
                Qual conceito de normalização elimina a duplicação de dados ao
                garantir que cada atributo de uma tabela dependa unicamente da
                chave primária?
                """;

        alternativas.add(enunciadoPergunta);

        alternativa1 = "Primeira Forma Normal (1FN)";
        alternativa2 = "Segunda Forma Normal (2FN)";
        alternativa3 = "Forma Não Normal (FNN)";
        alternativa4 = "Forma Normal de Boyce-Codd (FNBC)";
        alternativaCorreta = "Terceira Forma Normal (3FN)";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativaCorreta);

        return alternativas;
    }

    public ArrayList<String> pergunta5() {
        ArrayList<String> alternativas = new ArrayList<>();

        enunciadoPergunta = """
                
                Qual das opções abaixo é um exemplo de relacionamento Um-para-Um (1:1)?
                """;

        alternativas.add(enunciadoPergunta);

        alternativa1 = "Um autor e seus livros";
        alternativa2 = "Um país e suas cidades";
        alternativa3 = "Um aluno e seus cursos";
        alternativa4 = "Um pedido e seus produtos";
        alternativaCorreta = "Um número de identificação de cidadão e o próprio cidadão";

        alternativas.add(alternativa1);
        alternativas.add(alternativa2);
        alternativas.add(alternativa3);
        alternativas.add(alternativa4);
        alternativas.add(alternativaCorreta);

        return alternativas;
    }
}
