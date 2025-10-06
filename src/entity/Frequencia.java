package entity;
import java.util.Date;

public class Frequencia {
    private int id_frequencia;
    private int id_aluno;
    private Date data;
    private boolean presenca;

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

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
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
