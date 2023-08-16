package Produk;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
    private java.sql.Connection connection;

    public void connect() {
        String url = "jdbc:mysql://localhost:3306/your_database_name";
        String user = "your_username";
        String password = "your_password";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
