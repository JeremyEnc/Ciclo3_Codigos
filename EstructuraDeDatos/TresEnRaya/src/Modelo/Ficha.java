/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author jere_
 */
public class Ficha 
{
    private Image icono;
    private ImageView imgView;
    private int indicador;

    public Image getIcono() 
    {
        return icono;
    }

    public void setIcono(Image icono) 
    {
        this.icono = icono;
    }

    public ImageView getImgView() 
    {
        return imgView;
    }

    public void setImgView(ImageView imgView) 
    {
        this.imgView = imgView;
    }

    public int getIndicador()
    {
        return indicador;
    }

    public void setIndicador(int indicador) 
    {
        this.indicador = indicador;
    }

    
    
    
    
}
