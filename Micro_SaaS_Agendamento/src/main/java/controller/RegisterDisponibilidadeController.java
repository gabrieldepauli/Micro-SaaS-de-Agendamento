package controller;

import dao.AvailabilityDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Availability;
import model.User;

import java.io.IOException;
import java.sql.Time;

@WebServlet("/teacher/RegisterDisponibilidade")
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

                if ((inicio != null && !inicio.isEmpty()) || (fim != null && !fim.isEmpty()) || (duracao != null && !duracao.isEmpty())) {
                    if (inicio == null || inicio.isEmpty() || fim == null || fim.isEmpty() || duracao == null || duracao.isEmpty()) {
                        algumErro = true;
                        mensagensErro.append("Para o dia ").append(nomeDoDia(i)).append(", início, fim e duração devem ser preenchidos juntos.<br>");
                        
                        continue;
                    }
                } else {
                    continue;
                }

                Time horarioInicio = Time.valueOf(inicio + ":00");
                Time horarioFim = Time.valueOf(fim + ":00");

                if (horarioInicio.after(horarioFim) || horarioFim.before(horarioInicio)) {
                    algumErro = true;
                    mensagensErro.append("Horário de início não pode ser maior que o horário de fim no dia ").append(nomeDoDia(i)).append(".<br>");
                    
                    continue;
                }

                Time descansoIni = (descansoInicio != null && !descansoInicio.isEmpty()) ? Time.valueOf(descansoInicio + ":00") : null;
                Time descansoFinal = (descansoFim != null && !descansoFim.isEmpty()) ? Time.valueOf(descansoFim + ":00") : null;
                Time tempoServico = Time.valueOf(duracao + ":00");
                
                if (tempoServico.equals(Time.valueOf("00:00:00"))) {
                    algumErro = true;
                    mensagensErro.append("A duração do serviço no dia ").append(nomeDoDia(i)).append(" deve ser maior que zero.<br>");
                    continue;
                }

                if (descansoIni != null && descansoFinal != null) {

                    if (descansoIni.after(descansoFinal)) {
                        algumErro = true;
                        mensagensErro.append("Horário de início do descanso não pode ser maior que o horário de fim do descanso no dia ").append(nomeDoDia(i)).append(".<br>");
                        
                        continue;
                    }

                    if (descansoFinal.before(descansoIni)) {
                        algumErro = true;
                        mensagensErro.append("Horário de fim do descanso não pode ser menor que o horário de início do descanso no dia ").append(nomeDoDia(i)).append(".<br>");
                        
                        continue;
                    }

                    if (descansoIni.before(horarioInicio) || descansoFinal.after(horarioFim)) {
                        algumErro = true;
                        mensagensErro.append("Horário de descanso deve estar dentro do intervalo de início e fim da disponibilidade no dia ").append(nomeDoDia(i)).append(".<br>");
                        
                        continue;
                    }
                } else if ((descansoIni != null && descansoFinal == null) || (descansoIni == null && descansoFinal != null)) {
                    algumErro = true;
                    mensagensErro.append("Para o dia ").append(nomeDoDia(i)).append(", ambos os horários de descanso devem ser preenchidos ou deixados em branco.<br>");
                    
                    continue;
                }

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
                disp.setInitial_rest_time(descansoIni != null ? descansoIni : Time.valueOf("00:00:00"));
                disp.setFinal_rest_time(descansoFinal != null ? descansoFinal : Time.valueOf("00:00:00"));
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
