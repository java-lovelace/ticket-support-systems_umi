package ConfigDB;
import java.sql.Connection;

public class DBping {
    public static void main(String[] args) throws Exception {
        try (Connection c = ConfigDB.getConnection()) {
            System.out.println("Conexión OK: " + !c.isClosed());
        }
    }
}
