package controller;

import entity.Aluno;
import entity.Frequencia;
import service.FrequenciaService;

import java.awt.*;
import java.util.List;

public class FrequenciaController {
    private FrequenciaService service = new FrequenciaService();

    public void registrar(Aluno aluno, Boolean presenca) {
        service.salvar(aluno, presenca);
    }

    public List<Frequencia> listarPorAluno(int idAluno) {
        return service.buscarPorAluno(idAluno);
    }

    public void deletar(int idFrequencia) {
        service.deletar(idFrequencia);
    }
}
