package entity;
import java.util.Date;

public class Aluno {

    private  int id_Aluno;
    private String nome;
    private String cpf;
    private Date data_ingresso;

    // Construtor vazio para permitir criação sem parâmetros
    public Aluno() {
    }

    public Aluno(int id_Aluno, String nome, String cpf, Date data_ingresso) {
        this.id_Aluno = id_Aluno;
        this.nome = nome;
        this.cpf = cpf;
        this.data_ingresso = data_ingresso;
    }

    // Métodos originais (mantidos)
    public int getId_Aluno() {
        return id_Aluno;
    }

    public void setId_Aluno(int id_Aluno) {
        this.id_Aluno = id_Aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_ingresso() {
        return data_ingresso;
    }

    public void setData_ingresso(Date data_ingresso) {
        this.data_ingresso = data_ingresso;
    }

    // Métodos compatíveis com AlunoDAO e outros DAOs
    public int getId() {
        return this.id_Aluno;
    }

    public void setId(int id) {
        this.id_Aluno = id;
    }

    public Date getDataIngresso() {
        return this.data_ingresso;
    }

    public void setDataIngresso(Date dataIngresso) {
        this.data_ingresso = dataIngresso;
    }
}
