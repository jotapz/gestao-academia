package controller;

import entity.Instrutor;
import service.InstrutorService;
import java.util.List;

public class InstrutorController {
    private InstrutorService service = new InstrutorService();

    public void cadastrar(Instrutor instrutor) {
        service.salvar(instrutor);
    }

    public List<Instrutor> listar() {
        return service.listarTodos();
    }

    public void atualizar(Instrutor instrutor) {
        service.atualizar(instrutor);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public Instrutor buscar(int id) {
        return service.buscarPorId(id);
    }
}
