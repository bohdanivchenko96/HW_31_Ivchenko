
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.testng.Assert.assertEquals;

public class WorkWithSQL extends WorkWithConnection {
    @Test
    public void createDataBase() {

        try {
            String createBaseSql = "CREATE DATABASE " + dataBaseName;
            connectToMySql.statement.executeUpdate(createBaseSql);
            System.out.println("DataBase is successfully created");
        } catch (SQLException e) {
            System.out.println("DataBase is existed");
        }
    }

    @Test
    public void createTable() {

        try {
            String createTableStudents = "CREATE TABLE " + tableName +
                    "(id INTEGER not NULL, " +
                    " name VARCHAR(50), " +
                    " surname VARCHAR (50), " +
                    " averageScore INTEGER not NULL, " +
                    " CHECK (averageScore>10 && averageScore<=100), " +
                    " PRIMARY KEY (id))";
            connectToMySql.statement.executeUpdate(createTableStudents);
            System.out.println("Table is successfully created");
        } catch (SQLException e) {
            System.out.println("Creating table was failed");
            e.printStackTrace();
        }
    }

    @Test
    public void insertIntoTable() {
        int id = 3;
        String name = "testname";
        String surname = "testSurname";
        int averageScore = 75;

        try {
            String insert = "INSERT INTO " + tableName +
                    " (`id`, `name`, `surname`, `averageScore`) " +
                    "VALUES (" +
                    "'" + id + "', " +
                    "'" + name + "', " +
                    "'" + surname + "', " +
                    "'" + averageScore + "')";

            connectToMySql.statement.executeUpdate(insert);
            System.out.println("New row is successfully inserted");
        } catch (SQLException throwables) {
            System.out.println("Inserting was failed");
            throwables.printStackTrace();
        }

    }

    @Test
    public void selectTable() {
        String select = "SELECT * FROM " + tableName;
        try {

            ResultSet resultSet = connectToMySql.statement.executeQuery(select);
            while (resultSet.next()) {
                Student student = new Student(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("surname"),
                        resultSet.getInt("averageScore"));
                System.out.println(student);
            }
            resultSet.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @Test
    public void updateRow(){
        String update = "UPDATE " + tableName +
                " SET averageScore = 15 WHERE id = 1";
        try {
            connectToMySql.statement.executeUpdate(update);
            System.out.println("Row is successfully updated");
        } catch (SQLException throwables) {
            System.out.println("row update is failed");
            throwables.printStackTrace();
        }
    }
    @Test
    public void deleteRow(){
        String delete = "DELETE FROM " + tableName +
                " WHERE id = 3";
        try {
            connectToMySql.statement.executeUpdate(delete);
            System.out.println("Row is successfully deleted");
        } catch (SQLException throwables) {
            System.out.println("Row is not deleted");
            throwables.printStackTrace();
        }
    }

    @Test
    public void dropTable(){
        String dropTable = "DROP TABLE " + tableName;
        String resultMessage;
        try {
            connectToMySql.statement.executeUpdate(dropTable);

            resultMessage = "Table is successfully dropped";
            System.out.println(resultMessage);
        } catch (SQLException throwables) {
            resultMessage = "Deleting table is failed";
            System.out.println(resultMessage);
            throwables.printStackTrace();
        }
        assertEquals(resultMessage, "Table is successfully dropped");
    }
    @Test
    public void dropDataBase(){
        String dropDataBase = "DROP DATABASE " + dataBaseName;
        String resultMessage;
        try {
            connectToMySql.statement.executeUpdate(dropDataBase);
            resultMessage = "Database is successfully dropped";
            System.out.println(resultMessage);
        } catch (SQLException throwables) {
            resultMessage = "Deleting DB is failed";
            System.out.println(resultMessage);
            throwables.printStackTrace();
        }
        assertEquals(resultMessage, "Database is successfully dropped");
    }
}
