package com.mycompany.gestaodeprojetosv2;

import com.mycompany.gestaodeprojetosv2.GestaoDeProjetosV2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

// Esta classe é um DAO (Data Access Object) e é responsável por toda a comunicação
// com o banco de dados. Ela isola a lógica de persistência da sua aplicação.
public class ProjetoDAO {

    /**
     * Salva um novo projeto no banco de dados.
     * @param projeto O objeto Projeto a ser salvo.
     */
    public void save(Projeto projeto) {
        // A instrução SQL para inserir um novo registro na tabela 'projects'.
        // O uso de '?' (placeholders) é uma prática segura contra SQL Injection.
        String sql = "INSERT INTO projects (nome, descricao, dataCriacao, dataAtualizacao) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            // Obtém a conexão com o banco de dados através da classe GestaoDeProjetosV2.
            conn = GestaoDeProjetosV2.getConnection();

            // Prepara a instrução SQL para ser executada.
            statement = conn.prepareStatement(sql);

            // Define os valores dos '?' com os atributos do objeto projeto.
            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getDescricao());
            statement.setDate(3, new java.sql.Date(projeto.getDataCriacao().getTime()));
            statement.setDate(4, new java.sql.Date(projeto.getDataAtualizacao().getTime()));

            // Executa o comando SQL.
            statement.execute();
        } catch (SQLException ex) {
            // Se houver um erro, lança uma exceção.
            throw new RuntimeException("Erro ao salvar o projeto", ex);
        } finally {
            // A seção 'finally' garante que os recursos do banco de dados (statement e conexão)
            // sejam fechados, mesmo que ocorra um erro. Isso previne vazamento de memória.
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    /**
     * Atualiza um projeto existente no banco de dados.
     * @param projeto O objeto Projeto com os dados a serem atualizados.
     */
    public void update(Projeto projeto) {
        String sql = "UPDATE projects SET nome = ?, descricao = ?, dataAtualizacao = ? WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, projeto.getNome());
            statement.setString(2, projeto.getDescricao());
            statement.setDate(3, new java.sql.Date(projeto.getDataAtualizacao().getTime()));
            statement.setInt(4, projeto.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar o projeto", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    /**
     * Deleta um projeto do banco de dados.
     * @param idProjeto O ID do projeto a ser deletado.
     */
    public void delete(int idProjeto) {
        String sql = "DELETE FROM projects WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProjeto);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar o projeto", ex);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
    }

    /**
     * Busca um projeto no banco de dados pelo seu ID.
     * @param idProjeto O ID do projeto a ser buscado.
     * @return O objeto Projeto encontrado ou um novo objeto se não for encontrado.
     */
    public Projeto getById(int idProjeto) {
        String sql = "SELECT * FROM projects WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Projeto projeto = new Projeto();

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idProjeto);
            rs = statement.executeQuery();

            if (rs.next()) {
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataCriacao(rs.getDate("dataCriacao"));
                projeto.setDataAtualizacao(rs.getDate("dataAtualizacao"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar o projeto", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return projeto;
    }

    /**
     * Busca todos os projetos no banco de dados.
     * @return Uma lista de objetos Projeto.
     */
    public List<Projeto> getAll() {
        String sql = "SELECT * FROM projects";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Projeto> projetos = new ArrayList<>();

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            // Itera sobre o conjunto de resultados e adiciona cada projeto à lista.
            while (rs.next()) {
                Projeto projeto = new Projeto();
                projeto.setId(rs.getInt("id"));
                projeto.setNome(rs.getString("nome"));
                projeto.setDescricao(rs.getString("descricao"));
                projeto.setDataCriacao(rs.getDate("dataCriacao"));
                projeto.setDataAtualizacao(rs.getDate("dataAtualizacao"));
                projetos.add(projeto);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar os projetos", ex);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                throw new RuntimeException("Erro ao fechar a conexão", ex);
            }
        }
        return projetos;
    }
}