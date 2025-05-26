package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionManager;

public class PortfolioDAO {

	public void insertImage(int idTeacher, String caminhoArquivo) {
		String sql = "INSERT INTO imagem_portfolio (professor_id, caminho_arquivo) VALUES (?, ?)";
		
		try (Connection conn = ConnectionManager.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, idTeacher);
			ps.setString(2, caminhoArquivo);
			ps.executeUpdate();
			
		} catch (SQLException e){
			e.printStackTrace();
		}
		
	}

	public List<String> searchImageByTeacher(int id) {
		List<String> images = new ArrayList<>();
		String sql = "SELECT caminho_arquivo FROM imagem_portfolio WHERE professor_id = ?";
		
		try (Connection conn = ConnectionManager.getConnection();
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				images.add(rs.getString("caminho_arquivo"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return images;
	}
	
}