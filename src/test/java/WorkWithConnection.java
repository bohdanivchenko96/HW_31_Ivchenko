import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class WorkWithConnection {
    ConnectToMySql connectToMySql = new ConnectToMySql();
    public String dataBaseName = "HillelGroup";
    public String tableName = "Students";
    Connection connectToTable;


    @BeforeClass
    public void runConnect() throws SQLException {
        connectToMySql.setUpConnectionWithDataBase();
    }

    @BeforeMethod
    public void connectToTable(){
        try {
            connectToTable = DriverManager
                    .getConnection(connectToMySql.URL + dataBaseName, connectToMySql.userName, connectToMySql.userPassword);


            connectToMySql.statement = connectToTable.createStatement();
            System.out.println("Connection with table is set up");
            } catch (SQLException throwables) {
                System.out.println("Connect to the table is failed");
                throwables.printStackTrace();
            }
        }


    @AfterClass
    public void closeConnect() throws SQLException {
        connectToMySql.closeConnection();
    }
}
