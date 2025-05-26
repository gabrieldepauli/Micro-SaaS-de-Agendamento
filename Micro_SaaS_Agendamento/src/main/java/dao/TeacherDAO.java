package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Teacher;
import model.User;
import util.ConnectionManager;

public class TeacherDAO {
	
	private int itemsPerPage  = 6;

    public boolean cadastrarProfessorComUsuario(User user, Teacher teacher) {
        Connection conn = null;

        try {
            conn = ConnectionManager.getConnection();
            conn.setAutoCommit(false);

            String sqlUsuario = "INSERT INTO usuario (email, senha, tipo) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS)) {
            	ps.setString(1, user.getEmail());
            	ps.setString(2, user.getSenha());
            	ps.setString(3, "TEACHER");
            	ps.executeUpdate();

                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int usuarioId = rs.getInt(1);
                    teacher.setId(usuarioId);
                } else {
                    conn.rollback();
                    return false;
                }
            }

            String sqlTeacher = "INSERT INTO professor (id, businessName, fullName, profilePicture, specialty, address, descricao) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement ps = conn.prepareStatement(sqlTeacher)) {
            	ps.setInt(1, teacher.getId());
            	ps.setString(2, teacher.getBusinessName());
            	ps.setString(3, teacher.getName());
            	ps.setString(4, teacher.getProfilePicture());
            	ps.setString(5, teacher.getSpecialty());
            	ps.setString(6, teacher.getAddress());
            	ps.setString(7, teacher.getDescription());
            	ps.executeUpdate();
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
    
    public List<Teacher> listTeacher(int page) {
    	List<Teacher> list = new ArrayList<>();
    	String sql = "SELECT * FROM professor " + "ORDER BY businessName " + "LIMIT ? OFFSET ?";
    	
    	try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
   			
   			ps.setInt(1, itemsPerPage );
   			ps.setInt(2, itemsPerPage * page);
   			ResultSet rs = ps.executeQuery();
   			
   			while (rs.next()) {
   				Teacher teacher = new Teacher();
   				
   				teacher.setId(rs.getInt("id"));
   				teacher.setBusinessName(rs.getString("businessName"));
   				teacher.setName(rs.getString("fullName"));
   				teacher.setProfilePicture(rs.getString("profilePicture"));
   				teacher.setSpecialty(rs.getString("specialty"));
   				teacher.setAddress(rs.getString("address"));
   				teacher.setDescription(rs.getString("descricao"));
   				teacher.setDate(rs.getDate("data_criacao"));
   				
   				list.add(teacher);
   			}
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	
    	return list;
    }
    
    public List<Teacher> searchByCity(String city, int page) {
    	List<Teacher> list = new ArrayList<>();
    	String sql = "SELECT * FROM professor WHERE address LIKE ? " + "LIMIT ? OFFSET ?";
    	
    	try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
   			
   			ps.setString(1,  "%" + (city != null ? city : "") + "%");
   			ps.setInt(2, itemsPerPage);
   			ps.setInt(3, itemsPerPage * page);
   			ResultSet rs = ps.executeQuery();
   			
   			while (rs.next()) {
   				Teacher teacher = new Teacher();
   				
   				teacher.setId(rs.getInt("id"));
   				teacher.setBusinessName(rs.getString("businessName"));
   				teacher.setName(rs.getString("fullName"));
   				teacher.setProfilePicture(rs.getString("profilePicture"));
   				teacher.setSpecialty(rs.getString("specialty"));
   				teacher.setAddress(rs.getString("address"));
   				teacher.setDescription(rs.getString("descricao"));
   				teacher.setDate(rs.getDate("data_criacao"));
   				
   				list.add(teacher);
   			}
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	
    	return list;
    }
    
    public int getPages() {
    	int pages = 0;
    	String sql = "SELECT COUNT(id) AS total FROM professor";
    	
    	try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
   			
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		
    		pages = rs.getInt("total");
    		pages = (int) Math.ceil((double) pages / itemsPerPage );
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	
    	return pages;
    }
    
    public int getPagesByCity(String city) {
    	int pages = 0;
    	String sql = "SELECT COUNT(id) AS total FROM professor WHERE address LIKE ?";
    	
    	try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
   			
    		ps.setString(1, "%" + (city != null ? city : "") + "%");
    		ResultSet rs = ps.executeQuery();
    		rs.next();
    		
    		pages = rs.getInt("total");
    		pages = (int) Math.ceil((double) pages / itemsPerPage );
   			
   		} catch (SQLException e) {
   			e.printStackTrace();
   		}
    	
    	return pages;
    }
    
}
