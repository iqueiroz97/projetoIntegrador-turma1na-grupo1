import java.util.Random;
import java.util.Scanner;

public class Utils {

    Random aleatorio = new Random();
    Scanner interacao = new Scanner(System.in);

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
    public void banner2() {

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
                                                                                   \s
                                  PRESSIONE <ENTER> PARA INICIAR                   \s
                """);

        interacao.nextLine();
    }

    public void retornar() {
        System.out.println("\nPRESSIONE <ENTER> PARA RETORNAR");
        interacao.nextLine();
    }

    //    Menu do jogo
    //    TODO: Implementar o restante da lógica
    public void mostraMenu() {

        System.out.println("""
                    MENU
                     \s
                1 - Instruções\r
                2 - Jogar\r
                3 - Créditos\r
                4 - Sair""");
    }

    public void opcoesMenu(int opcaoSelecionada) {

        switch (opcaoSelecionada) {
            case 1 -> instrucoes();
            case 2 -> jogar();
            case 3 -> creditos();
            case 4 -> sair();
            default -> System.out.println("\nOPÇÃO INVÁLIDA\n");
        }
    }

    //    TODO: Pensar nas instruções do jogo
    public void instrucoes() {

        System.out.println("\nPara jogar este jogo você deve...");
        retornar();
    }

    //    TODO: Elaborar inicialização do jogo
    public void jogar() {

        System.out.println("\nIniciar jogo");
        retornar();
    }

    //    TODO: Pensar melhor nos créditos
    public void creditos() {

        System.out.println("""
                
                AUTORES DO JOGO\r
                
                ROTEIRO:\r
                Igor Queiroz\r
                
                DESENVOLVIMENTO:\r
                Breno Rios\r
                Igor Queiroz\r
                Lucas Serafim\r
                Rafael Batista""");
        retornar();
    }

    //    TODO: Elaborar lógica de encerramento
    public void sair() {

        System.out.println("\nEncerrando jogo...");
    }

    //    ALEATORIEDADE
    public int jogaDado() {

        int selecionaLado;
        selecionaLado = aleatorio.nextInt(1, 7);
        return selecionaLado;
    }
}
