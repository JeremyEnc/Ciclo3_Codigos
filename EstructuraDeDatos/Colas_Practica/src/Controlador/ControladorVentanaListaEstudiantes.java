/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaListaEstudiantes implements Initializable {

    @FXML
    private TextField txtfNombre;
    @FXML
    private TextField txtfEdad;
    @FXML
    private TableView<Estudiante> tvListaEstudiantes;
    @FXML
    private TableColumn<Estudiante, String> tcNombre;
    @FXML
    private TableColumn<Estudiante, Integer> tcEdad;

    ControladorEstudiante ctrlEstudiante;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        ctrlEstudiante = new ControladorEstudiante();
        ctrlEstudiante.setColaEstudiantes(ctrlEstudiante.listarEstudiantes());
        tcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        listarTabla(1);
    }    

    @FXML
    private void agregarEstudiante(ActionEvent event)
    {
        String nombre, edad;
        
        nombre = txtfNombre.getText();
        edad = txtfEdad.getText();
        
        ctrlEstudiante.añadirEstudiante(nombre, Integer.valueOf(edad));
        ctrlEstudiante.guardar();
        listarTabla(1);
    }

    @FXML
    private void invertirEstudiantes(ActionEvent event)
    {
        ctrlEstudiante.setColaEstudiantes(ctrlEstudiante.invertirSinEstructRept());
        ctrlEstudiante.guardar();

        listarTabla(2);
    }
    
    
    private void listarTabla(int opcion)
    { 
        tvListaEstudiantes.getItems().clear();
        ctrlEstudiante.setColaEstudiantes(ctrlEstudiante.listarEstudiantes());
        ctrlEstudiante.setColaEstudiantes(ctrlEstudiante.invertirSinEstructRept());
        ctrlEstudiante.setColaInvertida(ctrlEstudiante.invertirSinEstructRept());
        ctrlEstudiante.setColaEstudiantes(ctrlEstudiante.listarEstudiantes());

        if(opcion == 1)
        {
            for (int i = 0; i < ctrlEstudiante.getColaEstudiantes().length(); i++) {
                tvListaEstudiantes.getItems().add(ctrlEstudiante.getColaInvertida().dequeue());
            }
        }
        else
        {
            for (int i = 0; i < ctrlEstudiante.getColaEstudiantes().length(); i++) {
                tvListaEstudiantes.getItems().add(0,ctrlEstudiante.getColaInvertida().dequeue());
            }
        }
        
        
        tvListaEstudiantes.refresh();
        
        
    }
    
}
