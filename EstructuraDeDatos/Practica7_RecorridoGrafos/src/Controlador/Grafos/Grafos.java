/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador.Grafos;

import Controlador.EstructurasDinamicas.Cola;
import Controlador.EstructurasDinamicas.Lista;
import java.io.Serializable;

/**
 *
 * @author jere_
 */
public abstract class Grafos implements Serializable
{
    /**
     * Es el numero de vertices de un grafo
     * @return Integer numero de vertices
     */
    public abstract Integer numVertices();
    /**
     * Retorna el numero de aristas
     * @return numero de Aristas
     */
    public abstract Integer numAristas();
    /**
     * Perimte ver si entre dos nodos hay conexion (arista)
     * @param i Nodo inicial
     * @param j Nodo final
     * @return true si hay conexion
     * @throws Exception exepcion 
     */
    public abstract Boolean existeAristas(Integer i, Integer j) throws Exception;
    /**
     * Retorna el peso que hay entre dos vertices
     * @param i Nodo Inicial
     * @param j Nodo final
     * @return Double peso de la arista
     */
    public abstract Double pesArista(Integer i, Integer j);
    /**
     * Permite inserta arista sin peso
     * @param i Nodo inicial
     * @param j Nodo final
     */
    public abstract void insertarArista(Integer i, Integer j);
    /**
     * Permite insertar arista con peso
     * @param i Nodo inicial
     * @param j Nodo final
     * @param peso Peso de la arista
     */
    public abstract void insertarAristaConPeso(Integer i, Integer j, Double peso);
    /**
     * Listado de adyacencias de un nodo
     * @param i Nodo
     * @return Lista de adyacencias
     */
    public abstract Lista<Adyacencia> adyacentes(Integer i);

    @Override
    public String toString()
    {
        String grafo = "";
        
        for (int i = 0; i < numVertices(); i++) 
        {
            grafo += "Vertice " + i;
            Lista<Adyacencia> lista = adyacentes(i);
            for (int j = 0; j < lista.length(); j++)
            {
                Adyacencia aux = lista.getByIndex(j);
                if(!aux.getPeso().toString().equalsIgnoreCase(String.valueOf(Double.NaN)))
                {
                    grafo += "  (Destino " + aux.getDestino() + "||" + "Peso " + aux.getPeso() + ")";  
                }else
                {
                    grafo += "  (Destino " + aux.getDestino() + ")";
                }
            }
            grafo += "\n";
        }       
        return grafo;
    }
    
    public Lista caminoMasCorto(Integer vI, Integer vF)
    {
        Lista<Integer> listaCaminos = new Lista();
        Lista<Double> listaPesos = new Lista();
        
        Integer inicial = vI;
        listaCaminos.add(vI);
        Boolean finalizar = false;
        
        while(!finalizar)
        {
            Lista<Adyacencia> listaAdyacencias = adyacentes(inicial);
            Double peso = 1000000.0;
            Integer I = -1;
            
            for (int i = 0; i < listaAdyacencias.length(); i++) 
            {
                Adyacencia ad = listaAdyacencias.getByIndex(i);
                Double pesoArista = ad.getPeso();
                
                I = ad.getDestino();
                
                if(vF.intValue() == ad.getDestino().intValue())
                {
                    peso = pesoArista;
                    break;
                }
                else if(pesoArista < peso)
                {
                    peso = pesoArista; 
                }
            }
            
            listaPesos.add(peso);
            listaCaminos.add(I);
            inicial = I;
            
            if(vF.intValue() == inicial.intValue())
            {
                finalizar = true;
            }
        }
        
        return listaCaminos;
    }
    
    public Lista<Integer> busquedaAnchura(Integer vI)
    {
        Lista<Integer> camino = new Lista();
        Integer nodosVisitados[] = new Integer[numVertices()];
        Cola<Integer> recorrido = new Cola<>(numVertices());
        
        Integer actual, nodoSiguiente;

        recorrido.queue(vI);
        nodosVisitados[vI] = 0;
        camino.add(vI);
        
        while(!recorrido.isEmpty())
        {
            actual = recorrido.dequeue();
            
            for (int i = 0; i < adyacentes(actual).length(); i++)
            {
                nodoSiguiente = adyacentes(actual).getByIndex(i).getDestino();
                if(nodosVisitados[nodoSiguiente] == null)
                {
                    nodosVisitados[nodoSiguiente] = 0;
                    recorrido.queue(nodoSiguiente);
                    camino.add(nodoSiguiente);
                }
                
            }
            
        }
 
        return camino;
    }
    
    
    public Lista<Integer> busquedaProfundidad(Integer vI)
    {
        Lista<Integer> camino = new Lista<>();
        Boolean nodosVisitados[] = new Boolean[numVertices()];
        
        ejectuarBusquedaEnProfundidad(vI, camino, nodosVisitados);
        
        return camino;
    }
    
    private void ejectuarBusquedaEnProfundidad(Integer v, Lista<Integer> camino, Boolean nv[])
    {
        int nodoSiguiente;
        
        nv[v] = true;
        camino.add(v);
        
        for (int i = 0; i < adyacentes(v).length(); i++) 
        {
            nodoSiguiente = adyacentes(v).getByIndex(i).getDestino();
            
            if(nv[nodoSiguiente] == null)
            {
                ejectuarBusquedaEnProfundidad(nodoSiguiente, camino, nv);
            }
        }
        
    }
    
}
