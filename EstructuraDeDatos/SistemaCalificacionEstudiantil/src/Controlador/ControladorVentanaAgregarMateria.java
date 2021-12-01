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
public class ControladorVentanaAgregarMateria implements Initializable {

    @FXML
    private TextField txtfNombre;
    @FXML
    private TextField txtfCalificacion;
    
    ControladorEstudiante ctrlEstudiante;
    int posicion;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       
    }    

    @FXML
    private void cancelarAgregacion(ActionEvent event) 
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
        
    }

    @FXML
    private void agregarMateria(ActionEvent event)
    {
        String nombre;
        double nota;
        
        if(!txtfCalificacion.getText().isBlank() && !txtfNombre.getText().isBlank())
        {
            nombre = txtfNombre.getText();
            nota = Double.valueOf(txtfCalificacion.getText());
            ctrlEstudiante.insertarNota(posicion, nombre, nota);
            cancelarAgregacion(event);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Debe llenar todos los datos");
            alert.showAndWait();
        }
        
    }
    
    public void asignarControlador(ControladorEstudiante ctrlEstudiante, int pos)
    {
        this.ctrlEstudiante = ctrlEstudiante;
        this.posicion = pos;
    }
    
}
