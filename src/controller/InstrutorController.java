package controller;

import entity.Instrutor;
import service.InstrutorService;
import java.util.List;

public class InstrutorController {
    private InstrutorService service = new InstrutorService();

    public void cadastrar(String nome, String esp) {
        service.salvar(nome, esp);
    }

    public List<Instrutor> listar() {
        return service.listarTodos();
    }

    public void atualizar(String nome, String esp, int id) {
        service.atualizar(nome, esp, id);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public Instrutor buscar(int id) {
        return service.buscarPorId(id);
    }
}
