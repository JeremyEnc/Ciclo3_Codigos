/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaAgregarEstudiante implements Initializable {

    @FXML
    private TextField txtfNombre;
    @FXML
    private TextField txtfEdad;
    @FXML
    private TextField txtfSexo;
    @FXML
    private TextField txtfCiclo;

    ControladorEstudiante ctrlEstudiante;
    
    /**
     * Initializes the controller class.

     */
  

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        
    }    


    @FXML
    private void agregarEstudiante(ActionEvent event)
    {
        String nombre, sexo;
        int edad,ciclo;
        
        if(!txtfCiclo.getText().isBlank() && !txtfSexo.getText().isBlank() && !txtfNombre.getText().isBlank() && !txtfEdad.getText().isBlank())
        {
            nombre = txtfNombre.getText();
            edad = Integer.valueOf(txtfEdad.getText());
            sexo = txtfSexo.getText();
            ciclo = Integer.valueOf(txtfCiclo.getText());
            
            ctrlEstudiante.insertarEstudiante(nombre,edad,sexo,ciclo);
            cancelarAgregacion(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Debe llenar todos los campos");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancelarAgregacion(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }
    
    public void asignarControlador(ControladorEstudiante ctrlEstudiante)
    {
        this.ctrlEstudiante = ctrlEstudiante;
    }
    
}
