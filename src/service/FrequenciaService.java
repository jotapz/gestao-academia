package service;

import dao.FrequenciaDAO;
import model.Aluno;
import model.Frequencia;

import java.util.Date;
import java.util.List;

public class FrequenciaService {
    private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public void salvar(Aluno aluno, Boolean presenca) {
        Frequencia frequencia = new Frequencia();
        frequencia.setAluno(aluno);
        frequencia.setPresenca(presenca);
        frequencia.setData(new Date());
        frequenciaDAO.salvar(frequencia);
    }

    public List<Frequencia> buscarPorAluno(int idAluno) {
        return frequenciaDAO.buscarPorAluno(idAluno);
    }

    public void deletar(int idFrequencia) {
        frequenciaDAO.deletar(idFrequencia);
    }
}
