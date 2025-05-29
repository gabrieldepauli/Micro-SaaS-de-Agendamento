package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Appointment;
import model.Status;
import util.ConnectionManager;

public class AppointmentDAO {

	private int itemsPerPage  = 6;
	
	public void insertAppointment(Appointment appointment) throws Exception {
		Connection conn = null;
		String sql = "INSERT INTO agendamento (cliente_id, professor_id, data_agendamento, horario, status) VALUES (?, ?, ?, ?, ?)";
	
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			
			if (appointment.getStatus() == null) {
	            appointment.setStatus(Status.SOLICITADO);
	        }

			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, appointment.getClientId());
				ps.setInt(2, appointment.getProfessorId());
				ps.setDate(3, java.sql.Date.valueOf(appointment.getDate()));
				ps.setTime(4, java.sql.Time.valueOf(appointment.getTime()));
				ps.setString(5, appointment.getStatus().name());

				ps.executeUpdate();
			}

			conn.commit();

		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			throw new Exception("Erro ao inserir agendamento: " + e.getMessage(), e);

		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true);
					conn.close();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		}
		
	}
	
	public Appointment searchById(int id_appointment) {
		Appointment appointment = new Appointment();
		String sql = "SELECT * FROM agendamento WHERE id = ?";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, id_appointment);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					appointment.setId(rs.getInt("id"));
					appointment.setClientId(rs.getInt("cliente_id"));
					appointment.setProfessorId(rs.getInt("professor_id"));
					appointment.setDate(rs.getDate("data_agendamento").toLocalDate());
					appointment.setTime(rs.getTime("horario").toLocalTime());

					String status = rs.getString("status");
					appointment.setStatus(Status.valueOf(status));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return appointment;
	}
	
	public List<Appointment> listByClient(int client_id, int page) {
		List<Appointment> list = new ArrayList<Appointment>();
		String sql = """
			    SELECT a.*, p.fullName AS professor_name
			    FROM agendamento a
			    JOIN professor p ON a.professor_id = p.id
			    WHERE a.cliente_id = ?
			    ORDER BY a.data_agendamento, a.horario
			    LIMIT ? OFFSET ?
			""";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, client_id);
			ps.setInt(2, itemsPerPage);
			ps.setInt(3, itemsPerPage * page);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					Appointment appointment = new Appointment();
					
					appointment.setId(rs.getInt("id"));
					appointment.setClientId(rs.getInt("cliente_id"));
					appointment.setProfessorId(rs.getInt("professor_id"));
					appointment.setDate(rs.getDate("data_agendamento").toLocalDate());
					appointment.setTime(rs.getTime("horario").toLocalTime());

					String status = rs.getString("status");
					appointment.setStatus(Status.valueOf(status));
					
					appointment.setProfessorName(rs.getString("professor_name"));
					
					list.add(appointment);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Appointment> listByTeacher(int teacher_id, int page) {
		List<Appointment> list = new ArrayList<Appointment>();	
		String sql = """
			    SELECT a.*, c.full_name AS client_name
			    FROM agendamento a
			    JOIN cliente c ON a.cliente_id = c.id
			    WHERE a.professor_id = ?
			    ORDER BY a.data_agendamento, a.horario
			    LIMIT ? OFFSET ?
			""";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, teacher_id);
			ps.setInt(2, itemsPerPage);
			ps.setInt(3, itemsPerPage * page);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					Appointment appointment = new Appointment();
					
					appointment.setId(rs.getInt("id"));
					appointment.setClientId(rs.getInt("cliente_id"));
					appointment.setProfessorId(rs.getInt("professor_id"));
					appointment.setDate(rs.getDate("data_agendamento").toLocalDate());
					appointment.setTime(rs.getTime("horario").toLocalTime());

					String status = rs.getString("status");
					appointment.setStatus(Status.valueOf(status));
					
					appointment.setClientName(rs.getString("client_name"));
					
					list.add(appointment);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<Appointment> listByStatus(int client_id, int page, Status status) {
	    List<Appointment> list = new ArrayList<>();
	    String sql = """
	        SELECT a.*, p.fullName 
	        FROM agendamento a
	        JOIN professor p ON a.professor_id = p.id
	        WHERE a.cliente_id = ? AND a.status = ?
	        ORDER BY a.data_agendamento, a.horario
	        LIMIT ? OFFSET ?
	    """;

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, client_id);
	        ps.setString(2, status.name());
	        ps.setInt(3, itemsPerPage);
	        ps.setInt(4, itemsPerPage * page);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Appointment appointment = new Appointment();

	                appointment.setId(rs.getInt("id"));
	                appointment.setClientId(rs.getInt("cliente_id"));
	                appointment.setProfessorId(rs.getInt("professor_id"));
	                appointment.setDate(rs.getDate("data_agendamento").toLocalDate());
	                appointment.setTime(rs.getTime("horario").toLocalTime());
	                appointment.setStatus(Status.valueOf(rs.getString("status")));

	                appointment.setProfessorName(rs.getString("fullName"));

	                list.add(appointment);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return list;
	}

	
	public List<Appointment> listByStatusTeacher(int professor_id, int page, Status status) {
	    List<Appointment> list = new ArrayList<>();
	    String sql = """
	        SELECT a.*, c.full_name 
	        FROM agendamento a
	        JOIN cliente c ON a.cliente_id = c.id
	        WHERE a.professor_id = ? AND a.status = ?
	        ORDER BY a.data_agendamento, a.horario
	        LIMIT ? OFFSET ?
	    """;

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        ps.setInt(1, professor_id);
	        ps.setString(2, status.name());
	        ps.setInt(3, itemsPerPage);
	        ps.setInt(4, itemsPerPage * page);

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Appointment appointment = new Appointment();

	                appointment.setId(rs.getInt("id"));
	                appointment.setClientId(rs.getInt("cliente_id"));
	                appointment.setProfessorId(rs.getInt("professor_id"));
	                appointment.setDate(rs.getDate("data_agendamento").toLocalDate());
	                appointment.setTime(rs.getTime("horario").toLocalTime());
	                appointment.setStatus(Status.valueOf(rs.getString("status")));

	                appointment.setClientName(rs.getString("full_name"));

	                list.add(appointment);
	            }
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    System.out.println(list.size());
	    
	    return list;
	}

	
	public int getPages() {
		int pages = 0;
		String sql = "SELECT COUNT(id) AS total FROM agendamento";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)){

			ResultSet rs = ps.executeQuery();
			rs.next();
			
			pages = rs.getInt("total");
			pages = (int) Math.ceil((double) pages / itemsPerPage);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pages;
	}
	
	public int getPagesByStatus(Status status) {
		int pages = 0;
		String sql = "SELECT COUNT(id) AS total FROM agendamento WHERE status LIKE ?";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)){
			
			ps.setString(1, status.name());
			ResultSet rs = ps.executeQuery();
			rs.next();
			
			pages = rs.getInt("total");
			pages = (int) Math.ceil((double) pages / itemsPerPage);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return pages;
	}
	
	public void statusUpdate(int id_appointment, Status newStatus) {
	    String sql = "UPDATE agendamento SET status = ? WHERE id = ?";
	    
	    try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	    	ps.setString(1, newStatus.name());
	    	ps.setInt(2, id_appointment);
	        
	    	ps.executeUpdate();
	    	
	    }catch (SQLException e) {
			e.printStackTrace();
		}
	    
	}
	
}
