/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import C.Lista;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author jere_
 */
public class ControladorVentanaTresEnRaya implements Initializable {

    @FXML
    private ImageView mvF1C1;
    @FXML
    private ImageView mvF1C2;
    @FXML
    private ImageView mvF1C3;
    @FXML
    private ImageView mvF2C1;
    @FXML
    private ImageView mvF2C2;
    @FXML
    private ImageView mvF2C3;
    @FXML
    private ImageView mvF3C1;
    @FXML
    private ImageView mvF3C2;
    @FXML
    private ImageView mvF3C3;
    @FXML
    private ImageView imvGanador;

    Lista<ImageView> listaImageView = new Lista();
    ControladorFicha ctrlControladorFicha = new ControladorFicha();
    
    Image imagen1 = new Image(getClass().getResource("../imgs/circulo.png").toString());
    Image imagen2 = new Image(getClass().getResource("../imgs/equis.png").toString());
    
    int contador;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
       listaImageView.add(mvF1C1);
       listaImageView.add(mvF1C2);
       listaImageView.add(mvF1C3);
       listaImageView.add(mvF2C1);
       listaImageView.add(mvF2C2);
       listaImageView.add(mvF2C3);
       listaImageView.add(mvF3C1);
       listaImageView.add(mvF3C2);
       listaImageView.add(mvF3C3);
       
       contador = 0;
    }    

    @FXML
    private void cargarFicha(MouseEvent event)
    {
        int ficha = listaImageView.getIndexOf((ImageView)event.getSource());
        
        if(contador % 2 == 0)
        {
            listaImageView.getByIndex(ficha).setImage(imagen2);
            ctrlControladorFicha.getTablero().getByIndex(ficha).setIndicador(1);
        }
        else
        {
            listaImageView.getByIndex(ficha).setImage(imagen1);
            ctrlControladorFicha.getTablero().getByIndex(ficha).setIndicador(2);
        }
        listaImageView.getByIndex(ficha).setDisable(true);
        contador++;
        
        if((contador >= 5) && (comprobarGanador()))
        {
            asignarGanador(ficha);
            bloquearFichas(true);
        }
        
        
    }
    
    private boolean comprobarGanador()
    {
        return (ctrlControladorFicha.verificarGanadorHorizontal()||
                ctrlControladorFicha.verificarGanadorVertical()||
                ctrlControladorFicha.verificarGanadorDiagonal());
        
    }
    
    private void bloquearFichas(boolean bloqueador)
    {
        for (int i = 0; i < listaImageView.length(); i++)
        {
            listaImageView.getByIndex(i).setDisable(bloqueador);
        }
    }
    
    private void asignarGanador(int ficha)
    {
        imvGanador.setImage((listaImageView.getByIndex(ficha).getImage()));
    }
    
    private void limpiarImagenes()
    {
        for (int i = 0; i < listaImageView.length(); i++)
        {
            listaImageView.getByIndex(i).setImage(null);
            ctrlControladorFicha.getTablero().getByIndex(i).setIndicador(0);
        }
        
        imvGanador.setImage(null);
    }

    @FXML
    private void reiniciarJuego(ActionEvent event)
    {
        contador = 0;
        bloquearFichas(false);
        limpiarImagenes();
    }
    
   
}
