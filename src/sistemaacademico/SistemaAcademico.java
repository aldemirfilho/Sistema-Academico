package sistemaacademico;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaAcademico {

    public static void main(String[] args) {
        System.out.println("           Bem vindo ao Sistema de Gestão de Produtividade Acadêmica");
        System.out.println("1. Cadastrar Colaborador;");
        System.out.println("2. Lançar publicação;");
        System.out.println("3. Editar Publicações;");
        System.out.println("4. Criar ou editar projetos;");
        System.out.println("5. Consultar colaborador;");
        System.out.println("6. Consultar projeto;");
        System.out.println("7. Produção acadêmica do Laboratório;");

        Scanner input = new Scanner(System.in);
        int opcao = 0, cont_aux = 0;

        String lixo = new String();

        java.util.ArrayList<Colaborador> Colaboradores = new java.util.ArrayList<Colaborador>();
        java.util.ArrayList<Publicacao> Publicacoes = new java.util.ArrayList<Publicacao>();
        java.util.ArrayList<Projeto> Projetos = new java.util.ArrayList<Projeto>();

        while (opcao != 10) {
            System.out.println();
            System.out.print("Digite a opção que deseja.\nResp: ");
            while (cont_aux == 0) {
                try {
                    opcao = input.nextInt();
                    cont_aux++;
                } catch (InputMismatchException Exception) {
                    lixo = input.nextLine();
                    System.out.println();
                    System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite a opção que deseja.\nResp: ");
                }
            }
            cont_aux = 0;
            System.out.println();
            if (opcao == 1) {
                lixo = input.nextLine();
                Colaborador colaborador = new Colaborador();
                Colaboradores = colaborador.creat_colaborador(colaborador, Colaboradores);
            }
            if (Colaboradores.size() != 0) {
                if (opcao == 2) {
                    lixo = input.nextLine();
                    Publicacao publicacao = new Publicacao();
                    publicacao.lancar_pub(Publicacoes, Colaboradores, Projetos);
                }
                if (opcao == 3) {
                    lixo = input.nextLine();
                    Publicacao publicacao1 = new Publicacao();
                    publicacao1.edit_pub(Publicacoes, Colaboradores, Projetos);
                }
                if (opcao == 4) {
                    int resp = 0;
                    System.out.print("Você deseja criar ou editar projetos?\nCriar: Digite 1\nEditar: Digite 2\nResp: ");
                    while (cont_aux == 0) {
                        try {
                            resp = input.nextInt();
                            cont_aux++;
                        } catch (InputMismatchException Exception) {
                            lixo = input.nextLine();
                            System.out.println();
                            System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nVocê deseja criar ou editar projetos?\nCriar: Digite 1\nEditar: Digite 2\nResp: ");
                        }
                    }
                    cont_aux = 0;
                    System.out.println();
                    if (resp == 1) {
                        lixo = input.nextLine();
                        Projeto projeto = new Projeto();
                        projeto.create_project(Projetos, Colaboradores);
                    }
                    if (resp == 2) {
                        if (Projetos.size() == 0) {
                            System.out.println("Ainda não existem projetos em atividade");
                        } else {
                            lixo = input.nextLine();
                            Projeto projeto = new Projeto();
                            projeto.edit_project(Projetos, Colaboradores, Publicacoes);
                        }
                    }
                }
                if (opcao == 5) {
                    lixo = input.nextLine();
                    Colaborador colaborador = new Colaborador();
                    colaborador.consultar_colab(Publicacoes, Colaboradores, Projetos);
                }
                if (opcao == 6) {
                    lixo = input.nextLine();
                    Projeto projeto = new Projeto();
                    projeto.consultar_proj(Publicacoes, Projetos);
                }
                if (opcao == 7) {
                    int r, cont = 0;
                    System.out.println("Relatório de Produção Acadêmica do Laboratório");
                    System.out.println("Número de colaboradores: " + Colaboradores.size());
                    for (r = 0; r < Projetos.size(); r++) {
                        if (Projetos.get(r).getStatus().equals("em elaboracao")) {
                            cont++;
                        }
                    }
                    System.out.println("Número de projetos em elaboração: " + cont);
                    cont = 0;
                    for (r = 0; r < Projetos.size(); r++) {
                        if (Projetos.get(r).getStatus().equals("em andamento")) {
                            cont++;
                        }
                    }
                    System.out.println("Número de projetos em andamento: " + cont);
                    cont = 0;
                    for (r = 0; r < Projetos.size(); r++) {
                        if (Projetos.get(r).getStatus().equals("concluido")) {
                            cont++;
                        }
                    }
                    System.out.println("Número de projetos concluidos: " + cont);
                    cont = 0;
                    System.out.println("Número Total de projetos: " + Projetos.size());
                    System.out.println("Número de produção acadêmica por tipo de produção:\nPublicações: " + Publicacoes.size());
                    System.out.println();
                }
            } else {
                System.out.println("Nenhum colaborador foi cadastrado.");
            }
            System.out.println();
            System.out.println("1. Cadastrar Colaborador;");
            System.out.println("2. Lançar publicação;");
            System.out.println("3. Editar Publicações;");
            System.out.println("4. Criar ou editar projetos;");
            System.out.println("5. Consultar colaborador;");
            System.out.println("6. Consultar projeto;");
            System.out.println("7. Produção acadêmica do Laboratório;");
            //fim.
        }
    }
}
