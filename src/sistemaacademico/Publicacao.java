package sistemaacademico;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Publicacao {

    private String titulo;
    private String nome_conferencia;
    private int ano_publicacao;
    private String projeto_ass;

    Scanner input = new Scanner(System.in);

    java.util.ArrayList<String> Autores = new java.util.ArrayList<String>();

    public int search_publicacao(java.util.ArrayList<Publicacao> Publicacoes, String nome) {
        int z, local = -1;
        for (z = 0; z < Publicacoes.size(); z++) {
            if (Publicacoes.get(z).getTitulo().equals(nome)) {
                local = z;
            }
        }
        return local;
    }

    public void lancar_pub(java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Projeto> Projetos) {
        Publicacao publicacao = new Publicacao();
        Projeto projeto1 = new Projeto();
        int cont_aux = 0;

        int ass = 0, i, j, k, g, f, autores = 0, control2_1 = 1, local;
        String projeto, nome_autor, lixo;

        while (control2_1 == 1) {
            int igual = 0;
            System.out.println("Digite o Titulo da publicação:");
            publicacao.setTitulo(input.nextLine());
            if (Publicacoes.size() == 0) {
                break;
            }
            local = publicacao.search_publicacao(Publicacoes, publicacao.getTitulo());
            if (local != -1) {
                System.out.println("Este titulo ja esta sendo usado, dê um novo titulo para esta publicaçâo.");
                igual = 1;
            }
            if (local == -1) {
                control2_1 = 0;
            }
        }
        //Publicacoes.add(publicacao);
        System.out.println("Digite o nome da conferencia");
        publicacao.setNome_conferencia(input.nextLine());
        System.out.println("Digite o ano de publicação");
        while (cont_aux == 0) {
            try {
                publicacao.setAno_publicacao(input.nextInt());
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o ano de publicação");
            }
        }
        cont_aux = 0;
        System.out.print("Esta publicação está associada á algum projeto?\nSIM: Digite 1\nNAO: Digite 0\nResp: ");
        while (cont_aux == 0) {
            try {
                ass = input.nextInt();
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nEsta publicação está associada á algum projeto?\nSIM: Digite 1\nNAO: Digite 0\nResp: ");
            }
        }
        cont_aux = 0;
        System.out.println();
        if (ass == 1) {
            lixo = input.nextLine();
            if (Projetos.size() == 0) {
                System.out.println("Não há projetos sendo realizados no momento.");
                System.out.println();
                publicacao.setProjeto_ass("0");
            } else {
                int control2_2 = 0;
                System.out.println("Projetos existentes: ");
                for (i = 0; i < Projetos.size(); i++) {
                    System.out.println(Projetos.get(i).getTitulo());
                }
                System.out.println("Digite o nome do projeto associado:");
                projeto = input.nextLine();
                local = projeto1.search_projeto(Projetos, projeto);
                if ((local != -1) && (Projetos.get(local).getStatus().equals("em andamento"))) {
                    Projetos.get(local).getPublicacoes().add(publicacao.getTitulo());
                    publicacao.setProjeto_ass(projeto);
                    control2_2 = 1;
                } else if ((local != -1) && (!(Projetos.get(local).getStatus().equals("em andamento")))) {
                    System.out.println("O projeto não possui o status 'em andamento' logo não pode ter tal projeto associado.");
                    publicacao.setProjeto_ass("0");
                    control2_2 = 1;
                }
                if ((i == Projetos.size() - 1) && (control2_2 == 0)) {
                    System.out.println("Projeto digitado incorretamente ou não existe.");
                }
            }
        }
        if (ass == 0) {
            publicacao.setProjeto_ass("0");
        }
        Colaborador colaborador = new Colaborador();
        System.out.println("Digite a quantidade de autores que esta publicaçâo possui:");
        while (cont_aux == 0) {
            try {
                autores = input.nextInt();
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite a quantidade de autores que esta publicaçâo possui:");
            }
        }
        cont_aux = 0;
        lixo = input.nextLine();
        System.out.println("Digite o(s) nomes(s) do(s) autor(es):");
        for (j = 0; j < autores; j++) {
            nome_autor = input.nextLine();
            local = colaborador.search_colaborador(Colaboradores, nome_autor);
            if (local != -1) {
                publicacao.getAutores().add(nome_autor);
                Colaboradores.get(local).getPublicacoes().add(publicacao.getTitulo());
            } else if (local == -1) {
                j--;
                System.out.println("Colaborador " + nome_autor + ", não emcontrado, tente novamente.");
            }
        }
        //System.out.println("autores " + publicacao.getAutores());
        Publicacoes.add(publicacao);
    }

    public void edit_titulo(int local, java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Projeto> Projetos) {
        int control3 = 1, k, h;
        Publicacao publicacao1 = new Publicacao();
        String new_name, old_name;
        old_name = Publicacoes.get(local).getTitulo();
        int localtit;
        //System.out.println("titulo antes " + Publicacoes.get(i).getTitulo());
        while (control3 == 1) {
            System.out.println("Digite o o novo Titulo da publicação:");
            new_name = input.nextLine();
            localtit = publicacao1.search_publicacao(Publicacoes, new_name);
            if (localtit != -1) {
                System.out.println("Este titulo ja esta sendo usado, dê um novo titulo para esta publicaçâo.");
            }
            if (localtit == -1) {
                Publicacoes.get(local).setTitulo(new_name);
                for (k = 0; k < Colaboradores.size(); k++) {
                    for (h = 0; h < Colaboradores.get(k).getPublicacoes().size(); h++) {
                        if (Colaboradores.get(k).getPublicacoes().get(h).equals(old_name)) {
                            //System.out.println("nome antigo " + old_name);
                            Colaboradores.get(k).getPublicacoes().set(h, new_name);
                            //System.out.println();
                            //System.out.println("nome" + Colaboradores.get(k).getNome() + Colaboradores.get(k).getPublicacoes());
                        }
                    }
                }
                for (k = 0; k < Projetos.size(); k++) {
                    for (h = 0; h < Projetos.get(k).getPublicacoes().size(); h++) {
                        if (Projetos.get(k).getPublicacoes().get(h).equals(old_name)) {
                            //System.out.println("nome antigo " + old_name);
                            Projetos.get(k).getPublicacoes().set(h, new_name);
                            //System.out.println("nome" + Projetos.get(k).getTitulo() + Projetos.get(k).getPublicacoes());
                        }
                    }
                }
                control3 = 0;
            }
        }
    }

    public void edit_proj_ass(int local, java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Projeto> Projetos) {
        int t, resp = 0, subst = 0, localass, j, cont_aux = 0;
        String nome_proj, old_proj = null, lixo;
        if (Projetos.size() == 0) {
            System.out.println("Não há projetos sendo realizados no momento.");
        } else {
            int localass2;
            Projeto projeto1 = new Projeto();
            System.out.print("Voce deseja remover um projeto relacionado a esta publicação ou adicionar um projeto?\nAdicionar: 1\nRemover: 2\nResp: ");
            while (cont_aux == 0) {
                try {
                    resp = input.nextInt();
                    cont_aux++;
                } catch (InputMismatchException Exception) {
                    lixo = input.nextLine();
                    System.out.println();
                    System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nVoce deseja remover um projeto relacionado a esta publicação ou adicionar um projeto?\nAdicionar: 1\nRemover: 2\nResp: ");
                }
            }
            cont_aux = 0;
            lixo = input.nextLine();
            System.out.println();
            if (resp == 1) {
                if (!(Publicacoes.get(local).getProjeto_ass().equals("0"))) {
                    System.out.print("A publicação já possui um projeto associado, deseja substituir?\nSIM: Digite 1\nNAO: Digite 0\nresp: ");
                    old_proj = Publicacoes.get(local).getProjeto_ass();
                    while (cont_aux == 0) {
                        try {
                            subst = input.nextInt();
                            cont_aux++;
                        } catch (InputMismatchException Exception) {
                            lixo = input.nextLine();
                            System.out.println();
                            System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nA publicação já possui um projeto associado, deseja substituir?\nSIM: Digite 1\nNAO: Digite 0\nresp: ");
                        }
                    }
                    cont_aux = 0;
                    lixo = input.nextLine();
                }
                if ((Publicacoes.get(local).getProjeto_ass().equals("0")) || (subst == 1)) {
                    int q;
                    System.out.println("Projetos existentes:");
                    for (q = 0; q < Projetos.size(); q++) {
                        System.out.println("-" + Projetos.get(q).getTitulo());
                    }
                    System.out.println("Insira o titulo do projeto que deseja relacionar à publicação:");
                    nome_proj = input.nextLine();
                    localass = projeto1.search_projeto(Projetos, nome_proj);
                    if (localass != -1 && (Projetos.get(localass).getStatus().equals("em andamento"))) {
                        Projetos.get(localass).getPublicacoes().add(Publicacoes.get(local).getTitulo());
                    } else if (localass == -1) {
                        System.out.println("Projeto desejado não existe ou foi digitado incorretamente, tente novamente.");
                    }
                    else if(!(Projetos.get(localass).getStatus().equals("em andamento")))
                    {
                        System.out.println("O projeto escolhido não possui status em andamento.");
                    }
                    if (subst == 1) {
                        localass2 = projeto1.search_projeto(Projetos, old_proj);
                        if (localass2 != -1) {
                            for (t = 0; t < Projetos.get(localass2).getPublicacoes().size(); t++) {
                                if (Projetos.get(localass2).getPublicacoes().get(t).equals(Publicacoes.get(local).getTitulo())) {
                                    Projetos.get(localass2).getPublicacoes().remove(t);
                                    //System.out.println("apagou");
                                }
                            }
                        }
                    }
                    Publicacoes.get(local).setProjeto_ass(nome_proj);
                }
            } else if (resp == 2) {
                String aux;
                int localass3;
                if (!Publicacoes.get(local).getProjeto_ass().equals("0")) {
                    aux = Publicacoes.get(local).getProjeto_ass();
                    localass3 = projeto1.search_projeto(Projetos, aux);
                    if (localass3 != -1) {
                        for (j = 0; j < Projetos.get(localass3).getPublicacoes().size(); j++) {
                            if (Projetos.get(localass3).getPublicacoes().get(j).equals(Publicacoes.get(local).getTitulo())) {
                                Projetos.get(localass3).getPublicacoes().remove(j);
                            }
                        }
                    }
                    Publicacoes.get(local).setProjeto_ass("0");
                } else if ((Publicacoes.get(local).getProjeto_ass().equals("0"))) {
                    System.out.println("A publicação não está relacionada a nenhum projeto.");
                }
            }
        }
    }

    public void edit_autores(int local, java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Colaborador> Colaboradores) {
        int func = 0, e, f, g, control35 = 0, localaut, cont_aux = 0;
        String nome_autor, lixo;
        Colaborador colaborador = new Colaborador();
        System.out.print("Você deseja remover um adicionar um novo autor?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
        while (cont_aux == 0) {
            try {
                func = input.nextInt();
                cont_aux++;
            } catch (InputMismatchException Exception) {
                lixo = input.nextLine();
                System.out.println();
                System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nVocê deseja remover um adicionar um novo autor?\nAdicionar: Digite 1\nRemover: Digite 2\nResp: ");
            }
        }
        cont_aux = 0;
        System.out.println();
        lixo = input.nextLine();
        if (func == 1) {
            System.out.println("Colaboradores existentes:");
            for (e = 0; e < Colaboradores.size(); e++) {
                System.out.println("-" + Colaboradores.get(e).getNome());
            }
            System.out.println("Digite o nome do autor que deseja adicionar:");
            nome_autor = input.nextLine();
            localaut = colaborador.search_colaborador(Colaboradores, nome_autor);
            if (localaut != -1) {
                Publicacoes.get(local).getAutores().add(nome_autor);
                Colaboradores.get(localaut).getPublicacoes().add(Publicacoes.get(local).getTitulo());
            } else if (localaut == -1) {
                System.out.println("Colaborador não existe ou digitado incorretamente.");
            }
        }
        if (func == 2) {
            System.out.println("Digite o nome do autor que deseja remover:");
            nome_autor = input.nextLine();
            for (e = 0; e < Publicacoes.get(local).getAutores().size(); e++) {
                if (Publicacoes.get(local).getAutores().get(e).equals(nome_autor)) {
                    //System.out.println("aquiii");
                    Publicacoes.get(local).getAutores().remove(e);
                    control35 = 1;
                }
                if ((e == Publicacoes.get(local).getAutores().size() - 1) && (control35 == 0)) {
                    System.out.println("autor não existe nessa publicação ou digitado incorretamente.");
                }
            }
            //System.out.println("autores depois" + Publicacoes.get(i).getAutores());
            localaut = colaborador.search_colaborador(Colaboradores, nome_autor);
            if (localaut != -1) {
                for (g = 0; g < Colaboradores.get(localaut).getPublicacoes().size(); g++) {
                    if (Colaboradores.get(localaut).getPublicacoes().get(g).equals(Publicacoes.get(local).getTitulo()));
                    {
                        Colaboradores.get(localaut).getPublicacoes().remove(g);
                    }
                }
            }
        }
    }

    public void edit_pub(java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Projeto> Projetos) {
        int i, j, k, h, control3 = 1, local, cont_aux = 0;
        String publicacao, escolha, lixo;
        Publicacao publicacao1 = new Publicacao();
        System.out.println("Publicações existentes:");
        for (i = 0; i < Publicacoes.size(); i++) {
            System.out.println("-" + Publicacoes.get(i).getTitulo());
        }
        System.out.println("Digite o nome da publicação que deseja editar: ");
        publicacao = input.nextLine();
        local = publicacao1.search_publicacao(Publicacoes, publicacao);
        if (local != -1) {
            System.out.println("Digite o que deseja editar na publicação(titulo, conferencia, ano, projeto associado, autores):");
            escolha = input.nextLine();
            if (escolha.equals("titulo")) {
                publicacao1.edit_titulo(local, Publicacoes, Colaboradores, Projetos);
                //System.out.println("titulo depois " + Publicacoes.get(i).getTitulo());
            }
            if (escolha.equals("conferencia")) {
                //System.out.println("conferencia antes " + Publicacoes.get(i).getNome_conferencia());
                System.out.println("Digite o nome da nova conferência:");
                Publicacoes.get(local).setNome_conferencia(input.nextLine());
                //System.out.println("conferencia dps " + Publicacoes.get(i).getNome_conferencia());
            }
            if (escolha.equals("ano")) {
                //System.out.println("ano antes " + Publicacoes.get(i).getAno_publicacao());
                System.out.println("Digite o nome do novo ano de publicação:");
                while (cont_aux == 0) {
                    try {
                        Publicacoes.get(local).setAno_publicacao(input.nextInt());
                        cont_aux++;
                    } catch (InputMismatchException Exception) {
                        lixo = input.nextLine();
                        System.out.println();
                        System.out.println("Foi digitado um elemento diferente de um numero! Tente novamente...\nDigite o nome do novo ano de publicação:");
                    }
                }
                cont_aux = 0;
                lixo = input.nextLine();
                //System.out.println("ano dps " + Publicacoes.get(i).getAno_publicacao());
            }
            if (escolha.equals("projeto associado")) {
                publicacao1.edit_proj_ass(local, Publicacoes, Projetos);
            }
            if (escolha.equals("autores")) {
                publicacao1.edit_autores(local, Publicacoes, Colaboradores);
                //System.out.println("autores depois" + Publicacoes.get(i).getAutores());
            }
        }
        if (local == -1) {
            System.out.println("Publicacao não existe ou foi digitado incorretamente");
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNome_conferencia() {
        return nome_conferencia;
    }

    public void setNome_conferencia(String nome_conferencia) {
        this.nome_conferencia = nome_conferencia;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public String getProjeto_ass() {
        return projeto_ass;
    }

    public void setProjeto_ass(String projeto_ass) {
        this.projeto_ass = projeto_ass;
    }

    public ArrayList<String> getAutores() {
        return Autores;
    }

    public void setAutores(ArrayList<String> autores) {
        this.Autores = autores;
    }
}
