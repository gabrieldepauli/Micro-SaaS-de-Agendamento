package model;

import java.sql.Time;

// Classe de Disponibilidade do Professor e seus atributos
public class Availability {
	private int id; // Auto increment no Banco de Dados
	private int teacher_id;
	private int day;
    private Time start_time;
    private Time end_time;
    private Time final_rest_time;
    private Time initial_rest_time;
    private Time service_duration;
    
    public Availability() {}

	public Availability(int id, int teacher_id, int day, Time start_time, Time end_time, Time final_rest_time,
			Time initial_rest_time, Time service_duration) {
		this.id = id;
		this.teacher_id = teacher_id;
		this.day = day;
		this.start_time = start_time;
		this.end_time = end_time;
		this.final_rest_time = final_rest_time;
		this.initial_rest_time = initial_rest_time;
		this.service_duration = service_duration;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTeacher_id() {
		return teacher_id;
	}

	public void setTeacher_id(int teacher_id) {
		this.teacher_id = teacher_id;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public Time getStart_time() {
		return start_time;
	}

	public void setStart_time(Time start_time) {
		this.start_time = start_time;
	}

	public Time getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Time end_time) {
		this.end_time = end_time;
	}

	public Time getFinal_rest_time() {
		return final_rest_time;
	}

	public void setFinal_rest_time(Time final_rest_time) {
		this.final_rest_time = final_rest_time;
	}

	public Time getInitial_rest_time() {
		return initial_rest_time;
	}

	public void setInitial_rest_time(Time initial_rest_time) {
		this.initial_rest_time = initial_rest_time;
	}

	public Time getService_duration() {
		return service_duration;
	}

	public void setService_duration(Time service_duration) {
		this.service_duration = service_duration;
	}
     
}

