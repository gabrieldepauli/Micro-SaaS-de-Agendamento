package controller;

import java.io.IOException;
import java.util.*;

import dao.LogAppointmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.LogAppointment;

@WebServlet("/teacher/LogController")
public class LogController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int appointmentId = Integer.parseInt(request.getParameter("appointment_Id"));

        try{
            LogAppointmentDAO logDao = new LogAppointmentDAO();
            List<LogAppointment> logsList = logDao.listByTeacher(appointmentId);

            request.setAttribute("logs", logsList);
            
            request.getRequestDispatcher("/teacher/log.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
	}
	
}
