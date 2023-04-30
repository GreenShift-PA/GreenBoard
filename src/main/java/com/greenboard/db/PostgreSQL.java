package com.greenboard.db;
import java.sql.Connection;
import java.sql.SQLException;

public class PostgreSQL {
    public static final String JDBC_DRIVER = "org.postgresql.Driver";

    public static final String DB_URL = "jdbc:postgresql://localhost:54321/greenboard";
    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "postgres";

    private static final String AUTH_USER_QUERY = "SELECT * FROM users WHERE email = ? AND password = ?";

    public static boolean authenticate(String email, String password)
    {
        try(Connection conn = java.sql.DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD))
        {
            java.sql.PreparedStatement stmt = conn.prepareStatement(AUTH_USER_QUERY);
            stmt.setString(1, email);
            stmt.setString(2, password);
            java.sql.ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
        catch(java.sql.SQLException e)
        {
            printSQLException(e);
            return false;
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
