package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

public class Appointment {

    private int id;
    private int clientId;
    private String clientName;
    private int professorId;
    private String professorName;
    private LocalDate date;
    private LocalTime time;
    private Status status;
    private LocalDateTime createdAt;

    public Appointment() {
        this.status = Status.SOLICITADO;
        this.createdAt = LocalDateTime.now();
    }

    public Appointment(int clientId, int professorId, LocalDate date, LocalTime time) {
        this.clientId = clientId;
        this.professorId = professorId;
        this.date = date;
        this.time = time;
        this.status = Status.SOLICITADO;
        this.createdAt = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }
    
    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public void setDate(LocalDate date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
}
