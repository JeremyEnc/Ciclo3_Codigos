/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.util.converter.DoubleStringConverter;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaMenuMatriz implements Initializable {

    @FXML
    private TextField txtfNmatriz;
    @FXML
    private TableView<double[]> tvMatriz;
    @FXML
    private CheckBox chbxSFila;
    @FXML
    private ComboBox<Integer> cbxFila;
    @FXML
    private ComboBox<Integer> cbxColumna;
    @FXML
    private Label lblResultado;

    ControladorModuloMatriz  ctrlModuloMatriz;
    @FXML
    private CheckBox chbxSColumna;
    @FXML
    private CheckBox chbxSDP;
    @FXML
    private CheckBox chbxSDI;
    @FXML
    private CheckBox chbxMediaM;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        txtfNmatriz.addEventHandler(KeyEvent.KEY_TYPED, (t) -> 
        {
            if((Character.isDigit(t.getCharacter().charAt(0)) || t.getCharacter().equals(".")) == false)
            {
               t.consume();
            }
        });
        
        chbxSFila.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbxMediaM.setSelected(false);
            chbxSColumna.setSelected(false);
            chbxSDI.setSelected(false);
            chbxSDP.setSelected(false);
            cbxColumna.getSelectionModel().clearSelection();
        });
        
        chbxMediaM.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbxSFila.setSelected(false);
            chbxSColumna.setSelected(false);
            chbxSDI.setSelected(false);
            chbxSDP.setSelected(false);
            cbxColumna.getSelectionModel().clearSelection();
            cbxFila.getSelectionModel().clearSelection();
        });
        
        chbxSColumna.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbxSFila.setSelected(false);
            chbxMediaM.setSelected(false);
            chbxSDI.setSelected(false);
            chbxSDP.setSelected(false);
            cbxFila.getSelectionModel().clearSelection();
        });
        
        chbxSDI.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbxSFila.setSelected(false);
            chbxSColumna.setSelected(false);
            chbxMediaM.setSelected(false);
            chbxSDP.setSelected(false);
            cbxColumna.getSelectionModel().clearSelection();
            cbxFila.getSelectionModel().clearSelection();
        });
        
        chbxSDP.addEventHandler(ActionEvent.ACTION, (t) -> 
        {
            chbxSFila.setSelected(false);
            chbxSColumna.setSelected(false);
            chbxSDI.setSelected(false);
            chbxMediaM.setSelected(false);
            cbxColumna.getSelectionModel().clearSelection();
            cbxFila.getSelectionModel().clearSelection();
        });
        
        
    }    

    @FXML
    private void generarMatriz(ActionEvent event)
    {
        cbxColumna.getItems().clear();
        cbxFila.getItems().clear();
        
        tvMatriz.getColumns().clear();
        tvMatriz.getItems().clear();
        
        if(!txtfNmatriz.getText().isBlank())
        {
            int n = Integer.parseInt(txtfNmatriz.getText());

            TableColumn<double[], Double> columsMatriz[] = new TableColumn[n];

            for (int i = 0; i < columsMatriz.length; i++)
            {
                int j = i;

                columsMatriz[i] = new TableColumn<>(" ");
                columsMatriz[i].setCellValueFactory((c -> new SimpleObjectProperty<>(c.getValue()[j])));
                columsMatriz[i].setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
                columsMatriz[i].setMinWidth(70);
                tvMatriz.getItems().add(new double[n]);
                tvMatriz.getColumns().add(columsMatriz[i]);
                columsMatriz[i].setOnEditCommit(data -> {
                    data.getRowValue()[j] = data.getNewValue();
                });
                cbxFila.getItems().add(i + 1);
                cbxColumna.getItems().add(i + 1);
            }

            tvMatriz.setMaxWidth(n * columsMatriz[0].getMinWidth());
            tvMatriz.setMaxHeight(n * 50);

            if (n > 7)  n = 7;
               
            tvMatriz.setTranslateX(90 - 10 * n);
            tvMatriz.setTranslateY(25 - 3 * n);
        }else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Debe asignar un tamaño a la matriz");
            alert.showAndWait();
            
        }
        
        

    }

    @FXML
    private void calcularResultado(ActionEvent event)
    {
        obtenerMatriz();
        ctrlModuloMatriz.imprimirMatriz();
        
        double resultado = 0.0;

        if(chbxSDP.isSelected()) resultado = ctrlModuloMatriz.sumarDiagonalPrincipal();
        
        else if(chbxMediaM.isSelected()) resultado =  ctrlModuloMatriz.mediaDeElementosDeMatriz();
        
        else if(chbxSColumna.isSelected())
        {
            if(cbxColumna.getValue() != null)
            {
                 resultado = ctrlModuloMatriz.sumarColumna(cbxColumna.getItems().indexOf(cbxColumna.getValue()));
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe escoger una columna");
                alert.showAndWait();
            }
            
            
        }
        
        else if(chbxSFila.isSelected())
        {
            if(cbxFila.getValue() != null)
            {
                resultado = ctrlModuloMatriz.sumarFila(cbxFila.getItems().indexOf(cbxFila.getValue()));
            }
            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Debe escoger una fila");
                alert.showAndWait();
            }
        }
        
        else if(chbxSDI.isSelected()) resultado = ctrlModuloMatriz.sumaDiagonalInversa();
        
        lblResultado.setText(Double.toString(resultado));
        
    }
    
    
    
    public void obtenerMatriz()
    {
        ObservableList<double[]> matrizPrevia = tvMatriz.getItems() ;
        
        if(!matrizPrevia.isEmpty())
        {
            ctrlModuloMatriz = new ControladorModuloMatriz(matrizPrevia.size());
            ctrlModuloMatriz.instanciarModulos();

            for (int i = 0; i < ctrlModuloMatriz.getMatriz().length; i++)
            {
                for (int j = 0; j < ctrlModuloMatriz.getMatriz().length; j++)
                {
                    ctrlModuloMatriz.getMatriz()[i][j].setModulo(matrizPrevia.get(i)[j]);
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Error");
            alert.setContentText("Primero debe crear una matriz");
            alert.showAndWait();
        }
        
        
        

    }
}
