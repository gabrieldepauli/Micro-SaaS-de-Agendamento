package controller;

import dao.AvailabilityDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Availability;
import model.User;

import java.io.IOException;
import java.sql.Time;

@WebServlet("/RegisterDisponibilidade")
public class RegisterDisponibilidadeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AvailabilityDAO dao = new AvailabilityDAO();
        HttpSession session = request.getSession();
        User usuarioLogado = (User) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !"TEACHER".equals(usuarioLogado.getTipo())) {
            response.sendRedirect("login.jsp");
            return;
        }

        int professorId = usuarioLogado.getId();
        boolean algumErro = false;
        StringBuilder mensagensErro = new StringBuilder();

        for (int i = 1; i <= 7; i++) {
            try {
                String inicio = request.getParameter("inicio_" + i);
                String fim = request.getParameter("fim_" + i);
                String descansoInicio = request.getParameter("descanso_inicio_" + i);
                String descansoFim = request.getParameter("descanso_fim_" + i);
                String duracao = request.getParameter("tempo_servico_" + i);

                if (inicio == null || fim == null || duracao == null ||
                    inicio.isEmpty() || fim.isEmpty() || duracao.isEmpty()) {
                    continue;
                }

                Time horarioInicio = Time.valueOf(inicio + ":00");
                Time horarioFim = Time.valueOf(fim + ":00");
                Time descansoIni = (descansoInicio != null && !descansoInicio.isEmpty()) ? Time.valueOf(descansoInicio + ":00") : Time.valueOf("00:00:00");
                Time descansoFinal = (descansoFim != null && !descansoFim.isEmpty()) ? Time.valueOf(descansoFim + ":00") : Time.valueOf("00:00:00");
                Time tempoServico = Time.valueOf(duracao + ":00");

                Availability existente = dao.searchByTeacherAndDay(professorId, i);
                if (existente != null) {
                    algumErro = true;
                    mensagensErro.append("Já existe disponibilidade cadastrada para o dia ").append(nomeDoDia(i)).append(".<br>");
                    continue;
                }

                Availability disp = new Availability();
                disp.setTeacher_id(professorId);
                disp.setDay(i);
                disp.setStart_time(horarioInicio);
                disp.setEnd_time(horarioFim);
                disp.setInitial_rest_time(descansoIni);
                disp.setFinal_rest_time(descansoFinal);
                disp.setService_duration(tempoServico);

                dao.insertDisponibilidade(disp);

            } catch (Exception e) {
                algumErro = true;
                mensagensErro.append("Erro ao processar o dia ").append(nomeDoDia(i)).append(".<br>");
                e.printStackTrace();
            }
        }

        if (algumErro) {
            session.setAttribute("mensagem", mensagensErro.toString());
        } else {
            session.setAttribute("mensagem", "Horários cadastrados com sucesso!");
        }

        response.sendRedirect(request.getContextPath() + "/teacher/registerDisponibilidade.jsp");
    }

    private String nomeDoDia(int i) {
        switch (i) {
            case 1: return "Segunda-feira";
            case 2: return "Terça-feira";
            case 3: return "Quarta-feira";
            case 4: return "Quinta-feira";
            case 5: return "Sexta-feira";
            case 6: return "Sábado";
            case 7: return "Domingo";
            default: return "Desconhecido";
        }
    }
}
