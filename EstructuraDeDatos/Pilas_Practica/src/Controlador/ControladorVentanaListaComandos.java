/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaListaComandos implements Initializable {

    @FXML
    private TextField txtfComando;
    @FXML
    private TextField txtfUltimoComando;
    
    ControladorComando ctrlComando;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ctrlComando = new ControladorComando(15);
        ctrlComando.setPilaComandos(ctrlComando.listarComandos());
        txtfUltimoComando.setEditable(false);
        actualizarUltimoComandoMostrado();
    }    

    @FXML
    private void agregarComando(ActionEvent event)
    {        
        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        if(!ctrlComando.getPilaComandos().isFull())
        {
            ctrlComando.agregarComando(txtfComando.getText(),fecha );
        }
        else
        {
            System.out.println("isfull");
            ctrlComando.borrarPrimerComando();
            ctrlComando.agregarComando(txtfComando.getText(),fecha);
        }
        
        actualizarUltimoComandoMostrado();
        ctrlComando.guardar();
    }
    
    
    private void actualizarUltimoComandoMostrado()
    {
        String fecha = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(Calendar.getInstance().getTime());
        
        txtfUltimoComando.setText(ctrlComando.obtenerUltimoComando().getComando() + "            " + fecha);

    }

    @FXML
    private void mostrarVentanaHistorial(ActionEvent event)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaHistorial.fxml"));
            Parent root2 = loader.load();
            Stage dialog = new Stage();      
            
            ControladorVentanaHistorial ctrlVentanaHistorial = (ControladorVentanaHistorial)loader.getController();
            ctrlVentanaHistorial.asignarControlador(ctrlComando);
            dialog.initStyle(StageStyle.UNDECORATED);
            Scene dialogScene = new Scene(root2, Color.TRANSPARENT);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
  
        } catch (IOException ex) {
            
        } 
    }
    
}
