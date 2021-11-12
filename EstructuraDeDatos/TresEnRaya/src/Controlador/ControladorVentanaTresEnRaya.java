/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.net.URL;
import java.util.ResourceBundle;
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
    
    Image imagen1 = new Image(getClass().getResource("../imgs/circulo.png").toString());
    Image imagen2 = new Image(getClass().getResource("../imgs/equis.png").toString());
    
    ControladorFicha ctrlficha; 
    int contador;
    @FXML
    private ImageView imvGanador;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    { 
        ctrlficha = new ControladorFicha(3);
        ctrlficha.instanciarFichas();
        
        ctrlficha.getFichas()[0][0].setImgView(mvF1C1);
        ctrlficha.getFichas()[0][1].setImgView(mvF1C2);
        ctrlficha.getFichas()[0][2].setImgView(mvF1C3);
        ctrlficha.getFichas()[1][0].setImgView(mvF2C1);
        ctrlficha.getFichas()[1][1].setImgView(mvF2C2);
        ctrlficha.getFichas()[1][2].setImgView(mvF2C3);
        ctrlficha.getFichas()[2][0].setImgView(mvF3C1);
        ctrlficha.getFichas()[2][1].setImgView(mvF3C2);
        ctrlficha.getFichas()[2][2].setImgView(mvF3C3);
    }    

    @FXML
    private void cargarFicha(MouseEvent event) 
    {
        for (int i = 0; i < 3; i++) 
        {
            for (int j = 0; j < 3; j++) 
            {
                if(event.getSource().equals(ctrlficha.getFichas()[i][j].getImgView()))
                {
                    contador++;
                    
                    if(contador%2 == 0)
                    {
                        ctrlficha.getFichas()[i][j].setIcono(imagen1);
                        ctrlficha.getFichas()[i][j].setIndicador(1);
                    }else
                    {
                        ctrlficha.getFichas()[i][j].setIcono(imagen2);
                        ctrlficha.getFichas()[i][j].setIndicador(2);
                    } 
                    
                    ctrlficha.getFichas()[i][j].getImgView().setDisable(true);
                    
                    ctrlficha.cargarImagen(i,j); 
                    
                    if(contador >= 3)
                    {
                       if(analizarGanador(i,j))
                       {
                            bloquearFichas();
                       }   
                    } 
                }
            }
        }
        
        
    }

    
    private boolean analizarGanador(int x, int j)
    {
        int resultadoGanador = 0;
   
        for(int i = 0; i < 2; i++)
        {   
            if(ctrlficha.getFichas()[x][i].getIndicador() == ctrlficha.getFichas()[x][i+1].getIndicador())
            {
                resultadoGanador++;
                if(resultadoGanador == 2)
                {
                    asignarImagenGanador( x, i);
                    return true;
                }   
            }
        }
        
        resultadoGanador = 0;
        
        for(int i = 0; i < 2; i++)
        {   
            if(ctrlficha.getFichas()[i][j].getIndicador() == ctrlficha.getFichas()[i+1][j].getIndicador())
            {
                resultadoGanador++;
                if(resultadoGanador == 2)
                {
                    asignarImagenGanador( i, j);
                    return true;
                }
            }
        }
        
        resultadoGanador = 0;
        
        if(j != 1 && x != 1)
        {
            if(j == 0 && x == 0)
            {
                for (int i = 0, y = 0; i < 2; i++, y++)
                {
                    if (ctrlficha.getFichas()[i][y].getIndicador() == ctrlficha.getFichas()[i + 1][y +1].getIndicador())
                    {
                        resultadoGanador++;
                        if(resultadoGanador == 2)
                        {
                            asignarImagenGanador( i, y);
                            return true;
                        }
                    }
                }
            }
            
            if(j == 2 && x == 2)
            {
                resultadoGanador = 0;
            
                for (int i = 2, y = 2; i > 0; i--, y--)
                {
                    if (ctrlficha.getFichas()[i][y].getIndicador() == ctrlficha.getFichas()[i - 1][y - 1].getIndicador())
                    {
                        resultadoGanador++;
                        if(resultadoGanador == 2)
                        {
                            asignarImagenGanador( i, y);
                            return true;
                        }
                    }
                }
            }
            
            if(j == 0 && x == 2)
            {
                resultadoGanador = 0;
            
                for (int i = 2, y = 0; i > 0; i--, y++)
                {
                    if (ctrlficha.getFichas()[i][y].getIndicador() == ctrlficha.getFichas()[i - 1][y + 1].getIndicador())
                    {
                        resultadoGanador++;
                        if(resultadoGanador == 2)
                        {
                            asignarImagenGanador( i, y);
                            return true;
                        }
                    }
                }
            }
            
            if(j == 2 && x == 0)
            {
                resultadoGanador = 0;
            
                for (int i = 0, y = 2; i < 2; i++, y--)
                {
                    if (ctrlficha.getFichas()[i][y].getIndicador() == ctrlficha.getFichas()[i + 1][y - 1].getIndicador())
                    {
                        resultadoGanador++;
                        if(resultadoGanador == 2)
                        {
                            asignarImagenGanador( i, y);
                            return true;
                        }
                    }
                }
            }  
        }  
        
        return false;
    }
    
    public void asignarImagenGanador( int f, int c)
    {
        if (ctrlficha.getFichas()[f][c].getIndicador() == 1)
        {
            imvGanador.setImage(imagen1);
        } 
        else
        {
            imvGanador.setImage(imagen2);
        }
    }
    
    public void bloquearFichas()
    {
        for (int k = 0; k < 3; k++)
        {
            for (int l = 0; l < 3; l++)
            {
                ctrlficha.getFichas()[k][l].getImgView().setDisable(true);
            }
        }
    }
    
}
