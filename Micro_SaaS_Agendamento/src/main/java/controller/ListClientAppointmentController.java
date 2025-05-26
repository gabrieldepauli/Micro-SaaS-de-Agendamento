package controller;

import java.io.IOException;
import java.util.*;

import dao.AppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.User;
import model.Appointment;
import model.Status;

@WebServlet("/ListClientAppointment")
public class ListClientAppointmentController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("usuarioLogado");

		int page = 0;
	    int totalPages = 0;

	    String pageStr = request.getParameter("page");
	    if (pageStr != null){
	    	page = Integer.parseInt(pageStr);
	    }

	    String status = request.getParameter("status");
	    Status statusStr = null;
	    if (status != null && !status.isEmpty()) {
	    	statusStr = Status.valueOf(status.toUpperCase());
	    }

	    try {
			AppointmentDAO appointmentDao = new AppointmentDAO();

			List<Appointment> listAppointment;
			if (statusStr == null) {
				listAppointment = appointmentDao.listByClient(user.getId(), page);
			} else {
				listAppointment = appointmentDao.listByStatus(user.getId(), page, statusStr);
			}
			
			totalPages = appointmentDao.getPages();

			session.setAttribute("appointment", listAppointment);
	        session.setAttribute("page", page);
		    session.setAttribute("totalPages", totalPages);
	        session.setAttribute("selectedStatus", status);

	        request.getRequestDispatcher("/client/clientAppointmentList.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
