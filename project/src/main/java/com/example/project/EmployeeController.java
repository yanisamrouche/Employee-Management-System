package com.example.project;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.Date;
import java.util.ResourceBundle;

public class EmployeeController implements Initializable {

    private Label label;
    /********************* TextFields *****************/
    @FXML
    TextField tfId;
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
    TextField tfSalaire;

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
    Button btnAjt;
    @FXML
    Button btnModif;
    @FXML
    Button btnSupp;

    /********************* Methods *****************/

    @FXML
    private void handleButtonAction(ActionEvent event){
        System.out.println("You Clicked me!");
        if(event.getSource() == btnAjt){
            insertEmployee();
        }
        else if (event.getSource() == btnModif){
            updateEmployee();
        }
        else if(event.getSource() == btnSupp){
            deleteEmployee();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showEmployees();
    }

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

    /** display employees **/
    public void showEmployees(){
        ObservableList<Employee> list = getEmployeesList();
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

    public void executeQuery(String query){
        Connection connection = getConnection();
        Statement statement;
        try{
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertEmployee(){
        String query = "INSERT INTO Employee VALUES " +
                "("+  tfId.getText() +
                ",'" + tfNom.getText() +
                "','" + tfPrenom.getText() +
                "','" + tfAdresse.getText() +
                "','" +tfAN.getText() +
                "','" + tfLN.getText() +
                "'," + tfAge.getText() +
                ",'" + tfFonction.getText() +
                "','" + tfSalaire.getText() +"')";
        executeQuery(query);
        showEmployees();
    }

    public void updateEmployee(){
        String query = "UPDATE Employee SET " +
                "nom = '"+ tfNom.getText() + "'" +
                ", prenom = '" + tfPrenom.getText() + "'" +
                ", adresse = '" + tfAdresse.getText() + "'" +
                ", an = '" + tfAN.getText() + "'" +
                ", ln = '" + tfLN.getText() + "'" +
                ", age = '" + tfAge.getText() + "'" +
                ", fonction = '" + tfFonction.getText() + "'" +
                ", salaire = '" + tfSalaire.getText() +"'" +
                "WHERE id = "+ tfId.getText() + "";
        executeQuery(query);
        showEmployees();
    }

    public void deleteEmployee(){
        String query = "DELETE FROM Employee" +
                " WHERE id = "+tfId.getText()+"";
        executeQuery(query);
        showEmployees();
        tfId.setText("");
        tfNom.setText("");
        tfPrenom.setText("");
        tfAdresse.setText("");
        tfAN.setText("");
        tfLN.setText("");
        tfAge.setText("");
        tfFonction.setText("");
        tfSalaire.setText("");
    }

    public void displayFromTableView(){
        TableView.TableViewSelectionModel selectionModel = tvEmp.getSelectionModel();
        ObservableList<Employee> selectedItems = selectionModel.getSelectedItems();
        for(Employee employee : selectedItems){
            tfId.setText(String.valueOf(employee.getId()));
            tfNom.setText(employee.getNom());
            tfPrenom.setText(employee.getPrenom());
            tfAdresse.setText(employee.getAdresse());
            tfAN.setText(String.valueOf(employee.getAn()));
            tfLN.setText(employee.getLn());
            tfAge.setText(String.valueOf(employee.getAge()));
            tfFonction.setText(employee.getFonction());
            tfSalaire.setText(String.valueOf(employee.getSalaire()));
        }
    }







}