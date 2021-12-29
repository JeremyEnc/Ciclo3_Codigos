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
public class Main extends Application
{
    public static void main(String[] args) 
    {
        launch(args);
//        
//        int[] xd= new int[]{1,2,3,4,5};
//        
//        Ordenamiento ord = new Ordenamiento();
//        
//        ord.ordenarListaPorInsercion(xd);
//        ord.mostrarArreglo(xd);
    }

    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("/Vista/VentanaListaPersonas.fxml"));
        Scene sceneLogin = new Scene(root);
        stage.setTitle("Inicio");
        stage.setScene(sceneLogin);
        stage.show();      
    }
}
