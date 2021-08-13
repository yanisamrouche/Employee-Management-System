package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class EmployeeDatabase {

    /** Database **/
    public Connection getConnection(){
        Connection connection;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empproject","yanis","AMROUCHEyanis1998%");
            return connection;
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            return null;}
    }
    public ObservableList<Employee> getEmployeesList(){
        ObservableList<Employee> employeelist = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM Employee";
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Employee employees;
            while (rs.next()){
                employees = new Employee(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getDate("an"), rs.getString("ln"),rs.getInt("age"),rs.getString("fonction"), rs.getDouble("salaire"));
                employeelist.add(employees);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return employeelist;
    }

}
