package controller;

import entity.Frequencia;
import service.FrequenciaService;
import java.util.List;

public class FrequenciaController {
    private FrequenciaService service = new FrequenciaService();

    public void registrar(Frequencia frequencia) {
        service.salvar(frequencia);
    }

    public List<Frequencia> listarPorAluno(int idAluno) {
        return service.buscarPorAluno(idAluno);
    }

    public void deletar(int idFrequencia) {
        service.deletar(idFrequencia);
    }
}
