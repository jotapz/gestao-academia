package service;

import dao.PlanoTreinoDAO;
import entity.Aluno;
import entity.Instrutor;
import entity.PlanoTreino;
import java.util.List;

public class PlanoTreinoService {
    private PlanoTreinoDAO planoDAO = new PlanoTreinoDAO();

    public void salvar(String descricao, int duracao, Aluno aluno, Instrutor instrutor) {
                PlanoTreino plano = new PlanoTreino();
        plano.setDescricao(descricao);
        plano.setDuracaoSemanas(duracao);
        plano.setAluno(aluno);
        plano.setInstrutor(instrutor);
        planoDAO.salvar(plano);
    }

    public PlanoTreino buscarPorId(int id) {
        return planoDAO.buscarPorId(id);
    }

    public List<PlanoTreino> listarTodos() {
        return planoDAO.listarTodos();
    }

    public void atualizar(String descricao, int duracao, Aluno aluno, Instrutor instrutor, int id) {
        PlanoTreino plano = planoDAO.buscarPorId(id);
        plano.setDescricao(descricao);
        plano.setDuracaoSemanas(duracao);
        plano.setAluno(aluno);
        plano.setInstrutor(instrutor);
        planoDAO.atualizar(plano);
    }

    public void deletar(int id) {
        PlanoTreino plano = planoDAO.buscarPorId(id);
        if (plano == null) {
            throw new RuntimeException("Erro: não existe plano com esse ID");
        }
        planoDAO.deletar(id);
    }
}
