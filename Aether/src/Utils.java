import java.util.*;

public class Utils {

    Scanner interacao = new Scanner(System.in);

    //    QUESTÕES / ALTERNATIVAS
    String[] opcoes = {"a) ", "b) ", "c) ", "d) ", "e) "};

    String alternativa1, alternativa2, alternativa3, alternativa4, alternativaCorreta;
    int contadorRespostaCorreta, contadorRespostaIncorreta, posicaoAlternativaCorreta;

    String enunciadoPergunta1 = """
            
            Qual é o tipo de relacionamento onde uma entidade pode estar
            associada a várias outras, mas essas estão associadas a apenas
            uma entidade?
            """;

    //    MENU
    //    O código morse abaixo do banner diz "Terror no Espaço"

    //    https://manytools.org/hacker-tools/ascii-banner/

    //    Fonte: Alligator
    /*
        Alligator by Simon Bradley <syb3@aber.ac.uk>
        17th June, 1994
    */
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
                                  PRESSIONE <ENTER> PARA INICIAR                        \s
                """);

        interacao.nextLine();
    }

    //    Fonte: Alligator2
    /*
        Alligator2 by Daniel Wiz. AKA Merlin Greywolf <merlin@brahms.udel.edu>
        27th July, 1994
        This is the STRAIGHT version of the revised Alligator font I edited.
        It's EXACTLY like my other posted font except the tilt was taken out.
    */
    public void bannerAlternativo() {

        System.out.print("""
                    :::     :::::::::: ::::::::::: :::    ::: :::::::::: ::::::::: \s
                  :+: :+:   :+:            :+:     :+:    :+: :+:        :+:    :+:\s
                 +:+   +:+  +:+            +:+     +:+    +:+ +:+        +:+    +:+\s
                +#++:++#++: +#++:++#       +#+     +#++:++#++ +#++:++#   +#++:++#: \s
                +#+     +#+ +#+            +#+     +#+    +#+ +#+        +#+    +#+\s
                #+#     #+# #+#            #+#     #+#    #+# #+#        #+#    #+#\s
                ###     ### ##########     ###     ###    ### ########## ###    ###\s
                                                                                   \s
                      - . .-. .-. --- .-.    -. ---    . ... .--. .- -.-. ---      \s
                """);

        interacao.nextLine();
    }

    public void retornar() {
        System.out.print("\nPRESSIONE <ENTER> PARA RETORNAR");
        interacao.nextLine();
    }

    //    TODO: Implementar o restante da lógica
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
                1 - Instruções\r
                2 - Jogar\r
                3 - Créditos\r
                4 - Estatísticas\r
                5 - Sair""");
    }

    public void opcoesMenu(int opcaoSelecionada) {

        switch (opcaoSelecionada) {
            case 1 -> instrucoes();
            case 2 -> jogar();
            case 3 -> creditos();
            case 4 -> estatisticas();
            case 5 -> sair();
            default -> System.out.println("\nOPÇÃO INVÁLIDA\n");
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

        System.out.println("\nIniciar jogo");

        //    Teste de pergunta
        String respostaJogador;

        do {
            mostraPergunta(pergunta1());

            System.out.print("\nResposta: ");
            respostaJogador = interacao.next();

            if (checaResposta(respostaJogador)) {

                contadorRespostaCorreta += 1;
                System.out.println("\nRESPOSTA CORRETA!");
            } else {

                contadorRespostaIncorreta += 1;
                System.out.println("\nRESPOSTA INCORRETA! TENTE NOVAMENTE");
            }
        } while (!checaResposta(respostaJogador));

        interacao.nextLine();
        retornar();
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

        System.out.println("\nRespostas corretas: " + contadorRespostaCorreta);
        System.out.println("Respostas incorretas: " + contadorRespostaIncorreta);
        retornar();
    }

    public void sair() {

        System.out.print("""
                
                :::::::::: ::::::::::: ::::    :::\s
                :+:            :+:     :+:+:   :+:\s
                +:+            +:+     :+:+:+  +:+\s
                :#::+::#       +#+     +#+ +:+ +#+\s
                +#+            +#+     +#+  +#+#+#\s
                #+#            #+#     #+#   #+#+#\s
                ###        ########### ###    ####\s
                """);
    }

    //    ALEATORIEDADE
    public ArrayList<String> embaralha(ArrayList<String> item) {

        Collections.shuffle(item);
        return item;
    }

    //    VALIDAÇÃO
    //    TODO: Revisar lógica
    public boolean checaResposta(String respostaJogador) {

        int posicaoResposta = switch (respostaJogador.toLowerCase()) {
            case "a" -> 0;
            case "b" -> 1;
            case "c" -> 2;
            case "d" -> 3;
            case "e" -> 4;
            default -> -1;
        };

        return posicaoResposta == posicaoAlternativaCorreta;
    }

    //    QUESTÕES
    public void mostraPergunta(ArrayList<String> pergunta) {

        for (int i = 0; i < pergunta.size(); i++) {

            System.out.println(opcoes[i] + pergunta.get(i));
        }

        posicaoAlternativaCorreta = pergunta.indexOf(this.alternativaCorreta);
    }

    //    TODO: Revisar lógica
    public ArrayList<String> pergunta1() {

        ArrayList<String> alternativas = new ArrayList<>();

        /*Qual é o tipo de relacionamento onde uma entidade pode estar
        associada a várias outras, mas essas estão associadas a apenas
        uma entidade?*/

        System.out.println(enunciadoPergunta1);

        this.alternativa1 = "Relacionamento Um-para-Um (1:1)";
        this.alternativa2 = "Relacionamento Hierárquico";
        this.alternativa3 = "Relacionamento Muitos-para-Muitos (N:N)";
        this.alternativa4 = "Relacionamento Circular";
        this.alternativaCorreta = "Relacionamento Um-para-Muitos (1:N)";

        alternativas.add(this.alternativa1);
        alternativas.add(this.alternativa2);
        alternativas.add(this.alternativa3);
        alternativas.add(this.alternativa4);
        alternativas.add(this.alternativaCorreta);

        return embaralha(alternativas);
    }
}
