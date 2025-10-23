package controller;

import entity.PlanoTreino;
import service.PlanoTreinoService;
import java.util.List;

public class PlanoTreinoController {
    private PlanoTreinoService service = new PlanoTreinoService();

    public void cadastrar(PlanoTreino plano) {
        service.salvar(plano);
    }

    public List<PlanoTreino> listar() {
        return service.listarTodos();
    }

    public void atualizar(PlanoTreino plano) {
        service.atualizar(plano);
    }

    public void deletar(int id) {
        service.deletar(id);
    }

    public PlanoTreino buscar(int id) {
        return service.buscarPorId(id);
    }
}
