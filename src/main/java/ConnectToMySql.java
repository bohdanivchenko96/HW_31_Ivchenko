

import java.sql.*;

public class ConnectToMySql {
    public Connection connection;
    public Statement statement;
    public String URL = "jdbc:mysql://localhost:3306/";
    public String userName = "root";
    public String userPassword = "Qwerty123";

    public void setUpConnectionWithDataBase() throws SQLException {
        connection = DriverManager
                .getConnection(URL, userName, userPassword);
        System.out.println("Connection is set up");
        statement = connection.createStatement();
        System.out.println("statement was created");
    }

    public void closeConnection() throws SQLException {
        if (statement != null)
            statement.close();
        System.out.println("statment was closed");
        if (connection != null)
            connection.close();
        System.out.println("Connection was closed");
    }
}