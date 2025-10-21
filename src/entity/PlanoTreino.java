package entity;

public class PlanoTreino {
    private int id_plano;
    private int id_aluno;
    private int id_instrutor;
    private String descricao;
    private int duracao_semanas;


    // Construtor vazio para uso pelos DAOs
    public PlanoTreino() {
    }

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

    // Métodos compatíveis com DAOs
    public int getId() {
        return this.id_plano;
    }

    public void setId(int id) {
        this.id_plano = id;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    // Relacionamento com objetos
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        if (aluno != null) this.id_aluno = aluno.getId();
    }

    public int getId_instrutor() {
        return id_instrutor;
    }

    public void setId_instrutor(int id_instrutor) {
        this.id_instrutor = id_instrutor;
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

    public int getDuracao_semanas() {
        return duracao_semanas;
    }

    public void setDuracao_semanas(int duracao_semanas) {
        this.duracao_semanas = duracao_semanas;
    }

    // Convenções camelCase esperadas pelos DAOs
    public int getDuracaoSemanas() {
        return this.duracao_semanas;
    }

    public void setDuracaoSemanas(int duracaoSemanas) {
        this.duracao_semanas = duracaoSemanas;
    }
}
