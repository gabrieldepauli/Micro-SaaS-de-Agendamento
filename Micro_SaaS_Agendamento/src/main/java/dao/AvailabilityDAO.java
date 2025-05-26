package dao;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import model.Availability;
import util.ConnectionManager;

public class AvailabilityDAO {

	public void insertDisponibilidade(Availability availability) {
	    String sql = "INSERT INTO disponibilidade (professor_id, dia_semana, horario_inicio, horario_fim, horario_descanso_inicio, horario_descanso_fim, tempo_servico) VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setInt(1, availability.getTeacher_id());
	        ps.setInt(2, availability.getDay());
	        ps.setTime(3, availability.getStart_time());
	        ps.setTime(4, availability.getEnd_time());
	        ps.setTime(5, availability.getInitial_rest_time());
	        ps.setTime(6, availability.getFinal_rest_time());
	        ps.setTime(7, availability.getService_duration());
	        ps.executeUpdate();

	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            availability.setId(rs.getInt(1));
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    
	}

	
	public Availability searchByTeacherAndDay(int teacher_id, int day) {
	    String sql = "SELECT * FROM disponibilidade WHERE professor_id = ? AND dia_semana = ?";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, teacher_id);
	        ps.setInt(2, day);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Availability availability = new Availability();
	            
	            availability.setId(rs.getInt("id"));
	            availability.setTeacher_id(rs.getInt("professor_id"));
	            availability.setDay(rs.getInt("dia_semana"));
	            availability.setStart_time(rs.getTime("horario_inicio"));
	            availability.setEnd_time(rs.getTime("horario_fim"));
	            availability.setInitial_rest_time(rs.getTime("horario_descanso_inicio"));
	            availability.setFinal_rest_time(rs.getTime("horario_descanso_fim"));
	            availability.setService_duration(rs.getTime("tempo_servico"));

	            return availability;
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public List<Availability> searchByTeacher(int teacher_id) {
		List<Availability> list = new ArrayList<>();
		String sql = "SELECT * FROM disponibilidade WHERE professor_id = ? ORDER BY dia_semana, horario_inicio";
		
		try (Connection conn = ConnectionManager.getConnection(); 
				PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, teacher_id);
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	        	Availability availability = new Availability();
	        	
	        	availability.setId(rs.getInt("id"));
	        	availability.setTeacher_id(rs.getInt("professor_id"));
	        	availability.setDay(rs.getInt("dia_semana"));
	        	availability.setStart_time(rs.getTime("horario_inicio"));
                availability.setEnd_time(rs.getTime("horario_fim"));
                availability.setInitial_rest_time(rs.getTime("horario_descanso_inicio"));
                availability.setFinal_rest_time(rs.getTime("horario_descanso_fim"));
                availability.setService_duration(rs.getTime("tempo_servico"));
                
                list.add(availability);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public List<LocalTime> getTakenTimes(int professorId, LocalDate date) throws Exception {
	    List<LocalTime> takenTimes = new ArrayList<>();

	    String sql = "SELECT horario FROM agendamento WHERE professor_id = ? AND data_agendamento = ?";

	    try (Connection conn = ConnectionManager.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setInt(1, professorId);
	        ps.setDate(2, Date.valueOf(date));

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                takenTimes.add(rs.getTime("horario").toLocalTime());
	            }
	        }
	    }

	    return takenTimes;
	}
	
}
