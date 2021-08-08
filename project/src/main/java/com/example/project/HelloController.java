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
public class HelloController implements Initializable {

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
    private TableView<Employee> tvEmp;
    /********************* TableColumn *****************/
    @FXML
    private TableColumn<Employee,Integer> colId;
    @FXML
    private TableColumn<Employee,String> colNom;
    @FXML
    private TableColumn<Employee,String> colPrenom;
    @FXML
    private TableColumn<Employee,String> colAdresse;
    @FXML
    private TableColumn<Employee, Date> colAN;
    @FXML
    private TableColumn<Employee,String> colLN;
    @FXML
    private TableColumn<Employee,Integer> colAge;
    @FXML
    private TableColumn<Employee,String> colFonction;
    @FXML
    private TableColumn<Employee, Double> colSalaire;
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

    /** Database **/
    public Connection getConnection(){
        Connection connection;
        try{
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/empproject","root","yanis98");
            return connection;
        }catch (Exception e){
            System.out.println("Error : "+e.getMessage());
            return null;}
    }
    public ObservableList<Employee> getEmployeesList(){
        ObservableList<Employee> employeelist = FXCollections.observableArrayList();
        Connection connection = getConnection();
        String query = "SELECT * FROM employee";
        Statement st;
        ResultSet rs;
        try{
            st = connection.createStatement();
            rs = st.executeQuery(query);
            Employee employees;
            while (rs.next()){
                employees = new Employee(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("adresse"), rs.getDate("dateDeNaissance"), rs.getString("LieuDeNaissance"),rs.getInt("age"),rs.getString("fonction"), rs.getDouble("salaire"));
                employeelist.add(employees);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return employeelist;
    }
    public void showEmployees(){
        ObservableList<Employee> employeelist = getEmployeesList();
        colId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        colNom.setCellValueFactory(new PropertyValueFactory<Employee, String>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<Employee, String>("prenom"));
        colAdresse.setCellValueFactory(new PropertyValueFactory<Employee, String>("adresse"));
        colAN.setCellValueFactory(new PropertyValueFactory<Employee, Date>("dateDeNaissance"));
        colLN.setCellValueFactory(new PropertyValueFactory<Employee, String>("lieuDeNaissance"));
        colAge.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("age"));
        colFonction.setCellValueFactory(new PropertyValueFactory<Employee, String>("fonction"));
        colSalaire.setCellValueFactory(new PropertyValueFactory<Employee, Double>("salaire"));
        tvEmp.setItems(employeelist);

    }
}