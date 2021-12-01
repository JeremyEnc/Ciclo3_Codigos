/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import C.Lista;
import modelo.Ficha;

/**
 *
 * @author jere_
 */
public class ControladorFicha
{
    private Lista<Ficha> tablero = new Lista();

    public ControladorFicha()
    {
        for (int i = 0; i < 9; i++)
        {
           tablero.add(new Ficha());
        }
    }

    public Lista<Ficha> getTablero() {
        return tablero;
    }

    public void setTablero(Lista<Ficha> tablero) {
        this.tablero = tablero;
    }
    
    public boolean verificarGanadorHorizontal()
    {
        for (int i = 0, j = 1, k = 2; k < 9; i+=3, j+=3, k+=3) 
        {
            if(tablero.getByIndex(i).getIndicador() == tablero.getByIndex(j).getIndicador())
            {
                if(tablero.getByIndex(i).getIndicador() == tablero.getByIndex(k).getIndicador())
                {
                    if(tablero.getByIndex(i).getIndicador() != 0)return true;
                }
            }
        }
        
        return false;
    }
    
    public boolean verificarGanadorVertical()
    {
        for (int i = 0, j = 3, k = 6; k < 9; i++, j++, k++) 
        {
            if(tablero.getByIndex(i).getIndicador() == tablero.getByIndex(j).getIndicador() )
            {
                if(tablero.getByIndex(j).getIndicador() == tablero.getByIndex(k).getIndicador())
                {
                    if(tablero.getByIndex(i).getIndicador() != 0)return true;               
                }
            }
        }
        
        return false;
    }
    
    public boolean verificarGanadorDiagonal()
    {
        for (int  i = 0, j = 4, k = 8; i < 3 ; i+=2, k-=2) 
        {
            if(tablero.getByIndex(i).getIndicador() == tablero.getByIndex(j).getIndicador())
            {   
                if(tablero.getByIndex(i).getIndicador() == tablero.getByIndex(k).getIndicador())
                {             
                    if(tablero.getByIndex(i).getIndicador() != 0)return true;
                }
            }
        }
        return false;
    }
    
}
