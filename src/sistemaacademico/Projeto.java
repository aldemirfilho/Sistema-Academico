package sistemaacademico;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Projeto {

    private String titulo;
    private int dia_inicio;
    private int mes_inicio;
    private int ano_inicio;
    private int dia_final;
    private int mes_final;
    private int ano_final;
    private String Ag_financiadora;
    private int valor_financiado;
    private String objetivo;
    private String descricao;
    private String status;
    private boolean fim;

    Scanner input = new Scanner(System.in);

    java.util.ArrayList<String> Participantes = new java.util.ArrayList<String>();
    java.util.ArrayList<String> Publicacoes = new java.util.ArrayList<String>();

    public int search_projeto(java.util.ArrayList<Projeto> Projetos, String nome) {
        int z, local = -1;
        for (z = 0; z < Projetos.size(); z++) {
            if (Projetos.get(z).getTitulo().equals(nome)) {
                local = z;
            }
        }
        return local;
    }

    public void consultar_proj(java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Projeto> Projetos) {
        int w, t, r, q, f, local;
        Projeto aux;
        Publicacao aux2;
        String nome;
        Projeto projeto = new Projeto();
        System.out.println("Projetos existentes:");
        for (w = 0; w < Projetos.size(); w++) {
            System.out.println("-" + Projetos.get(w).getTitulo());
        }
        System.out.println("Digite o nome do projeto que você deseja ver os dados:");
        nome = input.nextLine();
        System.out.println();
        local = projeto.search_projeto(Projetos, nome);
        if (local != -1) {
            java.util.ArrayList<Publicacao> Publicacoesprint = new java.util.ArrayList<Publicacao>();
            System.out.println("Nome: " + Projetos.get(local).getTitulo());
            System.out.println("Dia de inicio: " + Projetos.get(local).getDia_inicio());
            System.out.println("Mes de inicio: " + Projetos.get(local).getMes_inicio());
            System.out.println("Ano de inicio: " + Projetos.get(local).getAno_inicio());
            System.out.println("Dia Finsl: " + Projetos.get(local).getDia_final());
            System.out.println("Mes final: " + Projetos.get(local).getMes_final());
            System.out.println("Ano Final: " + Projetos.get(local).getAno_final());
            System.out.println("Agencia Financiadora: " + Projetos.get(local).getAg_financiadora());
            System.out.println("Valor financiado: " + Projetos.get(local).getValor_financiado());
            System.out.println("Objetivo: " + Projetos.get(local).getObjetivo());
            System.out.println("Descricao: " + Projetos.get(local).getDescricao());
            System.out.println("Status: " + Projetos.get(local).getStatus());
            System.out.println("Integrantes: " + Projetos.get(local).getParticipantes());
            for (t = 0; t < Projetos.get(local).getPublicacoes().size(); t++) {
                for (r = 0; r < Publicacoes.size(); r++) {
                    if (Projetos.get(local).getPublicacoes().get(t).equals(Publicacoes.get(r).getTitulo())) {
                        Publicacoesprint.add(Publicacoes.get(r));
                    }
                }
            }
            for (q = 0; q < Publicacoesprint.size(); q++) {
                for (f = q + 1; f < Publicacoesprint.size(); f++) {
                    if (Publicacoesprint.get(f).getAno_publicacao() > Publicacoesprint.get(q).getAno_publicacao()) {
                        aux2 = Publicacoesprint.get(f);
                        Publicacoesprint.set(f, Publicacoesprint.get(q));
                        Publicacoesprint.set(q, aux2);
                    }
                }
            }
            System.out.println("Produção academica: ");
            for (t = 0; t < Publicacoesprint.size(); t++) {
                System.out.println(Publicacoesprint.get(t).getTitulo() + " - " + Publicacoesprint.get(t).getAno_publicacao());
            }
        } else if (local == -1) {
            System.out.println("Projeto não existe ou digitado incorretamente, tente novamente");
        }
    }

    public void edit_titulo(int local, String old_name, java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Publicacao> Publicacoes) {
        int w, y, q;
        System.out.println("Digite o novo titulo:");
        String new_name = input.nextLine();
        for (w = 0; w < Colaboradores.size(); w++) {
            for (y = 0; y < Colaboradores.get(w).getProjetos().size(); y++) {
                if (Colaboradores.get(w).getProjetos().get(y).equals(old_name)) {
                    Colaboradores.get(w).getProjetos().remove(y);
                    Colaboradores.get(w).getProjetos().add(new_name);
                    if (Colaboradores.get(w).getTipo_colaborador().equals("professor")) {
                        for (q = 0; q < Colaboradores.get(w).getOrientacoes().size(); q++) {
                            if (Colaboradores.get(w).getOrientacoes().get(q).equals(old_name)) {
                                Colaboradores.get(w).getOrientacoes().remove(q);
                                Colaboradores.get(w).getOrientacoes().add(new_name);
                            }
                        }
                    }
                }
                //System.out.println(Colaboradores.get(w).getProjetos());
                //System.out.println(Colaboradores.get(w).getOrientacoes());
            }
        }
        //System.out.println(old_name);
        //System.out.println(Publicacoes.size());
        //System.out.println(Publicacoes.get(0).getProjeto_ass());
        for (w = 0; w < Publicacoes.size(); w++) {
            if (Publicacoes.get(w).getProjeto_ass().equals(old_name)) {
                //System.out.println("aquiiiiiii");
                Publicacoes.get(w).setProjeto_ass(new_name);
                //System.out.println(Publicacoes.get(w).getProjeto_ass());
            }
        }
        Projetos.get(local).setTitulo(new_name);
    }

    public void edit_status(int local, java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores) {
        int cont1, cont2, z, p, cont3, cont_aux = 0;
        String lixo;
        cont1 = cont2 = cont3 = 0;
        if (Projetos.get(local).getStatus().equals("em elaboracao")) {
            if (!(Projetos.get(local).getTitulo().equals(null))) {
                System.out.println("titulo OK");
            } else {
                System.out.println("titulo vazio");
                cont1++;
            }
            if (!(Projetos.get(local).getAg_financiadora().equals(null))) {
                System.out.println("Ag. financiadora OK");
            } else {
                System.out.println("Ag. financiadora vazia");
                cont1++;
            }
            if (!(Projetos.get(local).getObjetivo().equals(null))) {
                System.out.println("Objetivo OK");
            } else {
                System.out.println("Objetivo vazio");
                cont1++;
            }
            if (!(Projetos.get(local).getDescricao().equals(null))) {
                System.out.println("Descricao OK");
            } else {
                System.out.println("Descricao vazia");
                cont1++;
            }
            if (!(Projetos.get(local).getStatus().equals(null))) {
                System.out.println("Status OK");
                System.out.println("estado: " + Projetos.get(local).getStatus());
                if (Projetos.get(local).getStatus().equals("em elaboracao")) {
                    for (z = 0; z < Projetos.get(local).getParticipantes().size(); z++) {
                        for (p = 0; p < Colaboradores.size(); p++) {
                            if (Projetos.get(local).getParticipantes().get(z).equals(Colaboradores.get(p).getNome())) {
                                if (Colaboradores.get(p).getTipo_colaborador().equals("professor")) {
                                    cont3++;
                                }
                            }
                        }
                    }
                    if (cont3 == 0) {
                        System.out.println("Adicione um professor antes de atualizar o status");
                        cont1++;
                    }
                }
            }
            if (cont1 != 0) {
                System.out.println("Para mudar o status de 'em elaboração' para 'em andamento' corrija os erros acima.");
            } else {
                System.out.println("Digite o Novo Status(em elaboracao, em andamento, concluido):");
                Projetos.get(local).setStatus(input.nextLine());
            }
        }
        if (Projetos.get(local).getStatus().equals("em andamento")) {
            if (Projetos.get(local).getPublicacoes().size() != 0) {
                int respp = 0;
                System.out.println("Deseja o Novo Status de concluido\nSIM: Digite 1\nNAO: Digite 2\nResp: ");
                while (cont_aux == 0) {
                    try {
                        respp = input.nextInt();
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDeseja o Novo Status de concluido\nSIM: Digite 1\nNAO: Digite 2\nResp: ");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
                if (respp == 1) {
                    Projetos.get(local).setStatus("concluido");
                }
            } else {
                System.out.println("obs: O projeto não possui publicações para ser concluido.");
            }
        }
        if (Projetos.get(local).getStatus().equals("concluido")) {
            System.out.println("O projeto esta concluido!");
        }
    }

    public void edit_pub(int local, java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Publicacao> Publicacoes) {
        int resp3 = 0, r, control44 = 0, w, cont_aux = 0;
        String lixo;
        Publicacao publicacao = new Publicacao();
        Projeto projeto = new Projeto();
        System.out.print("Você deseja adicionar ou remover uma publicação?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
        while (cont_aux == 0) {
            try {
                resp3 = input.nextInt();
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nVocê deseja adicionar ou remover uma publicação?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
            }
        }
        cont_aux = 0;
        System.out.println();
        if (resp3 == 1) {
            int localpub;
            lixo = input.nextLine();
            String nome_pub;
            System.out.println("Publicacoes existentes:");
            for (w = 0; w < Publicacoes.size(); w++) {
                System.out.println("-" + Publicacoes.get(w).getTitulo());
            }
            System.out.println("Digite o nome da pulicação que deseja adicionar:");
            nome_pub = input.nextLine();
            //if diferente de 0
            localpub = publicacao.search_publicacao(Publicacoes, nome_pub);
            if (localpub != -1) {
                if (Publicacoes.get(localpub).getProjeto_ass().equals("0")) {
                    if (Projetos.get(local).getStatus().equals("em andamento")) {
                        Publicacoes.get(localpub).setProjeto_ass(Projetos.get(local).getTitulo());
                        Projetos.get(local).getPublicacoes().add(nome_pub);
                    } else {
                        System.out.println("O projeto não possui status 'em andamento', tente novamente.");
                    }
                } else {
                    int resp31 = 0, x, z = 0, localpub2;
                    String old_proj;
                    System.out.println("Esta publicação ja está associada a um projeto, deseja mudar a associação?\nSIM: Digite 1\nNAO: Digite 2\nResp: ");
                    while (cont_aux == 0) {
                        try {
                            resp31 = input.nextInt();
                            cont_aux++;
                        } catch (InputMismatchException Exception) {
                            lixo = input.nextLine();
                            System.out.println();
                            System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nEsta publicação ja está associada a um projeto, deseja mudar a associação?\nSIM: Digite 1\nNAO: Digite 2\nResp: ");
                        }
                    }
                    cont_aux = 0;
                    lixo = input.nextLine();
                    old_proj = Publicacoes.get(localpub).getProjeto_ass();
                    if (resp31 == 1) {
                        localpub2 = projeto.search_projeto(Projetos, old_proj);
                        if (Projetos.get(local).getStatus().equals("em andamento")) {
                            if (localpub2 != -1) {
                                for (z = 0; z < Projetos.get(localpub2).getPublicacoes().size(); z++) {
                                    if (Projetos.get(localpub2).getPublicacoes().get(z).equals(nome_pub)) {
                                        Projetos.get(localpub2).getPublicacoes().remove(z);
                                    }
                                }
                            }
                            Publicacoes.get(localpub).setProjeto_ass(Projetos.get(local).getTitulo());
                            Projetos.get(local).getPublicacoes().set(z, nome_pub);
                        } else {
                            System.out.println("O projeto não possui status 'em andamento'. Operaçaõ não concluida.");
                        }
                    }
                    if (resp31 == 2) {
                        System.out.println("Associação mantida");
                    }
                }
            } else if (localpub == -1) {
                System.out.println("Publicacao não encontrada, tente novamente.");
            }
        }
        if (resp3 == 2) {
            lixo = input.nextLine();
            int s, e, control452, localpub;
            control452 = 0;
            String nome_pub;
            System.out.println("Publicacoes relacionadas :");
            for (w = 0; w < Projetos.get(local).getPublicacoes().size(); w++) {
                System.out.println("-" + Projetos.get(local).getPublicacoes().get(w));
            }
            System.out.println("Digite o nome da publicação que deseja remover:");
            nome_pub = input.nextLine();
            localpub = publicacao.search_publicacao(Publicacoes, nome_pub);
            if (localpub != -1) {
                for (e = 0; e < Projetos.get(local).getPublicacoes().size(); e++) {
                    if (Projetos.get(local).getPublicacoes().get(e).equals(nome_pub)) {
                        Projetos.get(local).getPublicacoes().remove(e);
                        Publicacoes.get(localpub).setProjeto_ass("0");
                        control452 = 1;
                    } else if ((e == Projetos.get(local).getPublicacoes().size() - 1) && (control452 == 0)) {
                        System.out.println("Essa publicação não faz parte deste projeto.");
                    }
                }
            } else if (localpub == -1) {
                System.out.println("Publicacao não encontrada, tente novamente.");
            }
        }
    }

    public void edit_participantes(int local, java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Publicacao> Publicacoes) {
        int resp32 = 0, e, r, t, u, w, control462, cont_aux2 = 0;
        String lixo;
        control462 = 0;
        Colaborador colaborador = new Colaborador();
        Projeto projeto = new Projeto();
        System.out.print("Você deseja adicionar ou remover uma participante?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
        while (cont_aux2 == 0) {
            try {
                resp32 = input.nextInt();
                cont_aux2++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nVocê deseja adicionar ou remover uma participante?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
            }
        }
        cont_aux2 = 0;
        if (resp32 == 1) {
            int localcolab;
            lixo = input.nextLine();
            String nome;
            System.out.println("Colaboradores existentes:");
            for (w = 0; w < Colaboradores.size(); w++) {
                System.out.println("-" + Colaboradores.get(w).getNome());
            }
            System.out.println("Digite o nome do colaborador que deseja adicionar: ");
            nome = input.nextLine();
            localcolab = colaborador.search_colaborador(Colaboradores, nome);
            if (localcolab != -1) {
                for (r = 0; r < Projetos.get(local).getParticipantes().size(); r++) {
                    if (Projetos.get(local).getParticipantes().get(r).equals(nome)) {
                        System.out.println("O colaborador já faz parte do projeto");
                        control462 = 1;
                    }
                }
                if (control462 == 0) {
                    int localproj;
                    int cont_aux = 0;
                    String proj;
                    if (Colaboradores.get(localcolab).getTipo_colaborador().equals("aluno de graduacao")) {
                        for (t = 0; t < Colaboradores.get(localcolab).getProjetos().size(); t++) {
                            proj = Colaboradores.get(localcolab).getProjetos().get(t);
                            localproj = projeto.search_projeto(Projetos, proj);
                            if (localproj != -1) {
                                if (Projetos.get(localproj).getStatus().equals("em andamento")) {
                                    cont_aux++;
                                }
                            }
                        }
                        if ((Projetos.get(local).getStatus().equals("em andamento")) && (cont_aux >= 2)) {
                            System.out.println("O colaborador é um aluno de graduação, e ja está em dois projetos em andamento");
                        }
                        if ((Projetos.get(local).getStatus().equals("em andamento")) && (cont_aux < 2)) {
                            Projetos.get(local).getParticipantes().add(nome);
                            Colaboradores.get(localcolab).getProjetos().add(Projetos.get(local).getTitulo());
                        } else {
                            Projetos.get(local).getParticipantes().add(nome);
                            Colaboradores.get(localcolab).getProjetos().add(Projetos.get(local).getTitulo());
                        }
                    } else {
                        Projetos.get(local).getParticipantes().add(nome);
                        Colaboradores.get(localcolab).getProjetos().add(Projetos.get(local).getTitulo());
                    }
                    if (Colaboradores.get(localcolab).getTipo_colaborador().equals("professor")) {
                        Colaboradores.get(localcolab).getOrientacoes().add(Projetos.get(local).getTitulo());
                    }
                }
            } else if (localcolab == -1) {
                System.out.println("Colaborador não existe ou foi digitado incorretamente");
            }
        }
        if (resp32 == 2) {
            int localcolab;
            lixo = input.nextLine();
            control462 = 0;
            String nome;
            System.out.println("Participantes existentes:");
            for (w = 0; w < Projetos.get(local).getParticipantes().size(); w++) {
                System.out.println("-" + Projetos.get(local).getParticipantes().get(w));
            }
            System.out.println("Digite o nome do colaborador que deseja remover: ");
            nome = input.nextLine();
            localcolab = colaborador.search_colaborador(Colaboradores, nome);
            if (localcolab != -1) {
                for (r = 0; r < Projetos.get(local).getParticipantes().size(); r++) {
                    if (Projetos.get(local).getParticipantes().get(r).equals(nome)) {
                        Projetos.get(local).getParticipantes().remove(r);
                        for (u = 0; u < Colaboradores.get(localcolab).getProjetos().size(); u++) {
                            if (Colaboradores.get(localcolab).getProjetos().get(u).equals(Projetos.get(local).getTitulo())) {
                                Colaboradores.get(localcolab).getProjetos().remove(u);
                                System.out.println("Removido com sucesso.");
                            }
                        }
                        control462 = 1;
                    } else if ((r == Projetos.get(local).getParticipantes().size() - 1) && (control462 == 0)) {
                        System.out.println("O colaborador não pertence a este projeto");
                    }
                }
            } else if (localcolab == -1) {
                System.out.println("Colaborador não existe ou foi digitado incorretamente");
            }
        }
    }

    public void create_project(java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores) {
        int control41 = 1, local, cont_aux = 0;
        String lixo;
        Projeto projeto = new Projeto();
        Colaborador colaborador = new Colaborador();

        while (control41 == 1) {
            System.out.println("Digite o Titulo do projeto:");
            projeto.setTitulo(input.nextLine());
            if (Projetos.size() == 0) {
                break;
            }
            local = projeto.search_projeto(Projetos, projeto.getTitulo());
            if (local != -1) {
                System.out.println("Este titulo ja esta sendo usado, dê um novo titulo para este projeto.");
            }
            if (local == -1) {
                control41 = 0;
            }
        }
        System.out.println("Digite a data de inicio do projeto: ");
        System.out.println("Dia: ");
        while (cont_aux == 0) {
            try {
                projeto.setDia_inicio(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDia: ");
            }
        }
        cont_aux = 0;
        System.out.println("Mês: ");
        while (cont_aux == 0) {
            try {
                projeto.setMes_inicio(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nMês: ");
            }
        }
        cont_aux = 0;
        System.out.println("Ano: ");
        while (cont_aux == 0) {
            try {
                projeto.setAno_inicio(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nAno: ");
            }
        }
        cont_aux = 0;
        System.out.println("Digite a data prevista para fim do projeto: ");
        System.out.println("Dia: ");
        while (cont_aux == 0) {
            try {
                projeto.setDia_final(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDia: ");
            }
        }
        cont_aux = 0;
        System.out.println("Mês: ");
        while (cont_aux == 0) {
            try {
                projeto.setMes_final(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nMês: ");
            }
        }
        cont_aux = 0;
        System.out.println("Ano: ");
        while (cont_aux == 0) {
            try {
                projeto.setAno_final(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nAno: ");
            }
        }
        cont_aux = 0;
        lixo = input.nextLine();
        System.out.println("Digite a Agencia Financiadora: ");
        projeto.setAg_financiadora(input.nextLine());
        System.out.println("Digite o valor financiado: ");
        while (cont_aux == 0) {
            try {
                projeto.setValor_financiado(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o valor financiado: ");
            }
        }
        cont_aux = 0;
        lixo = input.nextLine();
        System.out.println("Digite o Objetivo do Projeto: ");
        projeto.setObjetivo(input.nextLine());
        System.out.println("Digite a Descrição do projeto: ");
        projeto.setDescricao(input.nextLine());
        System.out.println("Como o o projeto acaba de ser criado, seu status no momento é 'em elaboracao', isso impede de relacionar publicacoes ao projeto, porem futuramente isso pode ser alterado na funcao 4 'editar projetos'.");
        projeto.setStatus("em elaboracao");
        int qntd = 0, g, f, control42 = 0, cont4 = 0;
        String nome, proj_grad;
        System.out.println("inclua os participantes do projeto, quantos são incluindo o professor?");
        while (cont_aux == 0) {
            try {
                qntd = input.nextInt();
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\ninclua os participantes do projeto, quantos são incluindo o professor?");
            }
        }
        cont_aux = 0;
        lixo = input.nextLine();
        int localpart;
        System.out.println("Digite o(s) nome(s) do(s) participante(es):");
        for (g = 0; g < qntd; g++) {
            nome = input.nextLine();
            localpart = colaborador.search_colaborador(Colaboradores, nome);
            if (localpart != -1) {
                projeto.getParticipantes().add(nome);
                Colaboradores.get(localpart).getProjetos().add(projeto.getTitulo());
                if (Colaboradores.get(localpart).getTipo_colaborador().equals("professor")) {
                    cont4++;
                    Colaboradores.get(localpart).getOrientacoes().add(projeto.getTitulo());
                }
                control42 = 1;
            } else if (localpart == -1) {
                g--;
                System.out.println("Colaborador " + nome + ", não emcontrado, tente novamente.");
            }
        }
        if (cont4 == 0) {
            System.out.println("Nenhum professor está cadastrado no projeto, adicione um professor usando a opcao 4 'editar', para seguir com este projeto.");
            Projetos.add(projeto);
        } else {
            Projetos.add(projeto);
        }
        //System.out.println(projeto.getParticipantes());
    }

    public void edit_project(java.util.ArrayList<Projeto> Projetos, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Publicacao> Publicacoes) {
        int d, local, cont_aux = 0;
        String resp2, old_name, lixo;
        Projeto projeto = new Projeto();

        System.out.println("Projetos existentes:");
        for (d = 0; d < Projetos.size(); d++) {
            System.out.println("-" + Projetos.get(d).getTitulo());
        }
        System.out.println();
        System.out.println("Digite o nome do projeto que deseja editar");
        old_name = input.nextLine();
        System.out.println();
        local = projeto.search_projeto(Projetos, old_name);
        if (local != -1) {
            System.out.print("Digite a opcao que deseja editar no projeto:\n-titulo\n-dia inicio\n-mes inicio\n-ano inicio\n-dia final\n-mes final\n-ano final\n-agencia financiadora\n-valor financiado\n-objetivo\n-descricao\n-status\n-publicacoes\n-participantes\nResp: ");
            resp2 = input.nextLine();
            System.out.println();
            if (resp2.equals("titulo")) {
                projeto.edit_titulo(local, old_name, Projetos, Colaboradores, Publicacoes);
            }
            if (resp2.equals("dia inicio")) {
                System.out.println("Digite o novo dia inicio:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setDia_inicio(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo dia inicio:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("mes inicio")) {
                System.out.println("Digite o novo mes inicio:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setMes_inicio(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo mes inicio:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("ano inicio")) {
                System.out.println("Digite o novo ano inicio:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setAno_inicio(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo ano inicio:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("dia final")) {
                System.out.println("Digite o novo dia final:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setDia_final(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo dia final:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("mes final")) {
                System.out.println("Digite o novo mes final:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setMes_final(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo mes final:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("ano final")) {
                System.out.println("Digite o novo ano final:");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setAno_final(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o novo ano final: ");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("agencia financiadora")) {
                System.out.println("Digite a Nova Agencia Financiadora");
                Projetos.get(local).setAg_financiadora(input.nextLine());
            }
            if (resp2.equals("valor financiado")) {
                System.out.println("Digite a Novo Valor Financiado");
                while (cont_aux == 0) {
                    try {
                        Projetos.get(local).setValor_financiado(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite a Novo Valor Financiado");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
            }
            if (resp2.equals("objetivo")) {
                System.out.println("Digite o Novo Objetivo");
                Projetos.get(local).setObjetivo(input.nextLine());
            }
            if (resp2.equals("descricao")) {
                System.out.println("Digite a Nova Descricao");
                Projetos.get(local).setDescricao(input.nextLine());
            }
            if (resp2.equals("status")) {
                projeto.edit_status(local, Projetos, Colaboradores);
            }
            if (resp2.equals("publicacoes")) {
                projeto.edit_pub(local, Projetos, Colaboradores, Publicacoes);
            }
            if (resp2.equals("participantes")) {
                projeto.edit_participantes(local, Projetos, Colaboradores, Publicacoes);
            }
        } else if (local == -1) {
            System.out.println("Projeto não existe ou foi digitado incorretamente");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getDia_inicio() {
        return dia_inicio;
    }

    public void setDia_inicio(int dia_inicio) {
        this.dia_inicio = dia_inicio;
    }

    public int getMes_inicio() {
        return mes_inicio;
    }

    public void setMes_inicio(int mes_inicio) {
        this.mes_inicio = mes_inicio;
    }

    public int getAno_inicio() {
        return ano_inicio;
    }

    public void setAno_inicio(int ano_inicio) {
        this.ano_inicio = ano_inicio;
    }

    public int getDia_final() {
        return dia_final;
    }

    public void setDia_final(int dia_final) {
        this.dia_final = dia_final;
    }

    public int getMes_final() {
        return mes_final;
    }

    public void setMes_final(int mes_final) {
        this.mes_final = mes_final;
    }

    public int getAno_final() {
        return ano_final;
    }

    public void setAno_final(int ano_final) {
        this.ano_final = ano_final;
    }

    public String getAg_financiadora() {
        return Ag_financiadora;
    }

    public void setAg_financiadora(String Ag_financiadora) {
        this.Ag_financiadora = Ag_financiadora;
    }

    public int getValor_financiado() {
        return valor_financiado;
    }

    public void setValor_financiado(int valor_financiado) {
        this.valor_financiado = valor_financiado;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isFim() {
        return fim;
    }

    public void setFim(boolean fim) {
        this.fim = fim;
    }

    public ArrayList<String> getParticipantes() {
        return Participantes;
    }

    public void setParticipantes(ArrayList<String> Participantes) {
        this.Participantes = Participantes;
    }

    public ArrayList<String> getPublicacoes() {
        return Publicacoes;
    }

    public void setPublicacoes(ArrayList<String> publicacoes) {
        this.Publicacoes = publicacoes;
    }
}
