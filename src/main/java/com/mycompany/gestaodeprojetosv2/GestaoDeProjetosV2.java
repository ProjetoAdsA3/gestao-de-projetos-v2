package com.mycompany.gestaodeprojetosv2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GestaoDeProjetosV2 {

    // --- Configurações do Banco de Dados ---
    // A URL de conexão com o MySQL, especificando o host, a porta e o nome do banco.
    // O parâmetro 'projects' é o nome do seu banco de dados.
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/projects?useTimezone=true&serverTimezone=UTC";
    // O nome de usuário para acessar o banco de dados.
    private static final String DATABASE_USER = "professor";
    // A senha do usuário. ATENÇÃO: Em projetos reais, a senha não deve ser codificada diretamente aqui.
    private static final String DATABASE_PASSWORD = "prof2025";

    // --- Método de Conexão ---
    // Este método estático retorna uma nova conexão com o banco de dados.
    public static Connection getConnection() throws SQLException {
        try {
            // Tenta estabelecer a conexão usando a URL, o usuário e a senha.
            return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            // Em caso de erro na conexão, lança uma exceção com uma mensagem mais clara.
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }
}