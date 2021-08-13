package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.Connection;
public class EmployeeController implements Initializable {

    private Label label;
    /********************* TextFields *****************/
    @FXML
    private TextField tfId;
    @FXML
    private TextField tfNom;
    @FXML
    private TextField tfPrenom;
    @FXML
    private TextField tfAdresse;
    @FXML
    private TextField tfAN;
    @FXML
    private TextField tfLN;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfFonction;
    @FXML
    private TextField tfSalaire;

    /********************* TableView *****************/
    @FXML
    TableView<Employee> tvEmp ;
    /********************* TableColumn *****************/
    @FXML
    TableColumn<Employee,Integer> col_id ;
    @FXML
    TableColumn<Employee,String> colNom ;
    @FXML
    TableColumn<Employee,String> colPrenom ;
    @FXML
    TableColumn<Employee,String> colAdresse ;
    @FXML
    TableColumn<Employee, Date> colAN ;
    @FXML
    TableColumn<Employee,String> colLN ;
    @FXML
    TableColumn<Employee,Integer> colAge ;
    @FXML
    TableColumn<Employee,String> colFonction ;
    @FXML
    TableColumn<Employee, Double> colSalaire ;
    /********************* Buttons *****************/
    @FXML
    private Button btnAjt;
    @FXML
    private Button btnModif;
    @FXML
    private Button btnSupp;

    /********************* Methods *****************/

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("You Clicked me!");
        label.setText("ClickClick");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployees();
    }
    /***** Database instance *****/
    EmployeeDatabase employeeDatabase = new EmployeeDatabase();

    /** display employees **/
    public void showEmployees(){
        ObservableList<Employee> list = employeeDatabase.getEmployeesList();
        col_id.setCellValueFactory
                (new PropertyValueFactory<Employee, Integer>("id"));
        colNom.setCellValueFactory
                (new PropertyValueFactory<Employee, String>("nom"));
        colPrenom.setCellValueFactory
                (new PropertyValueFactory<Employee, String>("prenom"));
        colAdresse.setCellValueFactory
                (new PropertyValueFactory<Employee, String>("adresse"));
        colAN.setCellValueFactory
                (new PropertyValueFactory<Employee, Date>("an"));
        colLN.setCellValueFactory
                (new PropertyValueFactory<Employee, String>("ln"));
        colAge.setCellValueFactory
                (new PropertyValueFactory<Employee, Integer>("age"));
        colFonction.setCellValueFactory
                (new PropertyValueFactory<Employee, String>("fonction"));
        colSalaire.setCellValueFactory
                (new PropertyValueFactory<Employee, Double>("salaire"));
        tvEmp.setItems(list);
    }





}