/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jere_
 */
public class ControladorPrincipal extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/VentanaListaEstudiantes.fxml"));
        Scene sceneLogin = new Scene(root);
        stage.setTitle("Inicio");
        stage.setScene(sceneLogin);
        stage.show();      
    }
//    ControladorEstudiante ctrlEstudiante;
//    
//    public static void main(String[] args)
//    {
//        ControladorEstudiante ctrlEstudiante = new ControladorEstudiante();
//        
//        ctrlEstudiante.añadirEstudiante("Juan", 12);
//        ctrlEstudiante.añadirEstudiante("John", 13);
//        ctrlEstudiante.añadirEstudiante("Pablo", 14);
//        ctrlEstudiante.añadirEstudiante("Jessica", 15);
//        ctrlEstudiante.añadirEstudiante("Maria", 16);
//        
//        ctrlEstudiante.añadirEstudiante("Alex", 12);
//        ctrlEstudiante.añadirEstudiante("Steven", 13);
//        ctrlEstudiante.añadirEstudiante("Jose", 14);
//        ctrlEstudiante.añadirEstudiante("Alejandra", 15);
//        ctrlEstudiante.añadirEstudiante("Emily", 16);
//        
//        ctrlEstudiante.añadirEstudiante("Jeremy", 12);
//        ctrlEstudiante.añadirEstudiante("Gustavo", 13);
//        ctrlEstudiante.añadirEstudiante("Anthony", 14);
//        ctrlEstudiante.añadirEstudiante("Kevin", 15);
//        ctrlEstudiante.añadirEstudiante("Manuel", 16);
//        
//        ctrlEstudiante.añadirEstudiante("Pedro", 12);
//        ctrlEstudiante.añadirEstudiante("Brayan", 13);
//        ctrlEstudiante.añadirEstudiante("Mirna", 14);
//        ctrlEstudiante.añadirEstudiante("Bridgitte", 15);
//        ctrlEstudiante.añadirEstudiante("Itzell", 16);
//        
//        ctrlEstudiante.añadirEstudiante("Ariana", 12);
//        ctrlEstudiante.añadirEstudiante("Ever", 13);
//        ctrlEstudiante.añadirEstudiante("Michael", 14);
//        ctrlEstudiante.añadirEstudiante("Mike", 15);
//        ctrlEstudiante.añadirEstudiante("Roberth", 16);
//
//        ctrlEstudiante.invertirSinEstructRept();
//        
//    }
    
}
