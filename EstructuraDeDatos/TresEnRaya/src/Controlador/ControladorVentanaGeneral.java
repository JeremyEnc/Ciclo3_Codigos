/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import animatefx.animation.ZoomIn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaGeneral implements Initializable {

    @FXML
    private MenuItem miTresEnRaya;
    @FXML
    private MenuItem miMenuMatriz;
    @FXML
    private SubScene sbEscenaCambiante;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        // TODO
    }    

    @FXML
    private void cargarVentanaTresEnRaya(ActionEvent event) 
    {
        try 
        {
            Parent rootVueloNacional = FXMLLoader.load(getClass().getResource("/vista/VentanaTresEnRaya.fxml"));  
            sbEscenaCambiante.setRoot(rootVueloNacional); 
            new ZoomIn(rootVueloNacional).setSpeed(4).play();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ControladorVentanaGeneral.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @FXML
    private void cargarVentananMenuMatriz(ActionEvent event) 
    {
        try 
        {
            Parent rootVueloNacional = FXMLLoader.load(getClass().getResource("/vista/VentanaMenuMatriz.fxml"));  
            sbEscenaCambiante.setRoot(rootVueloNacional); 
            new ZoomIn(rootVueloNacional).setSpeed(4).play();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(ControladorVentanaGeneral.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
