package dao;

import entity.PlanoTreino;
// Pode ser necessário importar Aluno e Instrutor também, dependendo da sua entidade
import entity.Aluno;
import entity.Instrutor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoTreinoDAO {

    public void salvar(PlanoTreino plano) {
        String sql = "INSERT INTO PlanoTreino (descricao, duracao_semanas, id_aluno, id_instrutor) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, plano.getDescricao());
            ps.setInt(2, plano.getDuracaoSemanas());
            ps.setInt(3, plano.getAluno().getId());
            ps.setInt(4, plano.getInstrutor().getId());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()) {
                if(rs.next()) {
                    plano.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar plano de treino: " + e.getMessage(), e);
        }
    }
    
    // Para buscar um plano, é útil trazer também os dados do aluno e instrutor.
    // Este método usa JOINs para isso.
    public PlanoTreino buscarPorId(int id) {
        String sql = "SELECT pt.*, a.nome AS aluno_nome, i.nome AS instrutor_nome " +
                     "FROM PlanoTreino pt " +
                     "LEFT JOIN Aluno a ON pt.id_aluno = a.id_aluno " +
                     "LEFT JOIN Instrutor i ON pt.id_instrutor = i.id_instrutor " +
                     "WHERE pt.id_plano = ?";
        PlanoTreino plano = null;

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, id);
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    plano = new PlanoTreino();
                    plano.setId(rs.getInt("id_plano"));
                    plano.setDescricao(rs.getString("descricao"));
                    plano.setDuracaoSemanas(rs.getInt("duracao_semanas"));
                    
                    Aluno aluno = new Aluno();
                    aluno.setId(rs.getInt("id_aluno"));
                    aluno.setNome(rs.getString("aluno_nome")); // Nome vindo do JOIN
                    plano.setAluno(aluno);

                    Instrutor instrutor = new Instrutor();
                    instrutor.setId(rs.getInt("id_instrutor"));
                    instrutor.setNome(rs.getString("instrutor_nome")); // Nome vindo do JOIN
                    plano.setInstrutor(instrutor);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar plano de treino por ID: " + e.getMessage(), e);
        }
        return plano;
    }

    public List<PlanoTreino> listarTodos() {
        // Implementação similar ao buscarPorId, mas sem a cláusula WHERE e com um loop
        // ...
        return new ArrayList<>(); // Retornando lista vazia por brevidade
    }

    public void atualizar(PlanoTreino plano) {
        String sql = "UPDATE PlanoTreino SET descricao = ?, duracao_semanas = ?, id_aluno = ?, id_instrutor = ? WHERE id_plano = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, plano.getDescricao());
            ps.setInt(2, plano.getDuracaoSemanas());
            ps.setInt(3, plano.getAluno().getId());
            ps.setInt(4, plano.getInstrutor().getId());
            ps.setInt(5, plano.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o plano de treino: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM PlanoTreino WHERE id_plano = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar o plano de treino: " + e.getMessage(), e);
        }
    }
}