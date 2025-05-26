package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Client;
import model.User;
import util.ConnectionManager;

public class ClientDAO {

    public boolean cadastrarClienteComUsuario(User user, Client client) {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            conn.setAutoCommit(false);

            String sqlUsuario = "INSERT INTO usuario (email, senha, tipo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getEmail());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, "CLIENT");
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1);
                    client.setId(usuarioId);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            String sqlClient = "INSERT INTO cliente (id, full_name, cpf, adress, phone) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlClient)) {
                stmt.setInt(1, client.getId());
                stmt.setString(2, client.getName());
                stmt.setString(3, client.getCpf());
                stmt.setString(4, client.getAdress());
                stmt.setString(5, client.getNumber());
                stmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) conn.setAutoCommit(true);
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
