package service;

import dao.AlunoDAO;
import entity.Aluno;

import java.util.List;

public class AlunoService {
    private AlunoDAO alunodao = new AlunoDAO();

    public void salvar(Aluno aluno) {
        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome precisa ser preenchido");
        }
        alunodao.salvar(aluno);
    }

    public Aluno buscarPorId(int id) {
        return alunodao.buscarPorId(id);
    }

    public List<Aluno> listarTodos() {
        return alunodao.listarTodos();
    }

    public void atualizar (Aluno aluno) {
        alunodao.atualizar(aluno);
    }

    public void deletar (int id) {
        alunodao.deletar(id);
    }

}
