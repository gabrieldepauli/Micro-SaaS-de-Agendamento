import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class JNDIConnectionTest {

    public static void main(String[] args) {
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SistemaAgendamento");
            try (Connection conn = ds.getConnection()) {
                System.out.println("Conexão estabelecida com sucesso.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
