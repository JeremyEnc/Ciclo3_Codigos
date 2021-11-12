/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Ficha;

/**
 *
 * @author jere_
 */
public class ControladorFicha 
{
    private Ficha fichas[][];

    public ControladorFicha(int n) 
    {
        this.fichas = new Ficha[n][n];
    }

    public Ficha[][] getFichas() {
        return fichas;
    }

    public void setFichas(Ficha[][] fichas) {
        this.fichas = fichas;
    }
    
    public void instanciarFichas()
    {
        for (int i = 0; i < fichas.length; i++) 
        {
            for (int j = 0; j < fichas.length; j++) 
            {
               fichas[i][j] = new Ficha();
            }   
        }
    }
    
    public void cargarImagen(int i, int j)
    {
        fichas[i][j].getImgView().setImage(fichas[i][j].getIcono());
    }
    
    
            
           
}
