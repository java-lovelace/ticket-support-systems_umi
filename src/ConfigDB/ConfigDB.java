package ConfigDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    // DATOS POOLER
    // host: aws-1-us-east-2.pooler.supabase.com
    // port: 6543
    // database: postgres
    // user: postgres.pathbalepzhuoknkhcvj  (ojo! incluye el project-id)
    // pool_mode: transaction

    private static final String URL =
            "jdbc:postgresql://aws-1-us-east-2.pooler.supabase.com:6543/postgres"
                    + "?sslmode=require&preferQueryMode=simple";

    private static final String USER = "postgres.pathbalepzhuoknkhcvj";
    private static final String PASS = "cXC9h#yXX2s!qQ6";

    static {
        try { Class.forName("org.postgresql.Driver"); }
        catch (ClassNotFoundException e) { throw new RuntimeException("Falta el driver JDBC de PostgreSQL.", e); }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
