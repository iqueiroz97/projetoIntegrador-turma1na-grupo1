/*
 * Grupo 1 - Turma 1NA
 *
 * Breno Rios Cordeiro
 * Igor Pereira de Queiroz
 * Lucas Oliveira Serafim
 * Rafael Batista da Silva
 *
 * */

public class Main {
    public static void main(String[] args) {
        Utils utils = new Utils();

        do {
            utils.iniciaJogo();
        } while (!utils.getEncerraGame());
    }
}