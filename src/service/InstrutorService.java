package service;

import dao.InstrutorDAO;
import entity.Instrutor;

import java.util.List;

public class InstrutorService {
    InstrutorDAO instrutorDao = new InstrutorDAO();

    public void salvar (Instrutor instrutor) {
        if (instrutor.getNome() == null || instrutor.getNome().isBlank()) {
            throw new IllegalArgumentException("Campos precisa ser preenchido");
        }
        instrutorDao.salvar(instrutor);
    }

    public Instrutor buscarPorId(int id) {
        return instrutorDao.buscarPorId(id);
    }

    public List<Instrutor> listarTodos () {
        return instrutorDao.listarTodos();
    }

    public void atualizar (Instrutor instrutor){
        instrutorDao.atualizar(instrutor);
    }

    public void deletar (int id) {
        instrutorDao.deletar(id);
    }
}
