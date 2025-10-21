package entity;
import java.util.Date;

public class Frequencia {
    private int id_frequencia;
    private int id_aluno;
    private Date data;
    private boolean presenca;

    // Construtor vazio para DAOs
    public Frequencia() {
    }

    public Frequencia(int id_frequencia, int id_aluno, Date data, boolean presenca) {
        this.id_frequencia = id_frequencia;
        this.id_aluno = id_aluno;
        this.data = data;
        this.presenca = presenca;
    }

    public int getId_frequencia() {
        return id_frequencia;
    }

    public void setId_frequencia(int id_frequencia) {
        this.id_frequencia = id_frequencia;
    }

    // Métodos compatíveis com DAOs
    public int getId() {
        return this.id_frequencia;
    }

    public void setId(int id) {
        this.id_frequencia = id;
    }

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    // Para relacionamento com objeto Aluno (usado pelos DAOs)
    private Aluno aluno;

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isPresenca() {
        return presenca;
    }

    public void setPresenca(boolean presenca) {
        this.presenca = presenca;
    }
}
