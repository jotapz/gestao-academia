package service;

import dao.AlunoDAO;
import dao.FrequenciaDAO;
import dao.PlanoTreinoDAO;
import entity.Aluno;
import java.util.Date;
import java.util.List;

public class AlunoService {
    private AlunoDAO alunodao = new AlunoDAO();
    private PlanoTreinoDAO deletarplano = new PlanoTreinoDAO();
    private FrequenciaDAO deletarfrequencia = new FrequenciaDAO();


    public void salvar(String nome, String cpf) {
        Aluno aluno = new Aluno();
        aluno.setNome(nome);
        aluno.setCpf(cpf);
        aluno.setDataIngresso(new Date());

        if (aluno.getNome() == null || aluno.getNome().isBlank()) {
            throw new IllegalArgumentException("Campo nome precisa ser preenchido");
        }
        alunodao.salvar(aluno);
    }

    public Aluno buscarPorId(int id) {
        Aluno aluno = alunodao.buscarPorId(id);
        if (aluno == null) {
            throw new RuntimeException("Erro: aluno não encontrado para o ID: " + id);
        }
        return alunodao.buscarPorId(id);
    }

    public List<Aluno> listarTodos() {
        return alunodao.listarTodos();
    }

    public void atualizar (String nome, String cpf, int id) {
        Aluno alunoatualizar = alunodao.buscarPorId(id);
        if (alunoatualizar == null) {
            throw new RuntimeException("Erro: não existe aluno com esse ID");
        }
        alunoatualizar.setNome(nome);
        alunoatualizar.setCpf(cpf);
        alunodao.atualizar(alunoatualizar);
    }

    public void deletar (int id) {
        Aluno alunoexiste = alunodao.buscarPorId(id);
        if (alunoexiste == null) {
            throw new RuntimeException("Erro: não existe aluno com esse ID");
        }
        deletarfrequencia.DeletarTodoFrequenciaUsuario(id);
        deletarplano.DeletarTodosTreinosDoUsuario(id);
        alunodao.deletar(id);
    }

}
