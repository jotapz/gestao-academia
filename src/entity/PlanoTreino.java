package entity;

public class PlanoTreino {
    private int id_plano;
    private int id_aluno;
    private int id_instrutor;
    private String descricao;
    private int duracao_semanas;


    public PlanoTreino(int id_plano, int duracao_semanas, String descricao, int id_instrutor, int id_aluno) {
        this.id_plano = id_plano;
        this.duracao_semanas = duracao_semanas;
        this.descricao = descricao;
        this.id_instrutor = id_instrutor;
        this.id_aluno = id_aluno;
    }

    public int getId_plano() {
        return id_plano;
    }

    public void setId_plano(int id_plano) {
        this.id_plano = id_plano;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public int getId_instrutor() {
        return id_instrutor;
    }

    public void setId_instrutor(int id_instrutor) {
        this.id_instrutor = id_instrutor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao_semanas() {
        return duracao_semanas;
    }

    public void setDuracao_semanas(int duracao_semanas) {
        this.duracao_semanas = duracao_semanas;
    }
}
