package controller;

import model.Aluno;
import model.Instrutor;
import model.PlanoTreino;
import service.PlanoTreinoService;
import java.util.List;

public class PlanoTreinoController {
    private PlanoTreinoService service = new PlanoTreinoService();

    public void cadastrar(String descricao, int duracao, Aluno aluno, Instrutor instrutor) {
        service.salvar(descricao, duracao, aluno, instrutor);
    }

    public List<PlanoTreino> listar() {
        return service.listarTodos();
    }

    public void atualizar(String descricao, int duracao, Aluno aluno, Instrutor instrutor, int id) {
        service.atualizar(descricao, duracao, aluno, instrutor, id);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public PlanoTreino buscar(int id) {
        return service.buscarPorId(id);
    }
}
