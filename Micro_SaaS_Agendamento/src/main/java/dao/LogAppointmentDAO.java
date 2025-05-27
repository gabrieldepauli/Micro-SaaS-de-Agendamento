package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.LogAppointment;
import model.Status;
import util.ConnectionManager;

public class LogAppointmentDAO {

	public void insertLog(LogAppointment log) throws Exception {
		String sql = "INSERT INTO log_agendamento (agendamento_id, status_anterior, status_novo) VALUES (?, ?, ?)";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, log.getAppointmentId());
			ps.setString(2, log.getPreviousStatus().name());
			ps.setString(3, log.getNewStatus().name());
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<LogAppointment> listByTeacher(int appointment_id) {
		List<LogAppointment> list = new ArrayList<LogAppointment>();
		String sql = "SELECT * FROM log_agendamento WHERE agendamento_id = ? ORDER BY alterado_em";
		
		try (Connection conn = ConnectionManager.getConnection();
   			 	PreparedStatement ps = conn.prepareStatement(sql)) {
			
			ps.setInt(1, appointment_id);
			
			try (ResultSet rs = ps.executeQuery()) {
				
				while (rs.next()) {
					LogAppointment log = new LogAppointment();
					
					log.setId(rs.getInt("id"));
					log.setAppointmentId(rs.getInt("agendamento_id"));
					
					String statusOld = rs.getString("status_anterior");
					log.setPreviousStatus(Status.valueOf(statusOld));
					String statusNew = rs.getString("status_novo");
					log.setNewStatus(Status.valueOf(statusNew));
					
					log.setChangedAt(rs.getTimestamp("alterado_em"));
					
					list.add(log);
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
