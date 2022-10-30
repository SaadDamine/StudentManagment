package sample.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcAccessUtil {
    private static Connection instance;

    public static Connection getInstance() {
        if (instance == null) {
            try {
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
                String databaseURL = "jdbc:ucanaccess://src/view/db/Database.accdb";
                instance = DriverManager.getConnection(databaseURL);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
}


