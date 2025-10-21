package entity;

public class Instrutor {
    private int id_Instrutor;
    private String nome;
    private String especialidade;

    // Construtor vazio necessário para DAOs
    public Instrutor() {
    }

    public Instrutor(int id_Instrutor, String nome, String especialidade) {
        this.id_Instrutor = id_Instrutor;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getId_Instrutor() {
        return id_Instrutor;
    }

    public void setId_Instrutor(int id_Instrutor) {
        this.id_Instrutor = id_Instrutor;
    }

    // Métodos compatíveis com convenção usada pelos DAOs
    public int getId() {
        return this.id_Instrutor;
    }

    public void setId(int id) {
        this.id_Instrutor = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
