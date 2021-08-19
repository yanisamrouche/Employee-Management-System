package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


public class SalarySlip {

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
    @FXML
    Button btn_generate;
    @FXML
    Button btn_rech;

    public void handleButtonAction(ActionEvent event) throws FileNotFoundException {
        if(event.getSource() == btn_rech){
            displayEmployeeData();
        }

    }
    public ObservableList<Employee> findEmployee(){
        ObservableList<Employee> employeelist = FXCollections.observableArrayList();
        String entry = tfRech.getText();
        String[] fullName = entry.split(" ");
        String query ="";
        if(fullName.length >=2) {
            query = "SELECT * FROM Employee WHERE  (nom = '" + fullName[0] + "' AND prenom='" + fullName[1] + "')";
        }else if(fullName.length == 1){
            query = "SELECT * FROM Employee WHERE id = " + tfRech.getText() +"";
        }
        Connection connection = EmployeeController.getConnection();
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Employee employees;
            while (rs.next()){
                employees = new Employee(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"),
                        rs.getDate("an"), rs.getString("ln"),rs.getInt("age"),rs.getString("fonction"),
                        rs.getString("organisme"),rs.getInt("numSecu"),rs.getInt("numCompte"), rs.getDouble("salaire"));
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
