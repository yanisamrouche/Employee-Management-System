package com.example.project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main {


    @FXML
    Button btn_employee;
    @FXML
    Button btn_alloc;
    @FXML
    Button btn_bulletin;

    @FXML
    public void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You Clicked me!!!");
        if (event.getSource() == btn_employee){
            //TODO
            Scene scene2 = new Scene(FXMLLoader.load(EmployeeApplication.class.getResource("employee.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene2);
            stage.show();
        }else if(event.getSource() == btn_alloc){
            //TODO
            Scene scene2 = new Scene(FXMLLoader.load(EmployeeApplication.class.getResource("salary.fxml")));
            Stage stage = new Stage();
            stage.setScene(scene2);
            stage.show();

        }else if(event.getSource() == btn_bulletin){
            //TODO

        }

    }







}
