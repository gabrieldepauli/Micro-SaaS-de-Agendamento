package model;

import java.sql.Timestamp;

public class LogAppointment {
	private int id;
	private int appointmentId;
	private Status previousStatus;
    private Status newStatus;
    private Timestamp changedAt; 
	
	public LogAppointment() {}

	public LogAppointment(int appointmentId, Status previousStatus, Status newStatus, Timestamp  changedAt) {
		this.appointmentId = appointmentId;
		this.previousStatus = previousStatus;
		this.newStatus = newStatus;
		this.changedAt = changedAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public Status getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(Status previousStatus) {
		this.previousStatus = previousStatus;
	}

	public Status getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Status newStatus) {
		this.newStatus = newStatus;
	}

	public Timestamp getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(Timestamp  changedAt) {
		this.changedAt = changedAt;
	}
	
}
