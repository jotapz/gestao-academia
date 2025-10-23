package service;

import dao.PlanoTreinoDAO;
import entity.PlanoTreino;
import java.util.List;

public class PlanoTreinoService {
    private PlanoTreinoDAO planoDAO = new PlanoTreinoDAO();

    public void salvar(PlanoTreino plano) {
        planoDAO.salvar(plano);
    }

    public PlanoTreino buscarPorId(int id) {
        return planoDAO.buscarPorId(id);
    }

    public List<PlanoTreino> listarTodos() {
        return planoDAO.listarTodos();
    }

    public void atualizar(PlanoTreino plano) {
        planoDAO.atualizar(plano);
    }

    public void deletar(int id) {
        planoDAO.deletar(id);
    }
}
