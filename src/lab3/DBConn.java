package lab3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {

    private static final String DRV_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String CONN_STRING
            = "jdbc:mysql://localhost:3306/?"
            + "user=admin&password=admin&"
            + "useLegacyDatetimeCode=false&"
            + "serverTimezone=UTC" /*+ "useSSL=false"*/;

    private static Connection conn = null;

    public DBConn() {

        //доступ к драйверу
        try {
            Class.forName(DRV_NAME);
        } catch (ClassNotFoundException ex) {
            System.out.println("MySQL driver not found!");
            return;
        }
        //подключение к серваку
        try {
            //присвоение conn
            conn = DriverManager.getConnection(CONN_STRING);
        } catch (SQLException ex) {
            System.out.println("Cannot open conection!" + ex.getMessage());
            return;
        }
    }

    public static void executeMyQuerry(String input) {

        ResultSet rs = null;
        try {
            Statement st = conn.createStatement();
            st.executeUpdate("USE my");

            /*
            rs = conn.getMetaData().getCatalogs();
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_CAT"));
            }
             */
            rs = st.executeQuery(input);
            int col = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                //System.out.println(rs.getString("id") + " - " + rs.getString("name"));
                for (int i = 1; i <= col; i++) {
                    if (i > 1) {
                        System.out.print(" ");
                    }
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
