package dao;

import entity.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {

    public void salvar(Aluno aluno) {
        String sql = "INSERT INTO Aluno (nome, cpf, data_ingresso) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setDate(3, new java.sql.Date(aluno.getDataIngresso().getTime()));
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    aluno.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar aluno: " + e.getMessage(), e);
        }
    }

    public Aluno buscarPorId(int id) {
        String sql = "SELECT * FROM Aluno WHERE id_aluno = ?";
        Aluno aluno = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    aluno = new Aluno();
                    aluno.setId(rs.getInt("id_aluno"));
                    aluno.setNome(rs.getString("nome"));
                    aluno.setCpf(rs.getString("cpf"));
                    aluno.setDataIngresso(rs.getDate("data_ingresso"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar aluno por ID: " + e.getMessage(), e);
        }
        return aluno;
    }

    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM Aluno";
        List<Aluno> alunos = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getInt("id_aluno"));
                aluno.setNome(rs.getString("nome"));
                aluno.setCpf(rs.getString("cpf"));
                aluno.setDataIngresso(rs.getDate("data_ingresso"));
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os alunos: " + e.getMessage(), e);
        }
        return alunos;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE Aluno SET nome = ?, cpf = ?, data_ingresso = ? WHERE id_aluno = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, aluno.getNome());
            ps.setString(2, aluno.getCpf());
            ps.setDate(3, new java.sql.Date(aluno.getDataIngresso().getTime()));
            ps.setInt(4, aluno.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar aluno: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Aluno WHERE id_aluno = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar aluno: " + e.getMessage(), e);
        }
    }
}