package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Salary {

    /********************* TextFields ***********************/
    @FXML
    TextField tfRech;
    @FXML
    TextField tfNom;
    @FXML
    TextField tfPrenom;
    @FXML
    TextField tfAdresse;
    @FXML
    TextField tfAN;
    @FXML
    TextField tfLN;
    @FXML
    TextField tfAge;
    @FXML
    TextField tfFonction;
    @FXML
    TextField tfOrganisme;
    @FXML
    TextField tfSalaire;
    /************************* Button *********************/
    @FXML
    Button btn_rech;


    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btn_rech){
            displayEmployeeData();
        }
    }

    public ObservableList<Employee> findEmployee(){
        ObservableList<Employee> employeelist = FXCollections.observableArrayList();

        String query = "SELECT * FROM Employee WHERE id ="+tfRech.getText()+"";

        Connection connection = EmployeeController.getConnection();
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Employee employees;
            while (rs.next()){
                employees = new Employee(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getDate("an"), rs.getString("ln"),rs.getInt("age"),rs.getString("fonction"),rs.getString("organisme"),rs.getInt("numSecu"),rs.getInt("numCompte"), rs.getDouble("salaire"));
                employeelist.add(employees);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return employeelist;
    }

    public void displayEmployeeData(){
        ObservableList<Employee> employeeData = findEmployee();
        for (Employee employee : employeeData){
            tfNom.setText(employee.getNom());
            tfPrenom.setText(employee.getPrenom());
            tfAdresse.setText(employee.getAdresse());
            tfAN.setText(String.valueOf(employee.getAn()));
            tfLN.setText(employee.getLn());
            tfAge.setText(String.valueOf(employee.getAge()));
            tfFonction.setText(employee.getFonction());
            tfOrganisme.setText(employee.getOrganisme());
            tfSalaire.setText(String.valueOf(employee.getSalaire()));
        }
    }


}
