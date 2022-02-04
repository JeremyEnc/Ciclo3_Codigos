/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Controlador.EstructurasDinamicas.Lista;
import Controlador.Grafos.Adyacencia;
import Controlador.Grafos.GrafoEtiquetadoD;
import Controlador.Grafos.GrafoEtiquetadoND;
import Modelo.ParadaBus;

/**
 *
 * @author jere_
 */
public class ControladorParadaBus
{
    ParadaBus paradaBus;
    GrafoEtiquetadoD grafo;

    public ParadaBus getParadaBus() {
        return paradaBus;
    }

    public void setParadaBus(ParadaBus paradaBus) {
        this.paradaBus = paradaBus;
    }

    public GrafoEtiquetadoD getGrafo()
    {
        return grafo;
    }

    public void setGrafo(GrafoEtiquetadoD grafo) {
        this.grafo = grafo;
    }
    
    
    public void generarParadaBus(String nombre, String calle1, String calle2, String referencia, Integer ruta)
    {
        agregarVertice();
        Long id = Long.valueOf(getGrafo().numVertices());
        paradaBus = new ParadaBus(id,nombre,calle1,calle2,referencia,ruta);
        getGrafo().colocarEtiquetaVertice((int)(paradaBus.getId() - 1), paradaBus.getNombre());
    }
    
    private void agregarVertice()
    {
        grafo = getGrafo();
        
        if(grafo == null)
        {
            System.out.println("creando nuevo grafo");
            grafo = new GrafoEtiquetadoND<>(1);
        }
        else
        {
            GrafoEtiquetadoD aux = new GrafoEtiquetadoD(grafo.numVertices()+1);
            System.out.println("añadiendo nuevo vertice");
            for (int i = 0; i < grafo.numVertices(); i++)
            {
                aux.colocarEtiquetaVertice(i, grafo.obtenerEtiqueta(i)); 
                Lista<Adyacencia> lista = grafo.adyacentes(i);
                
                for (int j = 0; j < lista.length(); j++)
                {
                    Adyacencia ad = lista.getByIndex(j);
                    aux.insertarAristaConPesoE(i, ad.getDestino(), ad.getPeso());
                }
            }
            grafo = aux;
        }
        
        setGrafo(grafo);
    }
}
