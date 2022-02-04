/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo.Nodo;

/**
 *
 * @author jere_
 */
public class NodoEtiqueta 
{
    private Double acumulado;
    private Integer verticeAnterior;
    private Integer iteraciones;
    private Integer vertice;

    public NodoEtiqueta(Double acumulado, Integer verticeAnterior, Integer iteraciones, Integer vertice) {
        this.acumulado = acumulado;
        this.verticeAnterior = verticeAnterior;
        this.iteraciones = iteraciones;
        this.vertice = vertice;
    }

    public Integer getVertice() {
        return vertice;
    }

    public void setVertice(Integer vertice) {
        this.vertice = vertice;
    }
    
    public Double getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Double acumulado) {
        this.acumulado = acumulado;
    }

    public Integer getVerticeAnterior() {
        return verticeAnterior;
    }

    public void setVerticeAnterior(Integer verticeAnterior) {
        this.verticeAnterior = verticeAnterior;
    }

    public Integer getIteraciones() {
        return iteraciones;
    }

    public void setIteraciones(Integer iteraciones) {
        this.iteraciones = iteraciones;
    }
    
    
}
