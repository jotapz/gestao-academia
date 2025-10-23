package service;

import dao.FrequenciaDAO;
import entity.Frequencia;
import java.util.List;

public class FrequenciaService {
    private FrequenciaDAO frequenciaDAO = new FrequenciaDAO();

    public void salvar(Frequencia frequencia) {
        frequenciaDAO.salvar(frequencia);
    }

    public List<Frequencia> buscarPorAluno(int idAluno) {
        return frequenciaDAO.buscarPorAluno(idAluno);
    }

    public void deletar(int idFrequencia) {
        frequenciaDAO.deletar(idFrequencia);
    }
}
