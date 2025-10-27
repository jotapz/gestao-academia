package service;

import dao.InstrutorDAO;
import dao.PlanoTreinoDAO;
import model.Instrutor;

import java.util.List;

public class InstrutorService {
    InstrutorDAO instrutordao = new InstrutorDAO();
    PlanoTreinoDAO deletarplano = new PlanoTreinoDAO();

    public void salvar (String nome, String esp) {
        Instrutor instrutor = new Instrutor();
		instrutor.setNome(nome);
		instrutor.setEspecialidade(esp);
        if (instrutor.getNome() == null || instrutor.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome e especialidade precisa ser preenchido");
        }
        instrutordao.salvar(instrutor);
    }

    public Instrutor buscarPorId(int id) {
        Instrutor instrutor = instrutordao.buscarPorId(id);
        if (instrutor == null) {
            throw new RuntimeException("Erro: não existe instrutor com esse ID");
        }
        return instrutordao.buscarPorId(id);
    }

    public List<Instrutor> listarTodos () {
        return instrutordao.listarTodos();
    }

    public void atualizar (String nome, String esp, int id){
        Instrutor instrutorAtualizar = instrutordao.buscarPorId(id);
        if (instrutorAtualizar == null) {
            throw new RuntimeException("Erro: não existe instrutor com esse ID");
        }
        instrutorAtualizar.setNome(nome);
        instrutorAtualizar.setEspecialidade(esp);
        instrutordao.atualizar(instrutorAtualizar);
    }

    public void deletar (int id) {
        Instrutor instrutorExiste = instrutordao.buscarPorId(id);
        if (instrutorExiste == null) {
            throw new RuntimeException("Erro: não existe instrutor com esse ID");
        }
        deletarplano.DeletarTodosTreinosDoInstrutor(id);
        instrutordao.deletar(id);
    }
}
