package dao;

import model.Frequencia;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FrequenciaDAO {

    public void salvar(Frequencia frequencia) {
        String sql = "INSERT INTO Frequencia (data, presenca, id_aluno) VALUES (?, ?, ?)";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setDate(1, new java.sql.Date(frequencia.getData().getTime()));
            ps.setBoolean(2, frequencia.isPresenca());
            ps.setInt(3, frequencia.getAluno().getId());
            ps.executeUpdate();

            try(ResultSet rs = ps.getGeneratedKeys()){
                if(rs.next()){
                    frequencia.setId(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar frequência: " + e.getMessage(), e);
        }
    }
    
    public List<Frequencia> buscarPorAluno(int idAluno) {
        String sql = "SELECT * FROM Frequencia WHERE id_aluno = ? ORDER BY data DESC";
        List<Frequencia> frequencias = new ArrayList<>();
        
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idAluno);
            try (ResultSet rs = ps.executeQuery()) {
                // Usamos um AlunoDAO para não ter que buscar os dados do aluno toda vez
                AlunoDAO alunoDAO = new AlunoDAO();
                Aluno aluno = alunoDAO.buscarPorId(idAluno);

                while(rs.next()) {
                    Frequencia freq = new Frequencia();
                    freq.setId(rs.getInt("id_frequencia"));
                    freq.setData(rs.getDate("data"));
                    freq.setPresenca(rs.getBoolean("presenca"));
                    freq.setAluno(aluno); // Reutiliza o objeto aluno
                    frequencias.add(freq);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar frequência por aluno: " + e.getMessage(), e);
        }
        return frequencias;
    }
    
    public void deletar(int idFrequencia) {
        String sql = "DELETE FROM Frequencia WHERE id_frequencia = ?";
        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idFrequencia);
            ps.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar frequência: " + e.getMessage(), e);
        }
    }

    public void DeletarTodoFrequenciaUsuario(int id) {
        String sql = "DELETE "
                + "FROM Frequencia fr "
                + "WHERE fr.id_aluno = ?";

        try (Connection conn = ConexaoFactory.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar" + e.getMessage(), e);
        }
    }
}