/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Paciente;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaNutricional implements Initializable {

    @FXML
    private Spinner<Integer> spNPacientes;
    @FXML
    private Button btGenerarPacientes;
    @FXML
    private ComboBox<Integer> cbxNroPacientes;
    @FXML
    private DatePicker dpFechaNacimiento;
    @FXML
    private TextField txtfNombre;
    @FXML
    private CheckBox chbM;
    @FXML
    private CheckBox chbF;
    @FXML
    private TableColumn<Paciente, String> tcNombre;
    @FXML
    private TableColumn<Paciente, Integer> tcEdad;
    @FXML
    private TableColumn<Paciente, String> tcGenero;
    @FXML
    private TableColumn<Paciente, String> tcClasificacion;
    @FXML
    private TableColumn<Paciente, String> tcRecomendacion;
    @FXML
    private TableColumn<Paciente, LocalDate> tcNacimiento;
    @FXML
    private TableView<Paciente> tvRecomendaciones;
    @FXML
    private HBox hbInformacion;
    @FXML
    private MenuItem miLimpiar;
    @FXML
    private HBox hbFechaNOpcion;
    @FXML
    private HBox hbNombreOpcion;
    @FXML
    private VBox vbGenAgrOpciones;
    @FXML
    private Button btAgregarPaciente;
    
    ObservableList<Paciente> listaPacientes;
    ControladorPaciente ctrlPaciente;
    @FXML
    private Label lblNroPacientes;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcClasificacion.setCellValueFactory(new PropertyValueFactory<>("clasificacion"));
        tcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tcGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        tcRecomendacion.setCellValueFactory(new PropertyValueFactory<>("tratamiento"));
        tcNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaDeNacimiento"));
        
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100);
        valueFactory.setValue(1);
        spNPacientes.setValueFactory(valueFactory);
        
        comprobarPacienteSeleccionado();

        chbF.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbM.setSelected(false);
        });
        
        chbM.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbF.setSelected(false);
        });
        
        cbxNroPacientes.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            comprobarPacienteSeleccionado();
            if(cbxNroPacientes.getItems().isEmpty() == false)
            {
                if (ctrlPaciente.getPacientes()[cbxNroPacientes.getValue() - 1] == null) 
                {
                    btAgregarPaciente.setText("Agregar");
                    txtfNombre.clear();
                    dpFechaNacimiento.setValue(null);
                    chbF.setSelected(false);
                    chbM.setSelected(false);
                } 
                else 
                {
                    llenarInformacion();
                    btAgregarPaciente.setText("Modificar");
                }  
            }        
        });
        
        miLimpiar.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            limpiarDatos();
            spNPacientes.setDisable(false);
            btGenerarPacientes.setDisable(false);
            hbInformacion.setDisable(true);
        });
        
        hbInformacion.setDisable(true);     
    }    

    @FXML
    private void generarPacientes(ActionEvent event) 
    {
        listaPacientes = FXCollections.observableArrayList();
        
        ctrlPaciente = new ControladorPaciente(spNPacientes.getValue());
        cargarNroPacientes();
        
        spNPacientes.setDisable(true);
        btGenerarPacientes.setDisable(true);
        lblNroPacientes.setDisable(true);
        
        hbInformacion.setDisable(false);
        tvRecomendaciones.getItems().clear();
    }

    @FXML
    private void agregarPaciente(ActionEvent event) 
    {      
        if(txtfNombre.getText().isBlank() == false && dpFechaNacimiento.getValue() != null &&(chbF.isSelected() || chbM.isSelected()))
        {
            int pacienteSeleccionado = cbxNroPacientes.getValue() - 1;

            if (ctrlPaciente.getPacientes()[pacienteSeleccionado] == null) 
            {
                instanciarPacientesAtributos();
                
                listaPacientes.add(ctrlPaciente.getPacientes()[pacienteSeleccionado]);
                tvRecomendaciones.setItems(listaPacientes);
            } 
            else 
            {
                instanciarPacientesAtributos();

                listaPacientes.set(pacienteSeleccionado, ctrlPaciente.getPacientes()[pacienteSeleccionado]);
                tvRecomendaciones.refresh();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe llenar todos los datos");
            alert.showAndWait();
        }
    }
    
    public void cargarNroPacientes()
    {
        for (int i = 1; i <= spNPacientes.getValue(); i++) 
        {
            cbxNroPacientes.getItems().add(i);            
        }
    }   

    @FXML
    private void guardarInformacion(ActionEvent event) 
    {
        String nombre, edad, genero, clasificacion, recomendacion, fechaDeNacimiento;
        FileWriter fwSA = null;
                
        try{

            if(ctrlPaciente.getPacientes() != null || listaPacientes.isEmpty() == false)
                fwSA = new FileWriter("SistemaAlimenticio.txt", false);

            for (int i = 0; i < ctrlPaciente.getPacientes().length; i++)
            {
                nombre = ctrlPaciente.getPacientes()[i].getNombre();
                edad = Integer.toString(ctrlPaciente.getPacientes()[i].getEdad());
                genero = ctrlPaciente.getPacientes()[i].getGenero();
                clasificacion = ctrlPaciente.getPacientes()[i].getClasificacion();
                recomendacion = ctrlPaciente.getPacientes()[i].getTratamiento();
                fechaDeNacimiento = ctrlPaciente.getPacientes()[i].getFechaDeNacimiento().toString();
                
                fwSA.write(nombre + "\t" + edad + "\t" + genero + "\t" +  
                           clasificacion + "\t" + recomendacion + "\t" + fechaDeNacimiento + "\n");
            }

            fwSA.close();
            
            TrayNotification tray = new TrayNotification();
            AnimationType animation = AnimationType.POPUP;
            
            tray.setAnimationType(animation);
            tray.setTitle("Informacion guardada con Exito");
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            
        }
        catch (IOException ex) 
        {
            Logger.getLogger(ControladorVentanaNutricional.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NullPointerException e)
        {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe existir informacion para guardarla");
            alert.showAndWait();
        }
        
        
    }

    @FXML
    private void cargarInformacion(ActionEvent event) 
    {
        int contador = 0;
        String datos, datosFinales = "";
        String filasDivididas[], columnasDivididas[];
        listaPacientes = FXCollections.observableArrayList();
        
        spNPacientes.setDisable(true);
        lblNroPacientes.setDisable(true);
        btGenerarPacientes.setDisable(true); 
        hbInformacion.setDisable(false);
        
        limpiarDatos();
        
        try 
        {  
            FileReader frSA = new FileReader("SistemaAlimenticio.txt");
            BufferedReader brSA = new BufferedReader(frSA);
            
            while((datos = brSA.readLine()) != null)
            {
                datosFinales += datos + "\n";
                contador++;
            }
            
            ctrlPaciente = new ControladorPaciente(contador);
            
            filasDivididas = datosFinales.split("\n");
            
            for (int i = 0; i < contador; i++) 
            {
                columnasDivididas = filasDivididas[i].split("\t");
                
                ctrlPaciente.getPacientes()[i] 
                        = new Paciente(columnasDivididas[2], columnasDivididas[0], Integer.parseInt(columnasDivididas[1]), columnasDivididas[4], columnasDivididas[3], LocalDate.parse(columnasDivididas[5]) );
                listaPacientes.add(ctrlPaciente.getPacientes()[i]);
            }
        
            for (int i = 1; i <= ctrlPaciente.getPacientes().length; i++) 
            {
                cbxNroPacientes.getItems().add(i);
            }
            
            tvRecomendaciones.setItems(listaPacientes);
            
        } 
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(ControladorVentanaNutricional.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(ControladorVentanaNutricional.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    public void limpiarDatos() 
    {
        tvRecomendaciones.getItems().clear();
        cbxNroPacientes.getItems().clear();
        cbxNroPacientes.setPromptText("Nro Paciente");
        txtfNombre.clear();
        dpFechaNacimiento.setValue(null);
        chbF.setSelected(false);
        chbM.setSelected(false);
        ctrlPaciente = null;
    }
    
    private void llenarInformacion()
    {
        int pacienteSelect = cbxNroPacientes.getValue() - 1;
        LocalDate localDateFN = ctrlPaciente.getPacientes()[pacienteSelect].getFechaDeNacimiento();
        
        txtfNombre.setText(ctrlPaciente.getPacientes()[pacienteSelect].getNombre());
        dpFechaNacimiento.setValue(localDateFN);
        
        if(ctrlPaciente.getPacientes()[pacienteSelect].getGenero().equals("Femenino"))
        {
            chbF.setSelected(true);
            chbM.setSelected(false);
        }
        else
        {
            chbM.setSelected(true);
            chbF.setSelected(false);
        }
    }
    
    private void comprobarPacienteSeleccionado()
    {
        if (cbxNroPacientes.getValue() == null) 
        {
            hbFechaNOpcion.setDisable(true);
            vbGenAgrOpciones.setDisable(true);
            hbNombreOpcion.setDisable(true);
        } 
        else 
        {
            hbFechaNOpcion.setDisable(false);
            vbGenAgrOpciones.setDisable(false);
            hbNombreOpcion.setDisable(false);
        }
    }
    
    private void instanciarPacientesAtributos()
    {
        String genero = "";
        int pacienteSeleccionado = cbxNroPacientes.getValue() - 1;

        if (chbF.isSelected()) 
        {
            genero = "Femenino";
        } 
        else if (chbM.isSelected()) 
        {
            genero = "Masculino";
        }

        ctrlPaciente.getPacientes()[pacienteSeleccionado]
                = new Paciente(genero, txtfNombre.getText(), dpFechaNacimiento.getValue());

        ctrlPaciente.calcularEdad(pacienteSeleccionado);
        ctrlPaciente.clasificarPorEdad(pacienteSeleccionado);
    }
    
}
