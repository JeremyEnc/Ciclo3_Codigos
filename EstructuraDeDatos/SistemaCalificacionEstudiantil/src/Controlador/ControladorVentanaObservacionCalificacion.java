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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaObservacionCalificacion implements Initializable {

    @FXML
    private Label lblObservacion;
    @FXML
    private TextArea txtAObservacion;

    ControladorEstudiante ctrlEstudiante;
    
    int posEstudiante;
    int posMateria;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ctrlEstudiante = new ControladorEstudiante();
    }    

    @FXML
    private void guardarObservacion(ActionEvent event) 
    {
        ctrlEstudiante.getListaEstudiantes().getByIndex(posEstudiante).getListaMaterias().getByIndex(posMateria).setObservacion(txtAObservacion.getText());
        cancelarEdicion(event);
    }

    @FXML
    private void cancelarEdicion(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    private void cargarObservacion()
    {
        txtAObservacion.setText(ctrlEstudiante.getListaEstudiantes().getByIndex(posEstudiante).getListaMaterias().getByIndex(posMateria).getObservacion());
    }
    
    public void asignarControlador(ControladorEstudiante ctrlEstudiante, int posEstudiante, int posMateria)
    {
        this.ctrlEstudiante = ctrlEstudiante;
        this.posEstudiante = posEstudiante;
        this.posMateria = posMateria;
        cargarObservacion();
    }
    
}
