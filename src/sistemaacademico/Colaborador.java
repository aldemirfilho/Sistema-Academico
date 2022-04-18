package sistemaacademico;

import java.util.ArrayList;
import java.util.Scanner;

public class Colaborador {

    private String tipo_colaborador;
    private String nome;
    private String email;

    Scanner input = new Scanner(System.in);

    java.util.ArrayList<String> Publicacoes = new java.util.ArrayList<String>();
    java.util.ArrayList<String> Projetos = new java.util.ArrayList<String>();
    java.util.ArrayList<String> Orientacoes = new java.util.ArrayList<String>();

    public int search_colaborador(java.util.ArrayList<Colaborador> Colaboradores, String nome) {
        int z, local = -1;
        for (z = 0; z < Colaboradores.size(); z++) {
            if (Colaboradores.get(z).getNome().equals(nome)) {
                local = z;
            }
        }
        return local;
    }

    public void consultar_colab(java.util.ArrayList<Publicacao> Publicacoes, java.util.ArrayList<Colaborador> Colaboradores, java.util.ArrayList<Projeto> Projetos) {
        int w, t, r, q, f, local;
        Projeto aux;
        Publicacao aux2;
        String nome;
        Colaborador colaborador = new Colaborador();
        System.out.println("Colaboradores existentes:");
        for (w = 0; w < Colaboradores.size(); w++) {
            System.out.println("-" + Colaboradores.get(w).getNome());
        }
        System.out.println("Digite o nome do colaborador que você deseja ver os dados:");
        nome = input.nextLine();
        System.out.println();
        local = colaborador.search_colaborador(Colaboradores, nome);
        if (local != -1) {
            java.util.ArrayList<Projeto> Projetosprint = new java.util.ArrayList<Projeto>();
            java.util.ArrayList<Publicacao> Publicacoesprint = new java.util.ArrayList<Publicacao>();
            System.out.println("Nome: " + Colaboradores.get(local).getNome());
            System.out.println("Email: " + Colaboradores.get(local).getEmail());
            System.out.println("Projetos: " + Colaboradores.get(local).getProjetos());
            for (t = 0; t < Colaboradores.get(local).getProjetos().size(); t++) {
                for (r = 0; r < Projetos.size(); r++) {
                    if (Colaboradores.get(local).getProjetos().get(t).equals(Projetos.get(r).getTitulo())) {
                        if (Projetos.get(r).getStatus().equals("em andamento")) {
                            Projetosprint.add(Projetos.get(r));
                        }
                    }
                }
            }
            for (q = 0; q < Projetosprint.size(); q++) {
                for (f = q + 1; f < Projetosprint.size(); f++) {
                    if (Projetosprint.get(f).getAno_final() > Projetosprint.get(q).getAno_final()) {
                        aux = Projetosprint.get(f);
                        Projetosprint.set(f, Projetosprint.get(q));
                        Projetosprint.set(q, aux);
                    }
                }
            }
            for (q = 0; q < Projetosprint.size(); q++) {
                for (f = q + 1; f < Projetosprint.size(); f++) {
                    if (Projetosprint.get(f).getAno_final() >= Projetosprint.get(q).getAno_final()) {
                        if (Projetosprint.get(f).getMes_final() > Projetosprint.get(q).getMes_final()) {
                            aux = Projetosprint.get(f);
                            Projetosprint.set(f, Projetosprint.get(q));
                            Projetosprint.set(q, aux);
                        }
                    }
                }
            }
            for (q = 0; q < Projetosprint.size(); q++) {
                for (f = q + 1; f < Projetosprint.size(); f++) {
                    if (Projetosprint.get(f).getAno_final() >= Projetosprint.get(q).getAno_final()) {
                        if (Projetosprint.get(f).getMes_final() >= Projetosprint.get(q).getMes_final()) {
                            if (Projetosprint.get(f).getDia_final() > Projetosprint.get(q).getDia_final()) {
                                aux = Projetosprint.get(f);
                                Projetosprint.set(f, Projetosprint.get(q));
                                Projetosprint.set(q, aux);
                            }
                        }
                    }
                }
            }
            System.out.println("Projetos em andamento: ");
            for (t = 0; t < Projetosprint.size(); t++) {
                System.out.println(Projetosprint.get(t).getTitulo() + " - " + Projetosprint.get(t).getDia_final() + "/" + Projetosprint.get(t).getMes_final() + "/" + Projetosprint.get(t).getAno_final());
            }

            for (t = 0; t < Colaboradores.get(local).getPublicacoes().size(); t++) {
                for (r = 0; r < Publicacoes.size(); r++) {
                    if (Colaboradores.get(local).getPublicacoes().get(t).equals(Publicacoes.get(r).getTitulo())) {
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
            System.out.println("Colaborador " + nome + ", não encontrado, tente novamente.");
        }
    }

    public java.util.ArrayList<Colaborador> creat_colaborador(Colaborador colaborador, java.util.ArrayList<Colaborador> Colaboradores) {
        int control1 = 1, local;

        while (control1 == 1) {
            int igual1 = 0, k;
            System.out.println("Digite o nome do colaborador:");
            colaborador.setNome(input.nextLine());
            if (Colaboradores.size() == 0) {
                break;
            }
            local = colaborador.search_colaborador(Colaboradores, colaborador.getNome());
            if (local != -1) {
                System.out.println("Este nome ja esta sendo usado, dê um novo nome para este colaborador.");
                igual1 = 1;
            }
            if (local == -1) {
                control1 = 0;
            }
        }
        System.out.println("Digite o E-mail do colaborador:");
        colaborador.setEmail(input.nextLine());
        System.out.println("Digite o tipo de colaborador(aluno de graduacao, aluno de mestrado, aluno de doutorado, professor, pesquisador):");
        String tipo;
        tipo = input.nextLine();
        if(!tipo.equals("aluno de graduacao") && !tipo.equals("aluno de mestrado") && !tipo.equals("aluno de doutorado") && !tipo.equals("professor") && !tipo.equals("pesquisador"))
        {
            System.out.println("-Foi digitado o tipo de colaborador incorretamente, tente novamente para adicionar o mesmo ao sistema.");
            System.out.println();
        }
        else
        {
            colaborador.setTipo_colaborador(tipo);
            Colaboradores.add(colaborador);
        }
        return Colaboradores;
    }

    public String getTipo_colaborador() {
        return tipo_colaborador;
    }

    public void setTipo_colaborador(String tipo_colaborador) {
        this.tipo_colaborador = tipo_colaborador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ArrayList<String> getPublicacoes() {
        return Publicacoes;
    }

    public void setPublicacoes(ArrayList<String> publicacoes) {
        this.Publicacoes = publicacoes;
    }

    public ArrayList<String> getProjetos() {
        return Projetos;
    }

    public void setProjetos(ArrayList<String> projetos) {
        this.Projetos = projetos;
    }

    public ArrayList<String> getOrientacoes() {
        return Orientacoes;
    }

    public void setOrientacoes(ArrayList<String> orientacoes) {
        this.Orientacoes = orientacoes;
    }
}
