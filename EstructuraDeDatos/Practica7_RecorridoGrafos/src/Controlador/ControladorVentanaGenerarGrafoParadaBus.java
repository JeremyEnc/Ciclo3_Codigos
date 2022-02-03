/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.EstructurasDinamicas.Lista;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaGenerarGrafoParadaBus implements Initializable {

    @FXML
    private TextField txtfNombre;
    @FXML
    private TextField txtfCalle1;
    @FXML
    private TextField txtfCalle2;
    @FXML
    private TextField txtfReferencia;
    @FXML
    private TextField txtfRuta;
    @FXML
    private TableView<String[]> tvGrafo;
    @FXML
    private ComboBox<String> cbxVerticeInicial;
    @FXML
    private ComboBox<String> cbxVerticeFinal;
    @FXML
    private TextArea txtArecorrido;
    @FXML
    private ComboBox<String> cbxRecorrido;
    @FXML
    private AnchorPane apTabla;
    @FXML
    private AnchorPane apRuta;
    @FXML
    private ComboBox<String> cbxVerticeRecorrido;
    
    ControladorParadaBus ctrlParadaBus;
    ObservableList<String[]> aristas;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ctrlParadaBus = new ControladorParadaBus();
        cbxRecorrido.getItems().add("Profundidad");
        cbxRecorrido.getItems().add("Anchura");
        
        apTabla.setDisable(true);
        apRuta.setDisable(true);
    }    

    @FXML
    private void agregarVertice(ActionEvent event) 
    {
        ctrlParadaBus.generarParadaBus(txtfNombre.getText(), txtfCalle1.getText(), 
                                       txtfCalle2.getText(), txtfReferencia.getText(), 
                                       Integer.valueOf(txtfRuta.getText()));
        
        if((ctrlParadaBus.getGrafo().numVertices() >= 2))
        {
            apTabla.setDisable(false);
            apRuta.setDisable(false);
            cargarCbxVertices();
            cargarTabla();
        }
        limpiarDatos(event);
    }

    @FXML
    private void limpiarDatos(ActionEvent event) 
    {
        txtfNombre.clear();
        txtfCalle1.clear(); 
        txtfCalle2.clear();
        txtfReferencia.clear();
        txtfRuta.clear();
    }

    @FXML
    private void agregarArista(ActionEvent event)
    {
        Integer vtI = ctrlParadaBus.getGrafo().obtenerCodigo(cbxVerticeInicial.getSelectionModel().getSelectedItem());
        Integer vtF = ctrlParadaBus.getGrafo().obtenerCodigo(cbxVerticeFinal.getSelectionModel().getSelectedItem());
        
        if(vtI == vtF)
        {
            System.out.println("No se puede agregar con el mismo vertice");
        }else
        {
            ctrlParadaBus.getGrafo().insertarArista(vtI, vtF);
            aristas.get(vtI)[vtF+1] = "OK";
            tvGrafo.refresh();
        }
    }

    @FXML
    private void generarRecorrido(ActionEvent event) 
    {
        Lista<Integer> recorrido = new Lista();
        Integer index = cbxVerticeRecorrido.getSelectionModel().getSelectedIndex();
        
        switch(cbxRecorrido.getSelectionModel().getSelectedIndex())
        {
            case 0:
                if(index != -1)
                {
                    recorrido = ctrlParadaBus.getGrafo().busquedaProfundidad(index);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debe seleccionar un vertice inicial");
                    alert.showAndWait();
                }
                break;
            case 1:
                
                if(index != -1)
                {
                    recorrido = ctrlParadaBus.getGrafo().busquedaAnchura(index);
                }
                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("Debe seleccionar un vertice inicial");
                    alert.showAndWait();
                }
                break;
            default:
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe seleccionar un tipo de recorrido");
                alert.showAndWait();
                break;
        }
        
        String camino = "";
        
        for (int i = 0; i < recorrido.length(); i++)
        {
            if((recorrido.length() - i) != 1) camino += recorrido.getByIndex(i) + "--";
            else camino += recorrido.getByIndex(i);
           
        }
        
        txtArecorrido.setText(camino);
    }

    @FXML
    private void reiniciarVentana(ActionEvent event)
    {
        limpiarDatos(event);
        cbxVerticeFinal.getItems().clear();
        cbxVerticeInicial.getItems().clear();
        cbxVerticeRecorrido.getItems().clear();
        tvGrafo.getItems().clear();
        tvGrafo.getColumns().clear();
        txtArecorrido.clear();
        
        ctrlParadaBus = new ControladorParadaBus();
        
        apTabla.setDisable(true);
        apRuta.setDisable(true);
        
    }

    @FXML
    private void mostrarVentanaGrafo(ActionEvent event)
    {
        try 
        {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Vista/VentanaGrafo.fxml"));
            Parent root2 = loader.load();
            Stage dialog = new Stage();      
            
            ControladorVentanaGrafo ctrlVentGrafo = (ControladorVentanaGrafo)loader.getController();
            ctrlVentGrafo.asignarControlador(ctrlParadaBus, txtArecorrido.getText());
            Scene dialogScene = new Scene(root2);
            dialog.setScene(dialogScene);
            dialog.showAndWait();
            
        } catch (IOException ex) {
            
        } 
    }
    
    private void cargarTabla()
    {
        tvGrafo.getItems().clear();
        tvGrafo.getColumns().clear();
        Double tamanoTabla;
        
        Integer n = ctrlParadaBus.getGrafo().numVertices() + 1;
        
        TableColumn<String[], String> columsMatriz[] = new TableColumn[n];

        try
        {
            for (int i = 0; i < n; i++)
            {
                int j = i;
                columsMatriz[i] = new TableColumn<>("--");
                columsMatriz[i].setCellValueFactory((c -> new SimpleObjectProperty<>(c.getValue()[j])));
                columsMatriz[i].setPrefWidth(tvGrafo.getPrefWidth() / n);
                tvGrafo.getColumns().add(columsMatriz[i]);
            }
        } 
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("xd");
        }
        finally
        {
            tvGrafo.setPrefHeight(30*(n));

            tamanoTabla = 280 - tvGrafo.getPrefHeight();
        
            if(tamanoTabla < 0.0)tamanoTabla = 0.0;
        
            tvGrafo.setMaxHeight(280 - tamanoTabla);
        
            asignarNombresColumnas();
            cargarObservableArrayList();
        }   
    }
    
    private void asignarNombresColumnas()
    {
        for (int i = 1; i < tvGrafo.getColumns().size(); i++)
        {
            if(i == tvGrafo.getColumns().size())
            {
                tvGrafo.getColumns().get(i-1).setText((String)ctrlParadaBus.getGrafo().obtenerEtiqueta(i - 1));
            }
            else
            {
                tvGrafo.getColumns().get(i).setText((String)ctrlParadaBus.getGrafo().obtenerEtiqueta(i - 1));
            }  
        }
    }
    
    private void cargarObservableArrayList()
    {
        Integer n = ctrlParadaBus.getGrafo().numVertices() + 1;
        aristas = FXCollections.observableArrayList();

        for (int i = 0; i < n - 1; i++)
        {
            String[] aux = new String[n];
            aux[0] = (String)ctrlParadaBus.getGrafo().obtenerEtiqueta(i);
            aristas.add(aux);
        }
        
        tvGrafo.setItems(aristas); 
    }
    
    private void cargarCbxVertices()
    {
        cbxVerticeInicial.getItems().clear();
        cbxVerticeFinal.getItems().clear();
        cbxVerticeRecorrido.getItems().clear();
        
        for (int i = 0; i < ctrlParadaBus.getGrafo().numVertices(); i++)
        {
           cbxVerticeInicial.getItems().add(ctrlParadaBus.getGrafo().obtenerEtiqueta(i).toString());
           cbxVerticeFinal.getItems().add(ctrlParadaBus.getGrafo().obtenerEtiqueta(i).toString());
           cbxVerticeRecorrido.getItems().add(ctrlParadaBus.getGrafo().obtenerEtiqueta(i).toString());
            
        }
    }
    
  
}
