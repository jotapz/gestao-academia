package entity;

public class PlanoTreino {
    private int id_plano;
    private int id_aluno;
    private int id_instrutor;
    private String descricao;
    private int duracao_semanas;


    public PlanoTreino() {
    }

    public PlanoTreino(int id_plano, int duracao_semanas, String descricao, int id_instrutor, int id_aluno) {
        this.id_plano = id_plano;
        this.duracao_semanas = duracao_semanas;
        this.descricao = descricao;
        this.id_instrutor = id_instrutor;
        this.id_aluno = id_aluno;
    }

    public int getId() {
        return this.id_plano;
    }

    public void setId(int id) {
        this.id_plano = id;
    }

    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        if (aluno != null) this.id_aluno = aluno.getId();
    }

    private Instrutor instrutor;

    public Instrutor getInstrutor() {
        return instrutor;
    }

    public void setInstrutor(Instrutor instrutor) {
        this.instrutor = instrutor;
        if (instrutor != null) this.id_instrutor = instrutor.getId();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracaoSemanas() {
        return this.duracao_semanas;
    }

    public void setDuracaoSemanas(int duracaoSemanas) {
        this.duracao_semanas = duracaoSemanas;
    }
}
