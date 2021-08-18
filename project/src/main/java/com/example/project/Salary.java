package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
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
    @FXML
    TextField tfPourcentage;
    @FXML
    TextField tfMontant;
    /************************* Button *********************/
    @FXML
    Button btn_rech;
    @FXML
    Button btn_calcul;
    @FXML
    Button btn_eng;
    @FXML
    Button btn_v;
    @FXML
    RadioButton rbtn_pourcentage;
    @FXML
    RadioButton rbtn_montant;
    @FXML
    Label label_salaire;


    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btn_rech){
            displayEmployeeData();
        }
        else if(event.getSource() == btn_calcul){
            if(tfPourcentage.isVisible() && rbtn_pourcentage.isSelected())
                editSalaryByPourcentage();
            else if(tfMontant.isVisible() && rbtn_montant.isSelected())
                editSalaryByAmount();
        }
        else if(event.getSource() == btn_v){
            clearData();
        }
        else if(event.getSource()==btn_eng){
            updateSalary();
        }
    }

    public void handleRadioButtonActionPoucentage(ActionEvent event){

          if(event.getSource() == rbtn_pourcentage){

              tfMontant.setVisible(false);
              tfPourcentage.setVisible(true);
              if(rbtn_montant.isSelected()){
                  rbtn_montant.setSelected(false);
              }
          }

    }

    public void handleRadioButtonActionAmount(ActionEvent event){

        if (event.getSource() == rbtn_montant){
            tfPourcentage.setVisible(false);
            tfMontant.setVisible(true);
            if(rbtn_pourcentage.isSelected()){
                rbtn_pourcentage.setSelected(false);
            }
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

    public void editSalaryByPourcentage(){
        double salary = Double.parseDouble(tfSalaire.getText());
        int pourcentage = Integer.parseInt(tfPourcentage.getText());
        double deduction = (salary*pourcentage)/100;
        double finalSalary = salary - deduction;
        label_salaire.setText(String.valueOf(finalSalary));
    }
    public void editSalaryByAmount(){
        double salary = Double.parseDouble(tfSalaire.getText());
        double amount = Double.parseDouble(tfMontant.getText());
        double finalSalary = salary + amount;
        label_salaire.setText(String.valueOf(finalSalary));
    }


    public void clearData(){
        tfNom.setText("");
        tfPrenom.setText("");
        tfAdresse.setText("");
        tfAN.setText("");
        tfLN.setText("");
        tfAge.setText("");
        tfFonction.setText("");
        tfOrganisme.setText("");
        tfSalaire.setText("");
        tfRech.setText("");
        tfPourcentage.setText("");
        tfMontant.setText("");
        label_salaire.setText("0");
        rbtn_pourcentage.setSelected(false);
        rbtn_montant.setSelected(false);
    }

    public void updateSalary(){
        String query = "UPDATE Employee SET "+
                "salaire = '"+label_salaire.getText()+"'";
        EmployeeController.executeQuery(query);
    }


}
