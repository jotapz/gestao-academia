package controller;

import entity.Aluno;
import service.AlunoService;
import java.util.List;

public class AlunoController {
    private AlunoService service = new AlunoService();

    public void cadastrar(Aluno aluno) {
        service.salvar(aluno);
    }

    public List<Aluno> listar() {
        return service.listarTodos();
    }

    public void atualizar(Aluno aluno) {
        service.atualizar(aluno);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public Aluno buscar(int id) {
        return service.buscarPorId(id);
    }
}
