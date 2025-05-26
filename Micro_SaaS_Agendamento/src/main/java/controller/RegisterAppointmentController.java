package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import dao.AppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Appointment;
import model.User;

@WebServlet("/RegisterAppointment")
public class RegisterAppointmentController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int teacher_id = Integer.parseInt(request.getParameter("teacher_id"));
		String selectedDate = request.getParameter("data_agendamento");
		String selectedTime = request.getParameter("appointment_time");

		try {
			LocalDate appointmentDate = LocalDate.parse(selectedDate);
			LocalTime appointmentTime = LocalTime.parse(selectedTime);

			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("usuarioLogado");

			if (user != null && appointmentDate != null && appointmentTime != null) {
				Appointment appointment = new Appointment();
				appointment.setClientId(user.getId());
				appointment.setProfessorId(teacher_id);
				appointment.setDate(appointmentDate);
				appointment.setTime(appointmentTime);

				AppointmentDAO appointmentDao = new AppointmentDAO();
				appointmentDao.insertAppointment(appointment);

				session.setAttribute("mensagem", "Agendamento solicitado com sucesso!");

				response.sendRedirect("ShowAvailability?teacher_id=" + teacher_id);

			} else {
				session.setAttribute("mensagem", "Usuário não autenticado ou dados inválidos.");

				response.sendRedirect("ShowAvailability?teacher_id=" + teacher_id + "&data_agendamento=" + selectedDate);
			}

		} catch (Exception e) {
			HttpSession session = request.getSession();
			session.setAttribute("mensagem", "Erro ao realizar o agendamento.");

			response.sendRedirect("ShowAvailability?teacher_id=" + teacher_id + "&data_agendamento=" + selectedDate);
			
			e.printStackTrace();
		}
	}
	
}
