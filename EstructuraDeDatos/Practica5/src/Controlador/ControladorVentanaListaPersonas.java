/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.EstructurasDinamicas.Lista;
import Modelo.Persona;
import java.lang.reflect.Field;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaListaPersonas implements Initializable {

    @FXML
    private TextField txtfCedula;
    @FXML
    private TextField txtfApellidos;
    @FXML
    private TextField txtfTelefono;
    @FXML
    private TextArea txtAreaDir;
    @FXML
    private TextField txtfNombres;
    @FXML
    private TextField txtfCorreo;
    @FXML
    private DatePicker dtFN;
    @FXML
    private ComboBox<String> cbxOrdenar;
    @FXML
    private TableView<Persona> tvListaPersonas;
    @FXML
    private TableColumn<Persona, Long> tcId;
    @FXML
    private TableColumn<Persona, String> tcNombres;
    @FXML
    private TableColumn<Persona, String> tcApellidos;
    @FXML
    private TableColumn<Persona, String> tcCorreos;
    @FXML
    private TableColumn<Persona, String> tcTelefono;
    @FXML
    private TableColumn<Persona, LocalDate> tcFN;
    @FXML
    private TableColumn<Persona, String> tcDir;
    @FXML
    private TableColumn<Persona, String> tcCedula;
    @FXML
    private ComboBox<String> cbxAtributo;
    @FXML
    private ComboBox<String> cbxMetodo;
    
    ControladorPersona ctrlPersona;
    
    
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       ctrlPersona = new ControladorPersona(Persona.class);
       ctrlPersona.setListaPersonas(ctrlPersona.listar());

       
       tcNombres.setCellValueFactory(new PropertyValueFactory<>("nombres"));
       tcApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
       tcDir.setCellValueFactory(new PropertyValueFactory<>("direccion"));
       tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
       tcCorreos.setCellValueFactory(new PropertyValueFactory<>("correo"));
       tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
       tcCedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
       tcFN.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
       
       cargarComboBoxDeOrdenacion();
       cargarAtributosEnCBX(cbxAtributo);
       presentarTabla(ctrlPersona.getListaPersonas());
    }    

    @FXML
    private void guardarPersona(ActionEvent event)
    {
        String nombre, apellidos, cedula, correo, telefono, direccion;
        LocalDate fechaNacimiento;
        
        nombre = txtfNombres.getText();
        apellidos = txtfApellidos.getText();
        cedula = txtfCedula.getText();
        correo = txtfCorreo.getText();
        telefono = txtfTelefono.getText();
        direccion = txtAreaDir.getText();
        fechaNacimiento = dtFN.getValue();
        
        ctrlPersona.agregarCliente(cedula, apellidos, nombre, direccion, correo, telefono, fechaNacimiento);
        presentarTabla(ctrlPersona.getListaPersonas());
        cancelarGuardado(event);
    }

    @FXML
    private void cancelarGuardado(ActionEvent event)
    {
        txtfApellidos.clear();
        txtfCedula.clear();
        txtfCorreo.clear();
        txtfNombres.clear();
        txtfTelefono.clear();
        txtAreaDir.clear();
        dtFN.setValue(null);
    }

    @FXML
    private void ordenarPersonas(ActionEvent event)
    {
        int orden = cbxOrdenar.getSelectionModel().getSelectedIndex() + 1;
        String atributo = cbxAtributo.getValue();
        String metodo = cbxMetodo.getValue();
        
        switch (metodo)
        {
            case "Quicksort":
                System.out.println("Quicksort");
                ctrlPersona.getListaPersonas().sortByQuickSort(atributo, orden, 0, ctrlPersona.getListaPersonas().length() - 1);    
                break;
            case "Shellsort":
                System.out.println("ShellSort");
                ctrlPersona.getListaPersonas().sortByShell(atributo, orden);    
                break;
        }
        
        
        presentarTabla(ctrlPersona.getListaPersonas());
    }
    
    private void presentarTabla(Lista<Persona> lista)
    {
        tvListaPersonas.getItems().clear();
        
        for (int i = 0; i < lista.length(); i++)
        {
            tvListaPersonas.getItems().add(lista.getByIndex(i));
        }
    }
    
    private void cargarComboBoxDeOrdenacion()
    {
        cbxOrdenar.getItems().add("Ascendente");
        cbxOrdenar.getItems().add("Descendente");
        cbxMetodo.getItems().add("Shellsort");
        cbxMetodo.getItems().add("Quicksort");
    }
    
    private void cargarAtributosEnCBX(ComboBox cbx)
    {
        for(Field field: Persona.class.getDeclaredFields())
        { 
            cbx.getItems().add(field.getName());
        }
    }
    
}
