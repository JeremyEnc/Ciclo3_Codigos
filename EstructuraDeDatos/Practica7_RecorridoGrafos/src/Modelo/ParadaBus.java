/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author jere_
 */
public class ParadaBus 
{
    private Long id;
    private String nombre;
    private String calle1;
    private String calle2;
    private String referencia;
    private Integer ruta;

    public ParadaBus(Long id, String nombre, String calle1, String calle2, String referencia, Integer ruta) {
        this.id = id;
        this.nombre = nombre;
        this.calle1 = calle1;
        this.calle2 = calle2;
        this.referencia = referencia;
        this.ruta = ruta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getCalle1() {
        return calle1;
    }

    public void setCalle1(String calle1) {
        this.calle1 = calle1;
    }

    public String getCalle2() {
        return calle2;
    }

    public void setCalle2(String calle2) {
        this.calle2 = calle2;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public Integer getRuta() {
        return ruta;
    }

    public void setRuta(Integer ruta) {
        this.ruta = ruta;
    }
    
    
}
