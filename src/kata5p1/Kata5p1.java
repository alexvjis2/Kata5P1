package kata5p1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Kata5p1 {

    private static final String emails = "email.txt";
    private static Connection conn = null;

    public static void main(String[] args) {
        connect();
        insert(MailListReader.read(emails));
    }

    private static void insert(List<String> emails) {
        String sql = "INSERT INTO EMAIL ( Mail ) VALUES ( ? )";

        try ( PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (String email : emails) {
                pstmt.setString(1, email);
                pstmt.executeUpdate(); 
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

    public static void selectAll() {
        String query = "SELECT * FROM PEOPLE";
        try ( Statement stmt = conn.createStatement();
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

        try (Statement stmt = conn.createStatement()) {
            return stmt.execute(sql);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

        return false;
    }

    private static void connect() {
        try {
            String url = "jdbc:sqlite:KATA5.db";
            conn = DriverManager.getConnection(url);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
    }

}
