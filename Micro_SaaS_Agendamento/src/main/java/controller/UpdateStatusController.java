package controller;

import java.io.IOException;

import dao.AppointmentDAO;
import dao.LogAppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Appointment;
import model.LogAppointment;
import model.Status;

@WebServlet("/teacher/AtualizarStatusServlet")
public class UpdateStatusController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int idAgendamento = Integer.parseInt(request.getParameter("id_agendamento"));
			String novoStatus = request.getParameter("novo_status").toUpperCase();

			AppointmentDAO dao = new AppointmentDAO();
			Appointment agendamento = dao.searchById(idAgendamento);

			if (agendamento != null) {
				LogAppointment log = new LogAppointment();
				log.setAppointmentId(idAgendamento);
				log.setPreviousStatus(Status.valueOf(agendamento.getStatus().name()));
				log.setNewStatus(Status.valueOf(novoStatus));

				LogAppointmentDAO logDao = new LogAppointmentDAO();
				logDao.insertLog(log);

				dao.statusUpdate(idAgendamento, Status.valueOf(novoStatus));
			} else {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Agendamento não encontrado.");
				return;
			}

			response.sendRedirect(request.getContextPath() + "/teacher/ListTeacherAppointment");

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Status inválido.");
		} catch (Exception e) {
			e.printStackTrace();
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar status.");
		}
		
	}
	
}
