/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import C.Lista;
import Modelo.Materia;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaListaEstudiantes implements Initializable {

    @FXML
    private ComboBox<String> cbxEstudiantes;
    @FXML
    private TableView<Lista<Materia>> tvListaMaterias;

    ControladorEstudiante ctrlEstudiante;
    @FXML
    private HBox hbMaterias;
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {   
        hbMaterias.setDisable(true);
        
        ctrlEstudiante = new ControladorEstudiante();
        ctrlEstudiante.setListaEstudiantes(ctrlEstudiante.listar());
        
        cargarComboBoxEstudiantes();
        
        cbxEstudiantes.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            if(cbxEstudiantes.getValue() == null)
            {
                hbMaterias.setDisable(true);
                tvListaMaterias.getColumns().clear();
                tvListaMaterias.getItems().clear();
            }else
            {
                hbMaterias.setDisable(false);
                generarColumnasNotas();
            }
            
            
        });
    }    

    @FXML
    private void cargarVentanaAgregarEstudiante(ActionEvent event)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaAgregarEstudiante.fxml"));
            Parent root2 = loader.load();
            Stage dialog = new Stage();      
            
            ControladorVentanaAgregarEstudiante ctrlAgregarEstudiante = (ControladorVentanaAgregarEstudiante)loader.getController();
            ctrlAgregarEstudiante.asignarControlador(ctrlEstudiante);
            dialog.initStyle(StageStyle.UNDECORATED);
            Scene dialogScene = new Scene(root2, Color.TRANSPARENT);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
      
            ctrlEstudiante.getListaEstudiantes().print();
            cargarComboBoxEstudiantes();
            ctrlEstudiante.guardar();

            
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentanaListaEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    @FXML
    private void cargarVentanaAgregarMateria(ActionEvent event)
    {
        int posicionEstudiante = cbxEstudiantes.getSelectionModel().getSelectedIndex();
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaAgregarMateria.fxml"));
            Parent root2 = loader.load();
            Stage dialog = new Stage();      
            dialog.initStyle(StageStyle.UNDECORATED);
            ControladorVentanaAgregarMateria ctrlVentanaAgregarMateria = (ControladorVentanaAgregarMateria)loader.getController();
            ctrlVentanaAgregarMateria.asignarControlador(ctrlEstudiante, posicionEstudiante );
            
            Scene dialogScene = new Scene(root2, Color.TRANSPARENT);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
            
            generarColumnasNotas();
            ctrlEstudiante.guardar();
         
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentanaListaEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    private void cargarComboBoxEstudiantes()
    {
        cbxEstudiantes.getItems().clear();
        
        for (int i = 0; i < ctrlEstudiante.getListaEstudiantes().length(); i++)
        {
            cbxEstudiantes.getItems().add(ctrlEstudiante.getListaEstudiantes().getByIndex(i).getNombre());
        }
    }
    
    private void generarColumnasNotas()
    {
        int posicionEstudiante = cbxEstudiantes.getSelectionModel().getSelectedIndex();
        tvListaMaterias.getColumns().clear();
        tvListaMaterias.getItems().clear();

        if(ctrlEstudiante.getListaEstudiantes().getByIndex(posicionEstudiante).getListaMaterias() == null) return;

        for (int i = 0; i < ctrlEstudiante.getListaEstudiantes().getByIndex(posicionEstudiante).getListaMaterias().length(); i++)
        {
            final int pos = i;
            TableColumn<Lista<Materia>, Double> tc = new TableColumn(ctrlEstudiante.getListaEstudiantes().getByIndex(posicionEstudiante)
                                                                            .getListaMaterias().getByIndex(i).getNombre());
            
            tc.setCellValueFactory((c -> new SimpleObjectProperty<>(c.getValue().getByIndex(pos).getCalificacion())));
            tc.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
            
            tvListaMaterias.getColumns().add(tc);
            
            tc.setOnEditCommit(data ->
            {
                data.getRowValue().getByIndex(pos).setCalificacion(data.getNewValue());
                ctrlEstudiante.guardar();
            });
        }
        
        tvListaMaterias.getItems().add(ctrlEstudiante.getListaEstudiantes().getByIndex(posicionEstudiante).getListaMaterias());
    }

    @FXML
    private void mostrarVentanaObservacion(ActionEvent event)
    {
        tvListaMaterias.getSelectionModel().setCellSelectionEnabled(true);
        
        int posicionEstudiante = cbxEstudiantes.getSelectionModel().getSelectedIndex();
        int posicionMateria = tvListaMaterias.getFocusModel().focusedCellProperty().getValue().getColumn();
        
        if(posicionMateria == -1)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Info");
            alert.setContentText("Debe seleccionar una nota");
            alert.showAndWait();
            return;
        }
        
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaObservacionCalificacion.fxml"));
            Parent root2 = loader.load();
            Stage dialog = new Stage();      
            
            ControladorVentanaObservacionCalificacion ctrlVentanaObservacionCalificacion = (ControladorVentanaObservacionCalificacion)loader.getController();
            ctrlVentanaObservacionCalificacion.asignarControlador(ctrlEstudiante, posicionEstudiante, posicionMateria);
            
            Scene dialogScene = new Scene(root2, Color.TRANSPARENT);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
            
            ctrlEstudiante.guardar();

         
        } catch (IOException ex) {
            Logger.getLogger(ControladorVentanaListaEstudiantes.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
}
