/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Comando;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaHistorial implements Initializable {

    @FXML
    private TableColumn<Comando, String> tcComando;
    @FXML
    private TableColumn<Comando, String> tcFecha;
    @FXML
    private TableView<Comando> tvListaComandos;

    ControladorComando ctrlComando;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tcComando.setCellValueFactory(new PropertyValueFactory<>("comando"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fechaEjecucion"));
    }    

    @FXML
    private void cerrarHistorial(ActionEvent event)
    {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public void asignarControlador(ControladorComando ctrlComando)
    {
        this.ctrlComando = ctrlComando;
        cargarHistorial();
    }
    
    private void cargarHistorial()
    {
        ctrlComando.setPilaComandosInvertida(ctrlComando.obtenerPilaInvertida());
        
        for (int i = 0; i < ctrlComando.getPilaComandos().length(); i++)
        {
            tvListaComandos.getItems().add(0,ctrlComando.getPilaComandosInvertida().pop());
            tvListaComandos.refresh();
        }
    }
}
