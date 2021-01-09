package Controllers;

import java.sql.*;

public class DatabaseConnection {
    //Specifies the URL used to connect to the database and the credentials used
    public static final String db_ConnectionURL = "jdbc:sqlserver://LAPTOP-EDR14DAJ\\SQLEXPRESS:1433;user=coursework_login;password=password;";

    // Method gets a connection to the database and runs query passed through
    public static Connection Connection() throws SQLException {
        Connection con = DriverManager.getConnection(db_ConnectionURL);
        System.out.println("Connection String Receieved");
        return con;
    }

    // Called by INSERT, UPDATE and DELETE statements
    protected static Boolean executeUpdate(String query){
        try{
            Connection con = Controllers.DatabaseConnection.Connection();
             Statement stmt = con.createStatement();
             stmt.executeUpdate(query);
             System.out.println("Query Ran: " + query);
             return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Called by SELECT statement
    protected static ResultSet executeQuery(String query){
        try{
            Connection con = Controllers.DatabaseConnection.Connection();
            Statement stmt = con.createStatement();
            ResultSet result = stmt.executeQuery(query);
            System.out.println("Query Ran: " + query);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
