package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
    // --- Configure aqui os dados do seu banco ---
    private static final String URL = "jdbc:mysql://localhost:3306/gestao_academia"; // Altere para o nome do seu banco
    private static final String USER = "root"; // Altere para seu usuário
    private static final String PASSWORD = "root"; // Altere para sua senha

    public static Connection getConexao() {
        try {
            // Carrega o driver JDBC do MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Retorna a conexão com o banco de dados
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Lança uma exceção em caso de erro
            throw new RuntimeException("Erro na conexão com o banco de dados: ", e);
        }
    }
}