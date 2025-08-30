package com.mycompany.gestaodeprojetosv2;

import com.mycompany.gestaodeprojetosv2.GestaoDeProjetosV2;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TarefaDAO {

    public void save(Tarefa tarefa) {
        String sql = "INSERT INTO tarefas ("
                + "idProjeto, "
                + "nome, "
                + "descricao, "
                + "observacoes, "
                + "isCompleted, "
                + "prazo, "
                + "dataCriacao, "
                + "dataAtualizacao) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, tarefa.getIdProjeto());
            statement.setString(2, tarefa.getNome());
            statement.setString(3, tarefa.getDescricao());
            statement.setString(4, tarefa.getObservacoes());
            statement.setBoolean(5, tarefa.isIsCompleted());
            statement.setDate(6, new java.sql.Date(tarefa.getPrazo().getTime()));
            statement.setDate(7, new java.sql.Date(tarefa.getDataCriacao().getTime()));
            statement.setDate(8, new java.sql.Date(tarefa.getDataAtualizacao().getTime()));
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao salvar a tarefa", ex);
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

    public void update(Tarefa tarefa) {
        String sql = "UPDATE tarefas SET "
                + "nome = ?, "
                + "descricao = ?, "
                + "observacoes = ?, "
                + "isCompleted = ?, "
                + "prazo = ?, "
                + "dataAtualizacao = ? "
                + "WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setString(1, tarefa.getNome());
            statement.setString(2, tarefa.getDescricao());
            statement.setString(3, tarefa.getObservacoes());
            statement.setBoolean(4, tarefa.isIsCompleted());
            statement.setDate(5, new java.sql.Date(tarefa.getPrazo().getTime()));
            statement.setDate(6, new java.sql.Date(tarefa.getDataAtualizacao().getTime()));
            statement.setInt(7, tarefa.getId());
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao atualizar a tarefa", ex);
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

    public void delete(int idTarefa) {
        String sql = "DELETE FROM tarefas WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idTarefa);
            statement.execute();
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao deletar a tarefa", ex);
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

    public Tarefa getById(int idTarefa) {
        String sql = "SELECT * FROM tarefas WHERE id = ?";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Tarefa tarefa = new Tarefa();

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, idTarefa);
            rs = statement.executeQuery();

            if (rs.next()) {
                tarefa.setId(rs.getInt("id"));
                tarefa.setIdProjeto(rs.getInt("idProjeto"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setObservacoes(rs.getString("observacoes"));
                tarefa.setIsCompleted(rs.getBoolean("isCompleted"));
                tarefa.setPrazo(rs.getDate("prazo"));
                tarefa.setDataCriacao(rs.getDate("dataCriacao"));
                tarefa.setDataAtualizacao(rs.getDate("dataAtualizacao"));
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar a tarefa", ex);
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
        return tarefa;
    }

    public List<Tarefa> getAll() {
        String sql = "SELECT * FROM tarefas";

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        List<Tarefa> tarefas = new ArrayList<Tarefa>();

        try {
            conn = GestaoDeProjetosV2.getConnection();
            statement = conn.prepareStatement(sql);
            rs = statement.executeQuery();

            while (rs.next()) {
                Tarefa tarefa = new Tarefa();
                tarefa.setId(rs.getInt("id"));
                tarefa.setIdProjeto(rs.getInt("idProjeto"));
                tarefa.setNome(rs.getString("nome"));
                tarefa.setDescricao(rs.getString("descricao"));
                tarefa.setObservacoes(rs.getString("observacoes"));
                tarefa.setIsCompleted(rs.getBoolean("isCompleted"));
                tarefa.setPrazo(rs.getDate("prazo"));
                tarefa.setDataCriacao(rs.getDate("dataCriacao"));
                tarefa.setDataAtualizacao(rs.getDate("dataAtualizacao"));
                tarefas.add(tarefa);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao buscar as tarefas", ex);
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
        return tarefas;
    }
}