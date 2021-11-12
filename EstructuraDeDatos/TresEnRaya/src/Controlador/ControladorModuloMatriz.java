/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.ModuloMatriz;

/**
 *
 * @author jere_
 */
public class ControladorModuloMatriz
{
    private ModuloMatriz matriz[][];

    public ControladorModuloMatriz(int n)
    {
        this.matriz = new ModuloMatriz[n][n];
    }

    public ModuloMatriz[][] getMatriz()
    {
        return matriz;
    }

    public void setMatriz(ModuloMatriz[][] matriz)
    {
        this.matriz = matriz;
    }
    
    public void imprimirMatriz()
    {
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz.length; j++)
            {
                System.out.print("[" + matriz[i][j].getModulo() + "]"); 
            } 
            System.out.println("\n");
        }
    }
    
    public void instanciarModulos()
    {
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz.length; j++)
            {
                matriz[i][j] = new ModuloMatriz(); 
            } 
        }
    }
    
    public double sumarFila(int n)
    {
        double sumaTotal = 0;
        
        for (int i = 0; i < matriz.length; i++)
        {
            sumaTotal += matriz[n][i].getModulo();  
        }
        
        return sumaTotal;
    }
    
    public double sumarColumna(int n)
    {
        double sumaTotal = 0;
        
        for (int i = 0; i < matriz.length; i++)
        {
            sumaTotal += matriz[i][n].getModulo();  
        }
        
        return sumaTotal;
    }
    
    public double sumarDiagonalPrincipal()
    {
        double sumaTotal = 0;
        
        for (int i = 0,  j = 0; i < matriz.length; i++, j++)
        {
            sumaTotal += matriz[i][j].getModulo();
        }
        
        return sumaTotal;
    }
    
    public double sumaDiagonalInversa()
    {
        double sumaTotal = 0;
        
        for (int i = matriz.length - 1,  j = 0; i >= 0; i--, j++)
        {
            sumaTotal += matriz[i][j].getModulo();
        }
        
        return sumaTotal;
    }
    
    public double mediaDeElementosDeMatriz()
    {
        double sumaTotal = 0;
        
        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz.length; j++)
            {
                sumaTotal += matriz[i][j].getModulo();
            }
  
        }
        
        return sumaTotal/(matriz.length*matriz.length);
    }
}
