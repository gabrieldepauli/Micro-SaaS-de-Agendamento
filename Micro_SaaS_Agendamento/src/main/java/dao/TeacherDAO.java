package dao;

import java.sql.*;
import java.util.List;
import model.Teacher;
import model.User;
import util.ConnectionManager;

public class TeacherDAO {

    public boolean cadastrarProfessorComUsuario(User user, Teacher teacher) {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            conn.setAutoCommit(false);

            String sqlUsuario = "INSERT INTO usuario (email, senha, tipo) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, user.getEmail());
                stmt.setString(2, user.getSenha());
                stmt.setString(3, "TEACHER");
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1);
                    teacher.setId(usuarioId);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            String sqlTeacher = "INSERT INTO professor (id, nome_fantasia, nome_completo, foto_perfil, especialidade, endereco, descricao) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement stmt = conn.prepareStatement(sqlTeacher)) {
                stmt.setInt(1, teacher.getId());
                stmt.setString(2, teacher.getBusinessName());
                stmt.setString(3, teacher.getName());
                stmt.setString(4, teacher.getProfilePicture());
                stmt.setString(5, teacher.getSpecialty());
                stmt.setString(6, teacher.getAdress());
                stmt.setString(7, teacher.getDescription());
                stmt.executeUpdate();
            }

            List<String> imagens = teacher.getServiceImages();
            if (imagens != null && !imagens.isEmpty()) {
                String sqlImagem = "INSERT INTO imagem_portfolio (professor_id, caminho_arquivo) VALUES (?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(sqlImagem)) {
                    for (String path : imagens) {
                        stmt.setInt(1, teacher.getId());
                        stmt.setString(2, path);
                        stmt.addBatch();
                    }
                    stmt.executeBatch();
                }
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
