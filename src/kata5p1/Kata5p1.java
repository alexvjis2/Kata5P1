package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Kata5p1 {

    public static void main(String[] args) {
        createTable();
    }

    public static void selectAll() {
        String query = "SELECT * FROM PEOPLE";
        try (Connection conn = connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.print(rs.getInt("id") + "\t");
                System.out.print(rs.getString("name") + "\t");
                System.out.print(rs.getString("apellidos") + "\t");
                System.out.println(rs.getString("departamento") + "\t");
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            System.exit(-1);
        }
    }

    private static boolean createTable() {
        String dbroute = "jdbc:sqlite:KATA5.db";
        String sql = "CREATE TABLE IF NOT EXISTS EMAIL (\n"
                + " ID integer PRIMARY KEY AUTOINCREMENT,\n"
                + " Mail text NOT NULL);";

        try (Connection conn = connect();
            Statement stmt = conn.createStatement()) {
            return stmt.execute(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        
        return false;
    }

    private static Connection connect() throws SQLException {
        Connection conn = null;
        String url = "jdbc:sqlite:KATA5.db";
        conn = DriverManager.getConnection(url);
        return conn;
    }

}
