package dao;

import entity.Instrutor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InstrutorDAO {

    public void salvar(Instrutor instrutor) {
        String sql = "INSERT INTO Instrutor (nome, especialidade) VALUES (?, ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, instrutor.getNome());
            ps.setString(2, instrutor.getEspecialidade());
            ps.executeUpdate();
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    instrutor.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar instrutor: " + e.getMessage(), e);
        }
    }

    public Instrutor buscarPorId(int id) {
        String sql = "SELECT * FROM Instrutor WHERE id_instrutor = ?";
        Instrutor instrutor = null;
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    instrutor = new Instrutor();
                    instrutor.setId(rs.getInt("id_instrutor"));
                    instrutor.setNome(rs.getString("nome"));
                    instrutor.setEspecialidade(rs.getString("especialidade"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar instrutor por ID: " + e.getMessage(), e);
        }
        return instrutor;
    }

    public List<Instrutor> listarTodos() {
        String sql = "SELECT * FROM Instrutor";
        List<Instrutor> instrutores = new ArrayList<>();
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Instrutor instrutor = new Instrutor();
                instrutor.setId(rs.getInt("id_instrutor"));
                instrutor.setNome(rs.getString("nome"));
                instrutor.setEspecialidade(rs.getString("especialidade"));
                instrutores.add(instrutor);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar todos os instrutores: " + e.getMessage(), e);
        }
        return instrutores;
    }

    public void atualizar(Instrutor instrutor) {
        String sql = "UPDATE Instrutor SET nome = ?, especialidade = ? WHERE id_instrutor = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, instrutor.getNome());
            ps.setString(2, instrutor.getEspecialidade());
            ps.setInt(3, instrutor.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar instrutor: " + e.getMessage(), e);
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM Instrutor WHERE id_instrutor = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar instrutor: " + e.getMessage(), e);
        }
    }
}