package controller;

import model.Aluno;
import service.AlunoService;
import java.util.List;

public class AlunoController {
    private AlunoService service = new AlunoService();

    public void cadastrar(String nome, String cpf) {
        service.salvar(nome, cpf);
    }

    public List<Aluno> listar() {
        return service.listarTodos();
    }

    public void atualizar(String nome, String cpf, int id) {
        service.atualizar(nome, cpf, id);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public Aluno buscar(int id) {
        return service.buscarPorId(id);
    }
}
