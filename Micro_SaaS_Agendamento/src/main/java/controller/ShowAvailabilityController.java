package controller;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeParseException;
import java.util.*;

import dao.AvailabilityDAO;
import jakarta.servlet.http.*;
import model.Availability;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;

@WebServlet("/ShowAvailability")
public class ShowAvailabilityController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> availability = new ArrayList<>();
		
		int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
		String date = request.getParameter("data_agendamento");
		
		LocalDate schedulingDate = null;
		
		try {
			if (date != null && !date.trim().isEmpty()) {
				schedulingDate = LocalDate.parse(date);
				
				DayOfWeek dayWeek = schedulingDate.getDayOfWeek();
				int dayOfWeekInteger = dayWeek.getValue();
				
				AvailabilityDAO availabilityDao = new AvailabilityDAO();
				Availability ava = availabilityDao.searchByTeacherAndDay(teacher_id, dayOfWeekInteger);
				
				if (ava != null) {
					LocalTime start = ava.getStart_time().toLocalTime();
					LocalTime end = ava.getEnd_time().toLocalTime();
					LocalTime initial_rest = ava.getInitial_rest_time().toLocalTime();
					LocalTime final_rest = ava.getFinal_rest_time().toLocalTime();
					
					Duration duration = Duration.between(LocalTime.MIN, ava.getService_duration().toLocalTime());
					LocalTime current = start;

					List<LocalTime> takenTimes = availabilityDao.getTakenTimes(teacher_id, schedulingDate);
					
					while (current.plus(duration).compareTo(end) <= 0) {
					    boolean isBeforeRest = current.plus(duration).compareTo(initial_rest) <= 0;
					    boolean isAfterRest = current.compareTo(final_rest) >= 0;

					    boolean isToday = schedulingDate.equals(LocalDate.now());
					    boolean isFutureTime = !isToday || current.isAfter(LocalTime.now());

					    if ((isBeforeRest || isAfterRest) && isFutureTime && !takenTimes.contains(current)) {
					        availability.add(current.toString());
					    }

					    current = current.plus(duration);
					}
				}
			} 
			
		} catch (DateTimeParseException e) {
			System.err.println("Data inválida fornecida: " + date);
		} catch (Exception e) {
			System.err.println("Erro inesperado ao buscar disponibilidade.");
			e.printStackTrace();
		}
		
		request.setAttribute("availableTimes", availability);
		request.setAttribute("teacher_id", teacher_id);
		request.setAttribute("selectedDate", date);
		
		request.getRequestDispatcher("/client/appointment.jsp").forward(request, response);
	}
}
