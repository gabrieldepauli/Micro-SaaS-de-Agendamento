package model;

import java.time.LocalDateTime;

public class LogAppointment {
	private int id;
	private int appointmentId;
	private Status previousStatus;
    private Status newStatus;
    private LocalDateTime changedAt; 
	
	public LogAppointment() {
		this.changedAt = LocalDateTime.now();
	}

	public LogAppointment(int appointmentId, Status previousStatus, Status newStatus, LocalDateTime changedAt) {
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

	public LocalDateTime getChangedAt() {
		return changedAt;
	}

	public void setChangedAt(LocalDateTime changedAt) {
		this.changedAt = changedAt;
	}
	
}
