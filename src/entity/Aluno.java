package entity;
import java.util.Date;

public class Aluno {

    private  int id_Aluno;
    private String nome;
    private String cpf;
    private Date data_ingresso;

    public Aluno() {
    }

    public Aluno(int id_Aluno, String nome, String cpf, Date data_ingresso) {
        this.id_Aluno = id_Aluno;
        this.nome = nome;
        this.cpf = cpf;
        this.data_ingresso = data_ingresso;
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

    @Override
    public String toString() {
        return nome;
    }
}
