/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Grafos;

import java.io.Serializable;

/**
 *
 * @author jere_
 */
public class GrafoND extends GrafoD implements Serializable
{   
    public GrafoND(Integer numV)
    {
        super(numV);
    }

    @Override
    public void insertarAristaConPeso(Integer i, Integer j, Double peso)
    {
        if(i <= numVertices() && j <= numVertices())
       {
           try
           {
               if(!existeAristas(i, j))
               {
                   numA++;
                   arregloListaAdyancentes[i].add(new Adyacencia(j,peso));
                   arregloListaAdyancentes[j].add(new Adyacencia(i,peso));
               }
           } catch (Exception ex)
           {
               System.out.println("Error al insertar arista en grafo no dirigido" + ex);
           }
       }
    }
    
    
}
